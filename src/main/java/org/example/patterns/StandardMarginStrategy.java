package org.example.patterns;

public class StandardMarginStrategy implements MarginStrategy {
    @Override
    public double calculate(double income, double expenses) {
        return income - expenses;
    }
}
