package com.practice.main;

import com.practice.entity.Employee;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Employee> employeeList = List.of(new Employee("Dinesh",10000),
                new Employee("Daniel",60000),
                new Employee("Ameez", 90000),
                new Employee("Daniel", 60000),
                new Employee("Hitesh", 55000),
                new Employee("Franklin", 35000));

        Integer secondHighestSalary = employeeList.stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed()) // sorted by desc
                .map(Employee::getSalary)
                .distinct()
                .skip(1)
                .findFirst().get();

        System.out.println(secondHighestSalary);

        System.out.println(employeeList.stream()
                .filter(employee -> employee.getSalary()==secondHighestSalary)
                .map(Employee::getName)
                .findFirst());



        Optional<String> secondHighestSalaryEmployee = employeeList.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .map(Employee::getSalary)
                .distinct()
                .skip(1)
                .findFirst()
                .flatMap(secondSalary -> employeeList.stream()
                        .filter(emp -> emp.getSalary() == secondSalary)
                        .map(Employee::getName)
                        .findFirst()
                );


        // List of employees with salary greater than 50000
        employeeList.stream().filter(employee -> employee.getSalary() > 50000)
                .map(Employee::getName)
                .collect(Collectors.toSet())
                .forEach(System.out::println);

        System.out.println();
        employeeList.stream().filter(employee -> employee.getName().equalsIgnoreCase("Dinesh"))
                .forEach(System.out::println);

        System.out.println();
        System.out.println("Count the number of employees in the list");
        System.out.println(employeeList.stream().map(Employee::getName).count());

        System.out.println();
        System.out.println("Find the employee with the highest salary using streams.");
        System.out.println(employeeList.stream()
                .map(Employee::getSalary)
                .max(Integer::compare));

        System.out.println();
        System.out.println("Find the employee with the highest salary using streams.");
        System.out.println(employeeList.stream()
                .mapToInt(Employee::getSalary)
                .max());

        System.out.println();
        System.out.println("Find the employee with the lowest salary using streams");

        Optional<Employee> lowestSalaryEmployee = employeeList.stream()
                .min((e1,e2) -> Integer.compare(e1.getSalary(), e2.getSalary()));


        lowestSalaryEmployee.ifPresent(employee ->
                System.out.println("This is the gareeb employee: " + employee.getName()));

        System.out.println();
        System.out.println("Create a list of employee names using the map operation");
        employeeList.stream()
                .map(Employee::getName)
                .toList()
                .forEach(System.out::println);

        System.out.println();
        System.out.println("Sort the employees by their salary in ascending order");
        employeeList.stream()
                .sorted(Comparator.comparingInt(Employee::getSalary))
                .collect(Collectors.toSet())
                .forEach(System.out::println);

        System.out.println();
        System.out.println("Sort the employees by their names in alphabetical order");
        employeeList.stream()
                .sorted(Comparator.comparing(Employee::getName))
                .forEach(System.out::println);

        System.out.println();
        System.out.println("Distinct list of distinct employee names");
        employeeList.stream()
                .map(Employee::getName).distinct()
