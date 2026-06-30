package com.retailbanking.customers.controller;

import com.retailbanking.common.constants.CorrelationConstants;
import com.retailbanking.common.response.ApiResponse;
import com.retailbanking.customers.dto.request.CustomerRequest;
import com.retailbanking.customers.dto.response.CustomerResponse;
import com.retailbanking.customers.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

import static com.retailbanking.common.constants.CorrelationConstants.CORRELATION_ID;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer( @Valid @RequestBody CustomerRequest request) {

        CustomerResponse response = customerService.createCustomer(request);

        ApiResponse<CustomerResponse> responseApiResponse =  ApiResponse.<CustomerResponse>builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .data(response)
                .message("Customer Created Successfully")
                .timestamp(Instant.now())
                .correlationId(MDC.get(CORRELATION_ID))
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(responseApiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> getCustomer(@PathVariable Long id) {

        CustomerResponse response = customerService.getCustomer(id);

        ApiResponse<CustomerResponse> apiResponse =
                ApiResponse.<CustomerResponse>builder()
                        .success(true)
                        .status(HttpStatus.OK.value())
                        .message("Customer Retrieved Successfully")
                        .data(response)
                        .timestamp(Instant.now())
                        .correlationId(
                                MDC.get(CorrelationConstants.CORRELATION_ID))
                        .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> getAllCustomers() {

        List<CustomerResponse> response = customerService.getAllCustomers();

        ApiResponse<List<CustomerResponse>> apiResponse =
                ApiResponse.<List<CustomerResponse>>builder()
                        .success(true)
                        .status(HttpStatus.OK.value())
                        .errorCode(null)
                        .message("Customers Retrieved Successfully")
                        .data(response)
                        .timestamp(Instant.now())
                        .correlationId(MDC.get(CORRELATION_ID))
                        .build();

        return ResponseEntity.ok(apiResponse);
    }


}
