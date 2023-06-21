package com.app.rewardprogram.api;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PurchaseRestIT extends TestControllerConfiguration {

    @Test
    public void shouldAddPurchaseAndReturnEarnedPoints() throws Exception {
        //given
        String newPurchaseBody = "";

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("purchase/add/6491d9694fd2e92b5c443d8b")
                        .content(newPurchaseBody)
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
