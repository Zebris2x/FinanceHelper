package org.example.patterns;

import com.margincalc.model.Expense;
import com.margincalc.model.MonthlyReport;
import java.util.ArrayList;
import java.util.List;

public class ReportBuilder {
    private String month;
    private double income;
    private final List<Expense> expenses = new ArrayList<>();

    public ReportBuilder setMonth(String month) {
        this.month = month;
        return this;
    }

    public ReportBuilder setIncome(double income) {
        this.income = income;
        return this;
    }

    public ReportBuilder addExpense(Expense expense) {
        this.expenses.add(expense);
        return this;
    }

    public MonthlyReport build() {
        if (month == null || month.isBlank()) {
            throw new IllegalStateException("Month must be set before building a report.");
        }
        return new MonthlyReport(month, income, expenses);
    }
}
