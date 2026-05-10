package com.financehelper.model;

import java.util.List;

public class MarginResult {
    private final double income;
    private final double totalExpenses;
    private final double savingsGoal;
    private final double margin;
    private final double marginPercentage;
    private final List<Expense> expenses;

    public MarginResult(double income, double totalExpenses, double savingsGoal,
                        double margin, double marginPercentage, List<Expense> expenses) {
        this.income           = income;
        this.totalExpenses    = totalExpenses;
        this.savingsGoal      = savingsGoal;
        this.margin           = margin;
        this.marginPercentage = marginPercentage;
        this.expenses         = expenses;
    }

    public double getIncome()           { return income; }
    public double getTotalExpenses()    { return totalExpenses; }
    public double getSavingsGoal()      { return savingsGoal; }
    public double getMargin()           { return margin; }
    public double getMarginPercentage() { return marginPercentage; }
    public List<Expense> getExpenses()  { return expenses; }
}
