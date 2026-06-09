package com.financehelper.factory;

import com.financehelper.model.Expense;

/**
 * PATTERN: Factory Method — Concrete Creator
 * Produces Fixed expenses. The category is encoded by the class itself,
 * so callers never need to pass a type string.
 */
public class FixedExpenseFactory implements ExpenseFactory {

    @Override
    public Expense create(String name, double amount) {
        return new Expense(name, amount, "Fixed");
    }
}
