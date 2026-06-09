package com.financehelper.model;

import java.util.ArrayList;
import java.util.List;

/**
 * PATTERN 2: Builder
 * Constructs MarginResult step-by-step with validation before build().
 */
public class MarginResultBuilder {
    private double income;
    private double savingsGoal;
    private List<Expense> expenses = new ArrayList<>();

    public MarginResultBuilder income(double income) {
        if (income < 0) throw new IllegalArgumentException("Income cannot be negative.");
        this.income = income;
        return this;
    }

    public MarginResultBuilder savingsGoal(double savingsGoal) {
        if (savingsGoal < 0) throw new IllegalArgumentException("Savings goal cannot be negative.");
        this.savingsGoal = savingsGoal;
        return this;
    }

    public MarginResultBuilder expenses(List<Expense> expenses) {
        this.expenses = new ArrayList<>(expenses);
        return this;
    }

    public MarginResult build() {
        double totalExpenses   = expenses.stream().mapToDouble(Expense::getAmount).sum();
        double margin          = income - totalExpenses - savingsGoal;
        double marginPct       = income == 0 ? 0 : (margin / income) * 100;
        return new MarginResult(income, totalExpenses, savingsGoal, margin, marginPct, expenses);
    }
}