//                .sorted(Comparator.comparing(Employee::getName)) // will not work as its no longer an emp obj, its a string
                .sorted()
                .forEach(System.out::println);

        System.out.println();
        System.out.println("List of employees whose salary is between 10,000 and 70,000");
        employeeList.stream()
                .filter(employee -> 10000 < employee.getSalary() && employee.getSalary() < 70000 )
                .distinct()
                .collect(Collectors.toSet())
                .forEach(System.out::println);

        Map<String, List<Employee>> groupedBySalary = employeeList.stream()
                .collect(Collectors.groupingBy(employee -> {
                    if (employee.getSalary() < 50000) {
                        return "low";
                    } else if (employee.getSalary() <= 80000) {
                        return "medium";
                    } else {
                        return "high";
                    }
                }));

        System.out.println();
        System.out.println("Using the Complex map with FlatMap");
        groupedBySalary.entrySet().stream()
                .flatMap(stringListEntry -> stringListEntry.getValue().stream())
                .forEach(employee -> System.out.println(employee.getName() + " - " + employee.getSalary()));

        System.out.println();

        System.out.println("Printing the Map itself");
        groupedBySalary.forEach((salaryCategory, employees) -> {
            System.out.println(salaryCategory + ":");
            employees.forEach(employee -> System.out.println("  " + employee.getName() + " - " + employee.getSalary()));
        });

        System.out.println();
        System.out.println("if there is any employee with a salary greater than 60,000");
        System.out.println(employeeList.stream().filter(employee -> employee.getSalary() > 60000)
                .map(Employee::getName)
                .collect(Collectors.joining(", ")));


        System.out.println();
        System.out.println("Calculate the total salary of all employees");
        System.out.println("Total Salary is: " + employeeList.stream()
                .mapToInt(Employee::getSalary)
                .distinct()
                .sum());

        Stream<Employee> uniqueEmployees = employeeList.stream()
                .collect(Collectors.toMap(
                        Employee::getName,      // Key: Employee name for uniqueness
                        Function.identity(),    // Value: The entire Employee object
                        (existing, replacement) -> existing))   // Merge function: keep the first occurrence
                .values()                       // Get the unique Employee objects from the Map
                .stream();                     // Convert the Collection back to a Stream


        System.out.println("Total Salary ( Elaborative approach ): "+ uniqueEmployees.mapToInt(Employee::getSalary).sum());



        int totalSalary = employeeList.stream()
                .collect(Collectors.toMap(Employee::getName, Employee::getSalary, (existing, replacement) -> existing))
                .values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("Total Salary of Unique Employees: " + totalSalary);

        System.out.println();
        System.out.println("Calculate the average salary of the employees: ");
        System.out.println("Average Salary is: " + employeeList.stream()
                .mapToDouble(Employee::getSalary).average().orElse(0.0));

        System.out.println();
        employeeList.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .ifPresent(avg -> System.out.println("(Another way to use Optional Objects) Average Salary is = "+avg));

        System.out.println();
        System.out.println("List of Employees whose salary is greater than Average Salary: ");
        employeeList.stream()
                .filter(employee -> employee.getSalary() > employeeList.stream()
                        .mapToDouble(Employee::getSalary)
                        .average()
                        .orElse(0.0))
                .map(Employee::getName)
                .distinct()
                .forEach(System.out::println);

        System.out.println();
        System.out.println("Create a Map<String, Integer> where the key is the employee name and the value is their salary");
        Map<String, Integer> employeeMap = employeeList.stream()
                .collect(Collectors.toMap(
                        Employee::getName,
                        Employee::getSalary,
                        (existing, newSalary) -> existing) // This line is for setting the merge function, in case there are duplicates
                );
        System.out.println(employeeMap);

        /***
         * [NOTES] - Alternative Merge Strategies:
         * Keep the higher salary:
         * (existingSalary, newSalary) -> Math.max(existingSalary, newSalary)
         *
         * Sum the salaries:
         * (existingSalary, newSalary) -> existingSalary + newSalary
         *
         * Keep the lower salary:
         * (existingSalary, newSalary) -> Math.min(existingSalary, newSalary)
         */


        System.out.println();
        System.out.println("Partition the employees into two groups: those earning above 50,000 and those earning 50,000 or less");
        Map<Boolean, List<Employee>> partitionedBySalary = employeeList.stream()
                .collect(Collectors.partitioningBy(employee -> employee.getSalary() > 50000));
        System.out.println(partitionedBySalary);

        System.out.println("-----TRYING TO ITERATE OVER A MAP (TYPE-1)-----");
        System.out.println();
        for (Map.Entry<Boolean, List<Employee>> em : partitionedBySalary.entrySet()){
            System.out.println();
            System.out.println(em.getKey() ? "Salaries over 50,000 : " : "Salaries under 50,000");
            for(Employee employee: em.getValue()){
                System.out.println(employee.getName() + " => " + employee.getSalary());
            }
        }

        System.out.println("-----TRYING TO ITERATE OVER A MAP (TYPE-2)-----");
        System.out.println();
        partitionedBySalary.forEach((isAbove50k, employees) -> {
            System.out.println(isAbove50k ? "Salaries above 50,000:" : "Salaries 50,000 or below:");
            employees.forEach(employee -> System.out.println(employee.getName() + ": " + employee.getSalary()));
        });


        System.out.println();;
        System.out.println("Use streams to find the employee with the longest name");
        Optional<Employee> longestNamedEmployee = employeeList.stream().max(Comparator.comparingInt(e -> e.getName().length()));
        longestNamedEmployee.ifPresent(employee -> System.out.println("Employee with Longest name is : " + employee.getName()));


        // Transformations in STREAMS

        System.out.println();
        System.out.println("Create a new list of employees with their salaries increased by 10%");
        List<Employee> increementedList = employeeList.stream()
                .map(employee -> new Employee(employee.getName(), (int) (employee.getSalary() * 1.1)))
                .toList();
        increementedList.forEach(System.out::println);


        System.out.println();
        System.out.println("a new list of employees with a 5% salary increase for those earning below 50,000");

        System.out.println();
        System.out.println("Create a list of the lengths of each employee's name");
        Map<String, Integer> lengthOfNames ;

        List<Integer> collect = employeeList.stream().map(employee -> employee.getName().length()).collect(Collectors.toList());
        collect.forEach(System.out::println);

        System.out.println();
        System.out.println(": If you have a list of employee details as strings (e.g., \"Dinesh:10000\"), convert them into Employee objects using streams.");


        System.out.println();
        System.out.println("Collect the names of employees with salaries above 50,000 into a Set<String>");


        // MISCELLANEOUS TYPES in STREAMS

        System.out.println();
        System.out.println("Find employees whose names start with the letter 'D'");


        System.out.println();
        System.out.println("Write a custom comparator to sort employees by salary in descending order.");


        System.out.println();
        System.out.println("");




    }


}