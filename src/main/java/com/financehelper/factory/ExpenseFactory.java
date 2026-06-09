package com.financehelper.factory;

import com.financehelper.model.Expense;

/**
 * PATTERN: Factory Method
 * Abstract creator — declares the factory method that subclasses must implement.
 * Callers depend only on this interface, never on a concrete factory.
 */
public interface ExpenseFactory {
    Expense create(String name, double amount);
}
