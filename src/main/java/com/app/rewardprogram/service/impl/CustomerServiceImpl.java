package com.app.rewardprogram.service.impl;

import com.app.rewardprogram.model.CustomerEntity;
import com.app.rewardprogram.model.dto.NewCustomerDto;
import com.app.rewardprogram.repository.CustomerRepository;
import com.app.rewardprogram.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    private static final int INITIAL_NUMBER_OF_POINTS = 0;

    @Override
    public String addNewCustomer(NewCustomerDto newCustomer) {
        CustomerEntity customer = new CustomerEntity();
        customer.setName(newCustomer.name());
        customer.setPoints(INITIAL_NUMBER_OF_POINTS);
        customer.setAuditCreationDate(newCustomer.creationDate());
        customer.setAuditModificationDate(newCustomer.creationDate());
        customer.setAuditModificationProcess("registerNewCustomer");

        return repository.save(customer).getId().toString();
    }
}
