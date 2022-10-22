package com.example.microservice.service;

import com.example.microservice.entity.Employee;
import com.example.microservice.repository.UserRepository;
import com.example.microservice.valueObject.Department;
import com.example.microservice.valueObject.ResponseTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;
    public Employee saveUser(Employee employee) {
        log.info("Inside saveUser of UserService");
        return userRepository.save(employee);
    }
    public ResponseTemplate getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment of UserService");
        ResponseTemplate vo = new ResponseTemplate();
        Employee employee = userRepository.findByUserId(userId);

        Department department =
                restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + employee.getDepartmentId()
                        ,Department.class);

        vo.setEmployee(employee);
        vo.setDepartment(department);

        return  vo;
    }
}
