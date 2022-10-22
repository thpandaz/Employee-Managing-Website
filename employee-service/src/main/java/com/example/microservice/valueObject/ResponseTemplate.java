package com.example.microservice.valueObject;

import com.example.microservice.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplate {

    private Employee employee;
    private  Department department;
}
