package com.financehelper.template;

import com.financehelper.model.Expense;
import com.financehelper.model.MarginResult;

import java.util.List;

/**
 * Pre-loaded with data — outputs CSV instead of a console summary.
 * Demonstrates how only format() and output() need to change.
 */
public class CsvReportGenerator extends ReportGenerator {
    private final double income;
    private final List<Expense> expenses;
    private final double savingsGoal;

    public CsvReportGenerator(double income, List<Expense> expenses, double savingsGoal) {
        this.income      = income;
        this.expenses    = expenses;
        this.savingsGoal = savingsGoal;
    }

    @Override
    protected double collectIncome()        { return income; }

    @Override
    protected List<Expense> collectExpenses() { return expenses; }

    @Override
    protected double collectSavingsGoal()   { return savingsGoal; }

    @Override
    protected String format(MarginResult r) {
        StringBuilder sb = new StringBuilder("income,totalExpenses,savingsGoal,margin,marginPct\n");
        sb.append(String.format("%.2f,%.2f,%.2f,%.2f,%.2f%n",
            r.getIncome(), r.getTotalExpenses(), r.getSavingsGoal(),
            r.getMargin(), r.getMarginPercentage()));
        return sb.toString();
    }

    @Override
    protected void output(String formatted) {
        System.out.print("[CSV Output]\n" + formatted);
    }
}
