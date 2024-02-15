package com.example.amex.service;

import com.example.amex.entity.Employee;
import com.example.amex.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    public EmployeeRepository repo;
    @Autowired
    public RestTemplate restTemplate;


    public Integer add(Integer a, Integer b){
        return a+b;
    }

    public Employee getEmpDetails(Long ID){
       return repo.findById(ID).get();
    }

    public List<Employee> getAll(){
        return repo.findAll();
    }

    public ResponseEntity<Employee> saveEmpDetails(Employee emp){
       Employee e=  repo.save(emp);
        return new ResponseEntity<>(e,HttpStatus.ACCEPTED);
    }

     public ResponseEntity<Employee> updateEmpdetails(Long Id, Employee emp){
        Optional<Employee> employee = repo.findById(Id);
        if(employee.isPresent()){
            employee.get().setName(emp.getName());
            employee.get().setEmail(emp.getEmail());
            return new ResponseEntity<>(repo.save(employee.get()),HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Employee> deleteEmpDetails(long ID){
        Optional<Employee> employee = repo.findById(ID);
       if (employee.isPresent()){
           repo.deleteById(ID);
           return new ResponseEntity<>(HttpStatus.OK);
       }
       else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }


public ResponseEntity<String> getStudent(){
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
    return restTemplate.exchange("http://localhost:7070/students/api/getAll", HttpMethod.GET,entity,String.class);
}

    public Page<Employee> getAllEmployees(Pageable pageable) {
        return repo.findAll(pageable);
    }
}
