package com.example.demo.controllers;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/_create", method = RequestMethod.POST)
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.create(employee);
        return ResponseEntity.status(HttpStatus.OK).body(savedEmployee);
    }

    @RequestMapping(value = "/_search", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> searchAllEmployees() {
        List<Employee> employeeList = employeeService.searchAll();
        return ResponseEntity.status(HttpStatus.OK).body(employeeList);
    }

    @RequestMapping(value = "/_search/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> searchById(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
        Employee employee = employeeService.search(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @RequestMapping(value = "/_update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                   @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee updatedEmployee = employeeService.update(employeeId, employeeDetails);
        return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
    }

}
