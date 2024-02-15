package com.example.amex.controller;


import com.example.amex.entity.Employee;
import com.example.amex.repo.EmployeeRepository;
import com.example.amex.service.EmployeeService;
import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository repo;
    @Autowired
    public EmployeeService service;
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

        return new ResponseEntity<>(service.getAll(),HttpStatus.OK);
    }


    @GetMapping ("/empID/{empID}")
    public Optional<Employee> userRequest(@PathVariable Long empID){
        //return service.getDetails(empID).get();
      // return emp;
        return repo.findById(empID);

    }

    @GetMapping ("/empID/{empID}/{name}")
    public Employee userRequestByIdAndName(@PathVariable Long empID, @PathVariable String name){
        //return service.getDetails(empID).get();
        // return emp;
        return repo.findByIdAndName(empID,name);

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



    @GetMapping("/add/{num1}/{num2}")
    public Integer userReq(@PathVariable Integer num1, @PathVariable Integer num2){
        return service.add(num1,num2);
    }

    @GetMapping("/add/get/{ID}")
    public Employee myRequest(@PathVariable Long ID){
        return service.getEmpDetails(ID);
    }

    @PostMapping("/save/new")
    public HttpStatusCode myRequest(@RequestBody Employee emp){
        ResponseEntity<Employee>  responseenity =  service.saveEmpDetails(emp);
        return responseenity.getStatusCode();

    }

    @PutMapping("/update/empID/{ID}")
    public HttpStatusCode myRequest(@PathVariable Long ID, @RequestBody Employee employee){
        return service.updateEmpdetails(ID, employee).getStatusCode();
    }

    @DeleteMapping("/delete/empID/delete/{ID}")
    public HttpStatusCode myRequestDelete(@PathVariable Long ID){
        return service.deleteEmpDetails(ID).getStatusCode();
    }
    @GetMapping("/students")
    public ResponseEntity<String> getAll1(){
       return service.getStudent();
    }
    @GetMapping("/download")
    public void downloadCsv(HttpServletResponse response) throws IOException {
        List<Employee> employees = service.getAll();
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employees.csv");

        // Write CSV content to response output stream using OpenCSV
        CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(response.getOutputStream()));
        String[] headers = {"ID", "Name", "Email"};
        csvWriter.writeNext(headers);

        for (Employee employee : employees) {
            String[] data = {String.valueOf(employee.getId()), employee.getName(), employee.getEmail()};
            csvWriter.writeNext(data);
        }

        csvWriter.close();
    }

    @GetMapping("/page/{page}/{size}")
    public ResponseEntity<Page<Employee>> getAllEmployees(
            @PathVariable Integer page, @PathVariable Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employees = service.getAllEmployees(pageable);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
