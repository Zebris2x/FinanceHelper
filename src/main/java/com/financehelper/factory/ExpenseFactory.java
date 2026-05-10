package com.financehelper.factory;

import com.financehelper.model.Expense;

public class ExpenseFactory {

    public static Expense create(String type, String name, double amount) {
        String category = switch (type.toUpperCase()) {
            case "F", "FIXED"     -> "Fixed";
            case "V", "VARIABLE"  -> "Variable";
            case "R", "RECURRING" -> "Recurring";
            default               -> "Other";
        };
        return new Expense(name, amount, category);
    }
}
