package com.financehelper.service;

import com.financehelper.model.Expense;
import com.financehelper.model.MarginResult;
import com.financehelper.model.MarginResultBuilder;
import com.financehelper.observer.MarginObserver;
import com.financehelper.strategy.MarginStrategy;
import com.financehelper.strategy.StandardMarginStrategy;

import java.util.ArrayList;
import java.util.List;


 
// PATTERN 1 Strategy — delegates calculation to the active MarginStrategy.
// PATTERN 2 Observer — notifies all registered observers after each calculation.

public class MarginCalculatorService {
    private MarginStrategy strategy = new StandardMarginStrategy();
    private final List<MarginObserver> observers = new ArrayList<>();

    // ── Strategy ──────────────────────────────────────────────
    public void setStrategy(MarginStrategy strategy) {
        this.strategy = strategy;
    }

    // ── Observer ──────────────────────────────────────────────
    public void addObserver(MarginObserver observer)    { observers.add(observer); }
    public void removeObserver(MarginObserver observer) { observers.remove(observer); }

    // ── Core calculation ──────────────────────────────────────
    public MarginResult calculate(double income, List<Expense> expenses, double savingsGoal) {
        double margin = strategy.calculate(income, expenses, savingsGoal);
        double totalExpenses = expenses.stream().mapToDouble(Expense::getAmount).sum();
        double marginPct = income == 0 ? 0 : (margin / income) * 100;

        MarginResult result = new MarginResultBuilder()
            .income(income)
            .expenses(expenses)
            .savingsGoal(savingsGoal)
            .build();

        observers.forEach(o -> o.onMarginCalculated(result));
        return result;
    }
}
