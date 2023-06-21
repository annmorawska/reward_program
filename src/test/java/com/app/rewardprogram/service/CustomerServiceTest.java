package com.app.rewardprogram.service;

import com.app.rewardprogram.model.dto.NewCustomerDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void shouldAddNewCustomer() {
        String customerId = customerService.addNewCustomer(createCustomerTestData());

        Assertions.assertNotNull(customerId);
    }

    private NewCustomerDto createCustomerTestData() {
        return new NewCustomerDto("Customer Name", LocalDateTime.now());
    }
}
