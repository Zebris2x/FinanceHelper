package com.financehelper.service;

import com.financehelper.model.Expense;
import com.financehelper.model.MarginResult;
import com.financehelper.observer.MarginObserver;

import java.util.ArrayList;
import java.util.List;

public class MarginCalculatorService {
    private final List<MarginObserver> observers = new ArrayList<>();

    public void addObserver(MarginObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(MarginObserver observer) {
        observers.remove(observer);
    }

    public MarginResult calculate(double income, List<Expense> expenses, double savingsGoal) {
        double totalExpenses = expenses.stream().mapToDouble(Expense::getAmount).sum();
        double margin        = income - totalExpenses - savingsGoal;
        double marginPct     = income == 0 ? 0 : (margin / income) * 100;

        MarginResult result = new MarginResult(income, totalExpenses, savingsGoal,
                                               margin, marginPct, expenses);
        for (MarginObserver observer : observers) {
            observer.onMarginCalculated(result);
        }
        return result;
    }
}
