package com.example.amex.repo;

import com.example.amex.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long > {


    Employee findByIdAndName(long id,String name);
}


