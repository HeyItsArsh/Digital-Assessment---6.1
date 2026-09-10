package com.example;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class AppTest 
{
    // Test for a Completely Eligible Employee
    @Test
    void testEligibleEmployee() 
    {
        // Create a valid employee
        App.Employee e=new App.Employee("E101", "Arsh", 25, "IT", "Active", "High", true, "Confidential");

        // Check Expected Eligibility Result
        assertEquals("Eligible",App.checkEligibility(e));
    }

    // Test for the Minimum Allowed Age of 21
    @Test
    void testBoundaryAge() 
    {
        // Employee is Exactly 21 Years Old
        App.Employee e=new App.Employee("E102", "Rahul", 21, "HR", "Active", "Medium", true, "Normal");

        // Employee should be Eligible
        assertEquals("Eligible",App.checkEligibility(e));
    }

    // Test for an Employee below the Minimum Age
    @Test
    void testBelowMinimumAge() {

        // Employee is Only 20 Years Old
        App.Employee e=new App.Employee("E103", "Aman", 20, "Finance", "Active", "High", true, "Normal");

        // Employee should not be Eligible
        assertEquals("Not Eligible: Employee Must be At Least 21 Years Old", App.checkEligibility(e));
    }

    // Test for an Unauthorized Department
    @Test
    void testUnauthorizedDepartment() 
    {
        // Sales is not an Authorized Department
        App.Employee e=new App.Employee("E104", "Rohan", 30, "Sales", "Active", "High", true, "Normal");

        // Employee should not be Eligible
        assertEquals("Not Eligible: Unauthorized Department", App.checkEligibility(e));
    }

    // Test for Inactive Employment Status
    @Test
    void testInactiveEmployee() 
    {
        // Employee has Inactive Employment Status
        App.Employee e=new App.Employee("E105", "Karan", 25, "IT", "Inactive", "High", true, "Normal");

        // Employee should not be Eligible
        assertEquals("Not Eligible: Employment Status is Inactive", App.checkEligibility(e));
    }

    // Test for an Invalid Employee ID
    @Test
    void testInvalidEmployeeId() 
    {
        // Employee ID Validity set to False
        App.Employee e=new App.Employee("E106", "Neha", 25, "HR", "Active", "High", false, "Normal");

        // Employee should not be Eligible
        assertEquals("Not Eligible: Employee ID is Invalid", App.checkEligibility(e));
    }

    // Test for Conditional Eligibility
    @Test
    void testConditionalEligibility() {

        // Employee has Medium Clearance but Requests Confidential Access
        App.Employee e=new App.Employee("E107", "Priya", 28, "Administration", "Active", "Medium", true, "Confidential");

        // Employee should be Conditionally Eligible
        assertEquals("Conditionally Eligible", App.checkEligibility(e));
    }

    // Test for Multiple Eligibility Failures
    @Test
    void testMultipleFailures() 
    {
        // Employee Fails several Eligibility Conditions
        App.Employee e=new App.Employee("", "Vijay", 19, "Sales", "Inactive", "Low", false, "Confidential");

        // Store the Eligibility Result
        String result=App.checkEligibility(e);

        // Verify that all failure reasons are reported
        assertEquals(
                "Not Eligible: Invalid Employee ID, " +
                "Employee Must be At Least 21 Years Old, " +
                "Unauthorized Department, " +
                "Employment Status is Inactive, " +
                "Employee ID is Invalid, " +
                "Insufficient Security Clearance for Confidential Access",
                result);
    }

    // Test for a null employee ID
    @Test
    void testNullEmployeeId() 
    {
        // Create an Employee with a NULL ID
        App.Employee e=new App.Employee(null, "Test", 25, "IT", "Active", "High", false, "Normal");

        // Both NULL/Invalid ID Conditions must be Detected
        assertEquals("Not Eligible: Invalid Employee ID, Employee ID is Invalid", App.checkEligibility(e));
    }

    // Test for Multiple Individual Failures
    @Test
    void testInvalidDepartmentAndInactiveStatus() 
    {
        // Employee has Unauthorized Department and Inactive Status
        App.Employee e=new App.Employee("E109", "Test", 25, "Marketing", "Inactive","High",true,"Normal");

        // Both Failure Reasons should be Reported
        assertEquals("Not Eligible: Unauthorized Department, Employment Status is Inactive", App.checkEligibility(e));
    }
}
