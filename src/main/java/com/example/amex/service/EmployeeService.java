package com.example.amex.service;

import com.example.amex.entity.Employee;
import com.example.amex.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private static EmployeeRepository repo;
    public static Optional<Employee> getDetails(Long empID) {

        return repo.findById(empID);

    }
}
