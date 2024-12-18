package com.practice.main;

import com.practice.database.EmployeeDataBase;
import com.practice.entity.Employee2;

import java.util.List;

public class Main2 {
    public static void main(String[] args) {

        List<Employee2> empList = EmployeeDataBase.getAllEmployee2s();

        System.out.println("Employees from Development Department:: ");
        empList.stream().filter(emp -> emp.getDept().equals("Development"))
                .map(Employee2::getName)
                .forEach(System.out::println);

        System.out.println();
        System.out.println("");

    }
}
