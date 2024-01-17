package com.example.amex.controller;


import com.example.amex.entity.Employee;
import com.example.amex.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/employee")
public class EmployeeController {
    @Autowired
 //   private EmployeeService service;
    private EmployeeRepository repo;
    @PostMapping ("/save")
    public Employee saveEmployee(@RequestBody Employee employee){
        return repo.save(employee);

    }

    @PostMapping ("/save/response")
    public ResponseEntity<Employee> saveEmployeeWithResponse(@RequestBody Employee employee){
        return new ResponseEntity<>(repo.save(employee), HttpStatus.CREATED);

    }
//    @GetMapping("/add/{number1}/{number2}")
//    public String addition(@PathVariable Integer number1, @PathVariable Integer number2) {
//        int i = number1 + number2;
//        String additionResultt =  String.valueOf(i);
//        return additionResultt;
//    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Employee>> getAll(){
        return new ResponseEntity<>(repo.findAll(),HttpStatus.OK);
    }


    @GetMapping ("/empID/{empID}")
    public Optional<Employee> userRequest(@PathVariable Long empID){
        //return service.getDetails(empID).get();
      // return emp;
        return repo.findById(empID);

    }

    @GetMapping("/empID/new/{empID}")
    public Employee userRequestNew(@PathVariable Long empID){
        return repo.findById(empID).get();
    }

    @GetMapping("/empID/response/{empID}")
    public ResponseEntity<Employee> getEmpDetails(@PathVariable Long empID){
        Optional<Employee> employee = repo.findById(empID);
        if(employee.isPresent()) {
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/empID/updateEmpDetails/{empID}")
    public ResponseEntity<Employee> userUpdate(@PathVariable Long empID, @RequestBody Employee emp){
        Optional<Employee> employee = repo.findById(empID);
        if(employee.isPresent()) {
            employee.get().setName(emp.getName());
            employee.get().setEmail(emp.getEmail());
            return new ResponseEntity<>(repo.save(employee.get()), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/empID/{empID}")
    public ResponseEntity<Void> deleteEmpDetails(@PathVariable Long empID){
        Optional<Employee> employee = repo.findById(empID);
        if(employee.isPresent()) {
            repo.deleteById(empID);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
