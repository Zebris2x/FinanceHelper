package com.financehelper.observer;

import com.financehelper.model.MarginResult;

public class SummaryObserver implements MarginObserver {

    @Override
    public void onMarginCalculated(MarginResult r) {
        System.out.println("\n--- Results ---");
        System.out.printf("Income:          $%.2f%n", r.getIncome());
        System.out.println("Expenses:");
        r.getExpenses().forEach(System.out::println);
        System.out.printf("Total Expenses:  $%.2f%n", r.getTotalExpenses());
        if (r.getSavingsGoal() > 0)
            System.out.printf("Savings Goal:    $%.2f%n", r.getSavingsGoal());
        System.out.printf("Margin:          $%.2f%n", r.getMargin());
        System.out.printf("Margin %%:        %.1f%%%n", r.getMarginPercentage());
    }
}
