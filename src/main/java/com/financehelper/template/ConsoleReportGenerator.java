package com.financehelper.template;

import com.financehelper.factory.ExpenseFactory;
import com.financehelper.factory.FixedExpenseFactory;
import com.financehelper.factory.VariableExpenseFactory;
import com.financehelper.factory.RecurringExpenseFactory;
import com.financehelper.model.Expense;
import com.financehelper.model.MarginResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Reads from stdin, prints a human-readable summary to stdout. */
public class ConsoleReportGenerator extends ReportGenerator {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    protected double collectIncome() {
        System.out.print("Enter income: $");
        return Double.parseDouble(scanner.nextLine().trim());
    }

    @Override
    protected List<Expense> collectExpenses() {
        List<Expense> expenses = new ArrayList<>();

        System.out.println("Enter expenses (type 'done' to finish):");
        System.out.println("  Types: F=Fixed  V=Variable  R=Recurring");

        while (true) {
            System.out.print("  Name (or 'done'): ");
            String name = scanner.nextLine().trim();
            if (name.equalsIgnoreCase("done")) break;

            System.out.print("  Amount: $");
            double amount = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("  Type (F/V/R): ");
            String type = scanner.nextLine().trim().toUpperCase();

            // Factory Method pattern — resolve the factory through the interface,
            // then call create() without knowing the concrete type.
            ExpenseFactory factory = resolveFactory(type);
            if (factory == null) {
                System.out.println("  Invalid type. Please enter F, V, or R.");
                continue;
            }

            expenses.add(factory.create(name, amount));
        }

        return expenses;
    }

    /**
     * Resolves the correct ExpenseFactory for the given type code.
     * Returns null for unrecognised codes so the caller can prompt again.
     */
    private ExpenseFactory resolveFactory(String type) {
        return switch (type) {
            case "F", "FIXED"     -> new FixedExpenseFactory();
            case "V", "VARIABLE"  -> new VariableExpenseFactory();
            case "R", "RECURRING" -> new RecurringExpenseFactory();
            default               -> null;
        };
    }

    @Override
    protected double collectSavingsGoal() {
        System.out.print("Enter savings goal (0 if none): $");
        return Double.parseDouble(scanner.nextLine().trim());
    }

    @Override
    protected String format(MarginResult r) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n--- Report ---\n");
        sb.append(String.format("Income:          $%.2f%n", r.getIncome()));
        sb.append("Expenses:\n");
        r.getExpenses().forEach(e -> sb.append(e).append("\n"));
        sb.append(String.format("Total Expenses:  $%.2f%n", r.getTotalExpenses()));
        if (r.getSavingsGoal() > 0)
            sb.append(String.format("Savings Goal:    $%.2f%n", r.getSavingsGoal()));
        sb.append(String.format("Margin:          $%.2f%n", r.getMargin()));
        sb.append(String.format("Margin %%:        %.1f%%%n", r.getMarginPercentage()));
        return sb.toString();
    }

    @Override
    protected void output(String formatted) {
        System.out.print(formatted);
    }
}