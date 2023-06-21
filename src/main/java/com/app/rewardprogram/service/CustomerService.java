package com.app.rewardprogram.service;

import com.app.rewardprogram.model.dto.NewCustomerDto;

public interface CustomerService {
    String addNewCustomer(NewCustomerDto newCustomer);
}
