package com.sree.employee.controller;

import com.sree.employee.service.EmployeeServiceImpl;
import com.sree.employee.vo.EmployeeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<EmployeeVO> createEmployee(@RequestBody EmployeeVO emp){
        return new ResponseEntity(employeeServiceImpl.createEmployee(emp), HttpStatus.CREATED);
    }

    @GetMapping("/{empId}")
    public ResponseEntity<EmployeeVO> getEmployee(@PathVariable Integer empId){
        return new ResponseEntity(employeeServiceImpl.getEmployee(empId), HttpStatus.OK);
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer empId){
        employeeServiceImpl.deleteEmployee(empId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{empId}")
    public ResponseEntity<?> updateEmployee(@PathVariable Integer empId,@RequestBody EmployeeVO empVO){
        return new ResponseEntity(employeeServiceImpl.updateEmployee(empId,empVO), HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeVO>> getAllEmployees(){
        return new ResponseEntity(employeeServiceImpl.getAllEmployees(), HttpStatus.OK);
    }

}

