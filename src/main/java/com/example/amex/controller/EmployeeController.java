package com.example.amex.controller;


import com.example.amex.entity.Employee;
import com.example.amex.repo.EmployeeRepository;
import com.example.amex.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping ("/employee")
public class EmployeeController {
    @Autowired
    EmployeeRepository repo;
   // @PostMapping ("/save")
//    public Employee saveEmployee(Employee employee){
//        return repo.save(employee);
//
//    }
//    @GetMapping("/add/{number1}/{number2}")
//    public String addition(@PathVariable Integer number1, @PathVariable Integer number2) {
//        int i = number1 + number2;
//        String additionResultt =  String.valueOf(i);
//        return additionResultt;
//    }

    @GetMapping ("/empID/{empID}")
    public static Optional<Employee> userRequest(@PathVariable Long empID){
        return EmployeeService.getDetails(empID);
      // return emp;
       // return repo.findById(empID);

    }



}
