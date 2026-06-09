package com.financehelper.strategy;

import com.financehelper.model.Expense;
import java.util.List;

/** Applies a tax rate to income before calculating margin. */
public class TaxAdjustedMarginStrategy implements MarginStrategy {
    private final double taxRate; // e.g. 0.20 = 20%

    public TaxAdjustedMarginStrategy(double taxRate) {
        if (taxRate < 0 || taxRate > 1) throw new IllegalArgumentException("Tax rate must be 0..1");
        this.taxRate = taxRate;
    }

    @Override
    public double calculate(double income, List<Expense> expenses, double savingsGoal) {
        double netIncome     = income * (1 - taxRate);
        double totalExpenses = expenses.stream().mapToDouble(Expense::getAmount).sum();
        return netIncome - totalExpenses - savingsGoal;
    }
}
