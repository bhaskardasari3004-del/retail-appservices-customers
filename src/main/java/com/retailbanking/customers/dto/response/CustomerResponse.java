package com.retailbanking.customers.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomerResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String mobile;

    private String status;

}