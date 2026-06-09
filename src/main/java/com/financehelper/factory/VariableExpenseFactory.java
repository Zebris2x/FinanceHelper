package com.financehelper.factory;

import com.financehelper.model.Expense;

/**
 * PATTERN: Factory Method — Concrete Creator
 * Produces Variable expenses. The category is encoded by the class itself,
 * so callers never need to pass a type string.
 */
public class VariableExpenseFactory implements ExpenseFactory {

    @Override
    public Expense create(String name, double amount) {
        return new Expense(name, amount, "Variable");
    }
}
