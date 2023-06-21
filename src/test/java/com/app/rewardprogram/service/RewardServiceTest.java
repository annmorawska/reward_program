package com.app.rewardprogram.service;

import com.app.rewardprogram.model.dto.MonthlyRewardDto;
import com.app.rewardprogram.model.dto.PurchaseDto;
import com.app.rewardprogram.model.dto.RewardDto;
import com.app.rewardprogram.service.impl.RewardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static com.app.rewardprogram.TestDataProvider.OBJECT_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = TestConfiguration.class)
public class RewardServiceTest {

    @Mock
    private PurchaseService purchaseService;

    private Clock clock;

    private RewardService rewardService;

    private static final int YEAR = 2023;
    private final static LocalDateTime DATE = LocalDateTime.of(YEAR, 6, 20, 12, 0, 0);


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        clock = Clock.fixed(DATE.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        rewardService = new RewardServiceImpl(purchaseService, clock);
    }

    @Test
    public void shouldReturnProperMonths() {
        RewardDto reward = rewardService.retrieveRewardData(OBJECT_ID);
        List<MonthlyRewardDto> monthlyRewards = reward.lastThreeMonthsReward();

        assertThat(monthlyRewards.size()).isEqualTo(3);
        assertThat(monthlyRewards)
                .anySatisfy(monthlyReward -> {
                    assertThat(monthlyReward.year()).isEqualTo(YEAR);
                    assertThat(monthlyReward.month()).isEqualTo(6);
                });
        assertThat(monthlyRewards)
                .anySatisfy(monthlyReward -> {
                    assertThat(monthlyReward.year()).isEqualTo(YEAR);
                    assertThat(monthlyReward.month()).isEqualTo(5);
                });
        assertThat(monthlyRewards)
                .anySatisfy(monthlyReward -> {
                    assertThat(monthlyReward.year()).isEqualTo(YEAR);
                    assertThat(monthlyReward.month()).isEqualTo(4);
                });
    }

    @Test
    public void shouldReturnProperReward() {
        when(purchaseService.getRecentPurchasesFromDate(any(), any())).thenReturn(createPurchaseData());

        RewardDto reward = rewardService.retrieveRewardData(OBJECT_ID);
        List<MonthlyRewardDto> monthlyRewards = reward.lastThreeMonthsReward();

        assertThat(monthlyRewards.size()).isEqualTo(3);
        assertThat(monthlyRewards)
                .anySatisfy(monthlyReward -> {
                    assertThat(monthlyReward.year()).isEqualTo(YEAR);
                    assertThat(monthlyReward.month()).isEqualTo(6);
                    assertThat(monthlyReward.points()).isEqualTo(10);
                });
        assertThat(monthlyRewards)
                .anySatisfy(monthlyReward -> {
                    assertThat(monthlyReward.year()).isEqualTo(YEAR);
                    assertThat(monthlyReward.month()).isEqualTo(5);
                    assertThat(monthlyReward.points()).isEqualTo(20);
                });
        assertThat(monthlyRewards)
                .anySatisfy(monthlyReward -> {
                    assertThat(monthlyReward.year()).isEqualTo(YEAR);
                    assertThat(monthlyReward.month()).isEqualTo(4);
                    assertThat(monthlyReward.points()).isEqualTo(30);
                });
        assertThat(reward.lastThreeMonthsPoints()).isEqualTo(60);
    }

    private List<PurchaseDto> createPurchaseData() {
        List<PurchaseDto> purchases = new ArrayList<>();
        purchases.add(PurchaseDto.builder().customerId(OBJECT_ID).points(10).creationDate(DATE).build());

        purchases.add(PurchaseDto.builder().customerId(OBJECT_ID).points(10).creationDate(DATE.minusMonths(1)).build());
        purchases.add(PurchaseDto.builder().customerId(OBJECT_ID).points(10).creationDate(DATE.minusMonths(1)).build());

        purchases.add(PurchaseDto.builder().customerId(OBJECT_ID).points(10).creationDate(DATE.minusMonths(2)).build());
        purchases.add(PurchaseDto.builder().customerId(OBJECT_ID).points(10).creationDate(DATE.minusMonths(2)).build());
        purchases.add(PurchaseDto.builder().customerId(OBJECT_ID).points(10).creationDate(DATE.minusMonths(2)).build());

        return purchases;
    }
}
