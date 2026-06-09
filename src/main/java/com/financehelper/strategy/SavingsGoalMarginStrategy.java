package com.financehelper.strategy;

import com.financehelper.model.Expense;
import java.util.List;

/** Deducts a fixed savings goal on top of expenses. */
public class SavingsGoalMarginStrategy implements MarginStrategy {
    private final double savingsGoal;

    public SavingsGoalMarginStrategy(double savingsGoal) {
        this.savingsGoal = savingsGoal;
    }

    @Override
    public double calculate(double income, List<Expense> expenses, double savingsGoal) {
        double totalExpenses = expenses.stream().mapToDouble(Expense::getAmount).sum();
        return income - totalExpenses - this.savingsGoal;
    }
}
