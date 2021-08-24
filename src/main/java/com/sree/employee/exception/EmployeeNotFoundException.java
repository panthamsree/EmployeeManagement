package com.sree.employee.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Integer empid) {
        super("could not find employee with empid: '" + empid + "'");
    }


}