package com.practice.database;

import com.practice.entity.Employee3;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnection {


    public static void main(String[] args) {

        //VALUES(141, 'Alice', 'Johnson', 'alice.johnson@example.com', '123-456-7890', '2023-01-01', 'Manager', 70000.00, 1, NULL);
        Employee3 employee3 = new Employee3("Avishek", "Barik", "avishek_barik@gmail.com", "+919876543120", Date.valueOf("2024-01-01"), "Legal Advisor", 43000, 1, null);
        Persistence.insertEmployee(employee3);



    }



}
