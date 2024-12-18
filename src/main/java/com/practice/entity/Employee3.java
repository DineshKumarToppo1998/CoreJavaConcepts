package com.practice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee3 {
    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private Date hire_date;
    private String job_title;
    private double salary;
    private int department_id;
    private Integer manager_id;
}
