package com.example;
import java.util.ArrayList;
import java.util.List;
public class App 
{
    // Class to store employee details
    static class Employee 
    {
        // Employee Information
        String empId;
        String name;
        int age;
        String dept;
        String empType;
        String clearanceLevel;
        boolean idValid;
        String accessLevel;

        // Constructor to Initialize Employee Details
        Employee(String empId, String name, int age, String dept, String empType, String clearanceLevel, boolean idValid, String accessLevel) 
        {
            this.empId=empId;
            this.name=name;
            this.age=age;
            this.dept=dept;
            this.empType=empType;
            this.clearanceLevel=clearanceLevel;
            this.idValid=idValid;
            this.accessLevel=accessLevel;
        }
    }
    // Method to Check Whether an Employee is Eligible for Access
    public static String checkEligibility(Employee e) 
    {
        // List to Store all Rejection Reasons
        List<String> reasons=new ArrayList<>();

        // Check if Employee ID is Present
        if(e.empId==null||e.empId.trim().isEmpty())
        {
            reasons.add("Invalid Employee ID");
        }

        // Check Minimum Age Requirement
        if(e.age<21)
        {
            reasons.add("Employee Must be At Least 21 Years Old");
        }

        // Check if Department is Authorized
        if(!e.dept.equalsIgnoreCase("IT") && !e.dept.equalsIgnoreCase("HR") && !e.dept.equalsIgnoreCase("Finance") && !e.dept.equalsIgnoreCase("Administration"))
        {
            reasons.add("Unauthorized Department");
        }

        // Check if Employee is Currently Active
        if(!e.empType.equalsIgnoreCase("Active"))
        {
            reasons.add("Employment Status is Inactive");
        }

        // Check if Employee ID is Valid
        if(!e.idValid)
        {
            reasons.add("Employee ID is Invalid");
        }

        // Check Security Clearance for Confidential Resources
        if(e.accessLevel.equalsIgnoreCase("Confidential")) 
        {
            // High Clearance is Required for Confidential Access
            if(!e.clearanceLevel.equalsIgnoreCase("High"))
            {
                reasons.add("Insufficient Security Clearance for Confidential Access");
            }
        }

        // Fully Eligible Employee
        if(reasons.size()==0)
        {
            return "Eligible";
        }

        // Only Insufficient Clearance Results in Conditional Eligibility
        if(reasons.size()==1 && reasons.get(0).equals("Insufficient Security Clearance for Confidential Access"))
        {
            return "Conditionally Eligible";
        }

        // Return all Applicable Rejection Reasons
        return "Not Eligible: " + String.join(", ",reasons);
    }

    public static void main(String[] args) 
    {
        // Employee Satisfying all Eligibility Conditions
        Employee e1=new Employee("E101", "Arsh", 25, "IT", "Active", "High", true, "Confidential");

        // Employee Exactly at the Minimum Age Boundary
        Employee e2=new Employee("E102", "Rahul", 21, "HR", "Active", "Medium", true, "Normal");

        // Employee below the Minimum Age
        Employee e3=new Employee("E103", "Aman", 20, "Finance", "Active", "High", true, "Normal");

        // Employee having Multiple Eligibility Failures
        Employee e4=new Employee("E104", "Rohan", 30, "Sales", "Inactive", "Low", false, "Confidential");

        // Employee with Insufficient Clearance for Confidential Access
        Employee e5=new Employee("E105", "Priya", 28, "Administration", "Active", "Medium", true, "Confidential");

        // Employee Satisfying all Conditions for Confidential Access
        Employee e6=new Employee("E106", "Neha", 22, "Finance", "Active", "High", true, "Confidential");

        // Store all Pre-Defined Employees in an Array
        Employee[] employees={e1, e2, e3, e4, e5, e6};

        // Check Eligibility of each Employee
        for(Employee e:employees) 
        {
            // Display employee details
            System.out.println("Employee ID: " + e.empId);
            System.out.println("Name: " + e.name);

            // Display eligibility result
            System.out.println("Result: " + checkEligibility(e));

            // Separate each employee's result
            System.out.println("-------------------------\n");
        }
    }
}
