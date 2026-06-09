package com.financehelper.strategy;

import com.financehelper.model.Expense;
import java.util.List;

/** income − totalExpenses − savingsGoal */
public class StandardMarginStrategy implements MarginStrategy {
    @Override
    public double calculate(double income, List<Expense> expenses, double savingsGoal) {
        double totalExpenses = expenses.stream().mapToDouble(Expense::getAmount).sum();
        return income - totalExpenses - savingsGoal;
    }
}
