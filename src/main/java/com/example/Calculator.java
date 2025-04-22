package com.example;

/**
 * A simple calculator class for demonstration purposes.
 */
public class Calculator {
    
    /**
     * Add two numbers.
     * 
     * @param a The first number
     * @param b The second number
     * @return The sum of a and b
     */
    public int add(int a, int b) {
        return a + b;
    }
    
    /**
     * Subtract two numbers.
     * 
     * @param a The first number
     * @param b The second number
     * @return The difference of a and b
     */
    public int subtract(int a, int b) {
        return a - b;
    }
    
    /**
     * Multiply two numbers.
     * 
     * @param a The first number
     * @param b The second number
     * @return The product of a and b
     */
    public int multiply(int a, int b) {
        return a * b;
    }
    
    /**
     * Divide two numbers.
     * 
     * @param a The first number
     * @param b The second number
     * @return The quotient of a and b
     * @throws IllegalArgumentException if b is zero
     */
    public double divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return (double) a / b;
    }
}