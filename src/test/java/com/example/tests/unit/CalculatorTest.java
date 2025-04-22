package com.example.tests.unit;

import com.example.Calculator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Unit tests for the Calculator class.
 */
public class CalculatorTest {
    
    private Calculator calculator;
    
    @BeforeMethod
    public void setUp() {
        calculator = new Calculator();
    }
    
    @Test(description = "Test addition of two positive numbers")
    public void testAddPositiveNumbers() {
        // Arrange
        int a = 5;
        int b = 3;
        
        // Act
        int result = calculator.add(a, b);
        
        // Assert
        Assert.assertEquals(result, 8, "Addition of 5 and 3 should be 8");
    }
    
    @Test(description = "Test addition of a positive and a negative number")
    public void testAddPositiveAndNegativeNumbers() {
        // Arrange
        int a = 5;
        int b = -3;
        
        // Act
        int result = calculator.add(a, b);
        
        // Assert
        Assert.assertEquals(result, 2, "Addition of 5 and -3 should be 2");
    }
    
    @Test(description = "Test subtraction of two positive numbers")
    public void testSubtractPositiveNumbers() {
        // Arrange
        int a = 5;
        int b = 3;
        
        // Act
        int result = calculator.subtract(a, b);
        
        // Assert
        Assert.assertEquals(result, 2, "Subtraction of 3 from 5 should be 2");
    }
    
    @Test(description = "Test multiplication of two positive numbers")
    public void testMultiplyPositiveNumbers() {
        // Arrange
        int a = 5;
        int b = 3;
        
        // Act
        int result = calculator.multiply(a, b);
        
        // Assert
        Assert.assertEquals(result, 15, "Multiplication of 5 and 3 should be 15");
    }
    
    @Test(description = "Test division of two positive numbers")
    public void testDividePositiveNumbers() {
        // Arrange
        int a = 6;
        int b = 3;
        
        // Act
        double result = calculator.divide(a, b);
        
        // Assert
        Assert.assertEquals(result, 2.0, "Division of 6 by 3 should be 2.0");
    }
    
    @Test(description = "Test division with decimal result")
    public void testDivideWithDecimalResult() {
        // Arrange
        int a = 5;
        int b = 2;
        
        // Act
        double result = calculator.divide(a, b);
        
        // Assert
        Assert.assertEquals(result, 2.5, "Division of 5 by 2 should be 2.5");
    }
    
    @Test(description = "Test division by zero", expectedExceptions = IllegalArgumentException.class)
    public void testDivideByZero() {
        // Arrange
        int a = 5;
        int b = 0;
        
        // Act & Assert
        calculator.divide(a, b); // Should throw IllegalArgumentException
    }
}