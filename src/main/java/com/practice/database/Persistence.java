package com.practice.database;


import com.practice.entity.Employee3;

import java.sql.*;

public class Persistence {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "lol123";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
    public static void insertEmployee(Employee3 employee){

        String sql = "INSERT INTO employee " +
                "(first_name, last_name, email, phone_number, hire_date, job_title, salary, department_id, manager_id) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";

        try(Connection conn = getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql)) {


            psmt.setString(1, employee.getFirst_name());
            psmt.setString(2, employee.getLast_name());
            psmt.setString(3, employee.getEmail());
            psmt.setString(4, employee.getPhone_number());
            psmt.setDate(5, Date.valueOf(String.valueOf(employee.getHire_date())));
            psmt.setString(6, employee.getJob_title());
            psmt.setDouble(7, employee.getSalary());
            psmt.setInt(8, employee.getDepartment_id());

            // Allowing null Values
            if(null != employee.getManager_id())
                psmt.setInt(9, employee.getManager_id());
            else
                psmt.setNull(9, Types.INTEGER);

            System.out.println("QUERY: " + psmt);
            int rowsAffected = psmt.executeUpdate();

            if (rowsAffected > 0){
                System.out.println("Data successfully inserted !!");
            } else {
                System.out.println("Failed to insert...");
            }

        } catch (SQLException e) {
            System.err.println("Database insertion error:");
            e.printStackTrace();
        }
    }



    public static void deleteEmployeeByID(int id) throws SQLException {
        String sql = "DELETE FROM public.employee " +
                "WHERE id = ?";
        try(Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql)){
            preparedStatement.setInt(1,id);

            System.out.println("QUERY: " + preparedStatement);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0){
                System.out.println("Data deleted successfully !!");
            } else {
                System.out.println("Failed to delete...");
            }
        }
    }
}
