package com.retailbanking.customers.mapper;

import com.retailbanking.customers.dto.request.CustomerRequest;
import com.retailbanking.customers.dto.response.CustomerResponse;
import com.retailbanking.customers.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toEntity(CustomerRequest request){

        return Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .mobile(request.getMobile())
                .build();
    }

    public CustomerResponse toResponse(Customer customer) {

        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .mobile(customer.getMobile())
                .status(customer.getStatus())
                .build();

    }

}