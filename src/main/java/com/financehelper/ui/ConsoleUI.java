package com.financehelper.ui;

import com.financehelper.factory.ExpenseFactory;
import com.financehelper.model.Expense;
import com.financehelper.service.MarginCalculatorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final MarginCalculatorService calculatorService;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleUI(MarginCalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    public void start() {
        System.out.println("=== FinanceHelper — Margin Calculator ===\n");

        double income = promptDouble("Enter income: $");

        List<Expense> expenses = new ArrayList<>();
        System.out.println("\nEnter expenses (type 'done' when finished):");
        System.out.println("  Types: F=Fixed  V=Variable  R=Recurring");

        while (true) {
            System.out.print("  Name (or 'done'): ");
            String name = scanner.nextLine().trim();
            if (name.equalsIgnoreCase("done")) break;

            double amount = promptDouble("  Amount: $");

            System.out.print("  Type (F/V/R): ");
            String type = scanner.nextLine().trim();

            expenses.add(ExpenseFactory.create(type, name, amount));
        }

        double savingsGoal = promptDouble("\nEnter savings goal (0 if none): $");

        calculatorService.calculate(income, expenses, savingsGoal);

        scanner.close();
    }

    private double promptDouble(String prompt) {
        System.out.print(prompt);
        return Double.parseDouble(scanner.nextLine().trim());
    }
}
