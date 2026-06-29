package com.retailbanking.customers.service.impl;

import com.retailbanking.common.exception.BusinessException;
import com.retailbanking.common.exception.ResourceNotFoundException;
import com.retailbanking.customers.dto.request.CustomerRequest;
import com.retailbanking.customers.dto.response.CustomerResponse;
import com.retailbanking.customers.entity.Customer;
import com.retailbanking.customers.mapper.CustomerMapper;
import com.retailbanking.customers.repository.CustomerRepository;
import com.retailbanking.customers.service.CustomerService;
//import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {

        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("CUS_002", "Email already registered");
        }

        if (customerRepository.existsByMobile(request.getMobile())) {
            throw new BusinessException("CUS_003", "Mobile number already registered");
        }

        Customer customer = customerMapper.toEntity(request);

        customer.setStatus("ACTIVE");
        customer.setCreatedAt(Instant.now());
        customer.setUpdatedAt(Instant.now());

        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toResponse(savedCustomer);
    }

    @Override
    public CustomerResponse getCustomer(Long id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "CUS_004",
                                "Customer not found"));
        return customerMapper.toResponse(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponse> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toResponse)
                .toList();
    }

}