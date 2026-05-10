package org.example.patterns;

public class SavingsGoalMarginStrategy implements MarginStrategy {
    private double savingsGoal;

    public SavingsGoalMarginStrategy(double savingsGoal) {
        this.savingsGoal = savingsGoal;
    }

    @Override
    public double calculate(double income, double expenses) {
        double rawMargin = income - expenses;
        return rawMargin - savingsGoal;
    }

    public double getSavingsGoal() { return savingsGoal; }
    public void setSavingsGoal(double savingsGoal) { this.savingsGoal = savingsGoal; }
}
