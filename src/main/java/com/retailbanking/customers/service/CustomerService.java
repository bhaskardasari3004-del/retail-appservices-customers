package com.retailbanking.customers.service;

import com.retailbanking.customers.dto.request.CustomerRequest;
import com.retailbanking.customers.dto.response.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest request);

    CustomerResponse getCustomer(Long id);

    List<CustomerResponse> getAllCustomers();
}