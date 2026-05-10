package org.example.patterns;

import org.example.model.Expense;
public class ExpenseFactory {
    public static final String TYPE_FIXED = "FIXED";
    public static final String TYPE_VARIABLE = "VARIABLE";
    public static final String TYPE_RECURRING = "RECURRING";

    public Expense createExpense(String type, String name, double amount) {
        String category = switch (type.toUpperCase()) {
            case TYPE_FIXED -> "Fixed";
            case TYPE_VARIABLE -> "Variable";
            case TYPE_RECURRING -> "Recurring";
            default -> "Other";
        };
        return new Expense(name, amount, category);
    }
}
