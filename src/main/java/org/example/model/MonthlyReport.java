package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class MonthlyReport {
    private String month;
    private double income;
    private List<Expense> expenses;
    private double totalExpenses;
    private double margin;
    private double marginPercentage;

    public MonthlyReport() {
        this.expenses = new ArrayList<>();
    }

    public MonthlyReport(String month, double income, List<Expense> expenses) {
        this.month = month;
        this.income = income;
        this.expenses = new ArrayList<>(expenses);
        this.totalExpenses = calculateTotalExpenses();
    }

    public double calculateTotalExpenses() {
        totalExpenses = expenses.stream().mapToDouble(Expense::getAmount).sum();
        return totalExpenses;
    }

    public double getMargin() { return margin; }
    public double getMarginPercentage() { return marginPercentage; }
    public String getMonth() { return month; }
    public double getIncome() { return income; }
    public List<Expense> getExpenses() { return expenses; }
    public double getTotalExpenses() { return totalExpenses; }

    public void setMonth(String month) { this.month = month; }
    public void setIncome(double income) { this.income = income; }
    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
        this.totalExpenses = calculateTotalExpenses();
    }
    public void setMargin(double margin) { this.margin = margin; }
    public void setMarginPercentage(double marginPercentage) { this.marginPercentage = marginPercentage; }

    @Override
    public String toString() {
        return String.format(
            "MonthlyReport{month='%s', income=%.2f, totalExpenses=%.2f, margin=%.2f, marginPct=%.1f%%}",
            month, income, totalExpenses, margin, marginPercentage
        );
    }
}
