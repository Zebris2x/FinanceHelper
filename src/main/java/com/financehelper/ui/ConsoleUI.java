package com.financehelper.ui;

import com.financehelper.command.*;
import com.financehelper.factory.ExpenseFactory;
import com.financehelper.factory.FixedExpenseFactory;
import com.financehelper.factory.VariableExpenseFactory;
import com.financehelper.factory.RecurringExpenseFactory;
import com.financehelper.model.Expense;
import com.financehelper.model.MarginResult;
import com.financehelper.repository.ReportRepository;
import com.financehelper.service.MarginCalculatorService;
import com.financehelper.strategy.*;
import com.financehelper.template.CsvReportGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final MarginCalculatorService calculatorService;
    private final ReportRepository repository = ReportRepository.getInstance();
    private final CommandInvoker invoker       = new CommandInvoker();
    private final Scanner scanner              = new Scanner(System.in);

    public ConsoleUI(MarginCalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    public void start() {
        System.out.println("=== FinanceHelper ===");
        boolean running = true;
        while (running) {
            System.out.println("\n1. Calculate margin");
            System.out.println("2. View saved reports");
            System.out.println("3. Undo last action");
            System.out.println("4. Switch strategy");
            System.out.println("5. Export last report as CSV");
            System.out.println("6. Exit");
            System.out.print("Choose: ");
            switch (scanner.nextLine().trim()) {
                case "1" -> calculateAndSave();
                case "2" -> listReports();
                case "3" -> invoker.undoLast();
                case "4" -> switchStrategy();
                case "5" -> exportLast();
                case "6" -> running = false;
                default  -> System.out.println("Invalid option.");
            }
        }
    }

    private void calculateAndSave() {
        System.out.print("Income: $");
        double income = Double.parseDouble(scanner.nextLine().trim());

        List<Expense> expenses = new ArrayList<>();
        System.out.println("Expenses (F=Fixed  V=Variable  R=Recurring, 'done' to finish):");
        while (true) {
            System.out.print("  Name: ");
            String name = scanner.nextLine().trim();
            if (name.equalsIgnoreCase("done")) break;

            System.out.print("  Amount: $");
            double amount = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("  Type (F/V/R): ");
            String type = scanner.nextLine().trim().toUpperCase();

            // Factory Method pattern — caller picks the factory by type,
            // then depends only on the ExpenseFactory interface from here on.
            ExpenseFactory factory = resolveFactory(type);
            expenses.add(factory.create(name, amount));
        }

        System.out.print("Savings goal (0 if none): $");
        double goal = Double.parseDouble(scanner.nextLine().trim());

        MarginResult result = calculatorService.calculate(income, expenses, goal);
        System.out.println("\n=== Margin Report ===");
        System.out.printf("Income:   $%.2f%n", result.getIncome());
        System.out.printf("Margin:   $%.2f%n", result.getMargin());
        System.out.printf("Margin %%: %.2f%%%n", result.getMarginPercentage());

        System.out.print("\nSave this report? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            invoker.run(new SaveReportCommand(repository, result));
            System.out.println("Report saved.");
        }
    }

    /**
     * Resolves the correct ExpenseFactory for the given type code.
     * Adding a new expense type only requires a new factory class and a new case here.
     */
    private ExpenseFactory resolveFactory(String type) {
        return switch (type) {
            case "V", "VARIABLE"  -> new VariableExpenseFactory();
            case "R", "RECURRING" -> new RecurringExpenseFactory();
            default               -> new FixedExpenseFactory();
        };
    }

    private void listReports() {
        List<MarginResult> reports = repository.findAll();
        if (reports.isEmpty()) { System.out.println("No reports saved."); return; }
        for (int i = 0; i < reports.size(); i++) {
            MarginResult r = reports.get(i);
            System.out.printf("%d. Income=$%.2f  Margin=$%.2f (%.1f%%)%n",
                i + 1, r.getIncome(), r.getMargin(), r.getMarginPercentage());
        }
    }

    private void switchStrategy() {
        System.out.println("  1. Standard  2. Savings Goal  3. Tax Adjusted");
        System.out.print("Choose: ");
        switch (scanner.nextLine().trim()) {
            case "2" -> {
                System.out.print("  Savings goal: $");
                double g = Double.parseDouble(scanner.nextLine().trim());
                calculatorService.setStrategy(new SavingsGoalMarginStrategy(g));
                System.out.println("  Strategy: SavingsGoal");
            }
            case "3" -> {
                System.out.print("  Tax rate (e.g. 0.20): ");
                double t = Double.parseDouble(scanner.nextLine().trim());
                calculatorService.setStrategy(new TaxAdjustedMarginStrategy(t));
                System.out.println("  Strategy: TaxAdjusted");
            }
            default -> {
                calculatorService.setStrategy(new StandardMarginStrategy());
                System.out.println("  Strategy: Standard");
            }
        }
    }

    private void exportLast() {
        List<MarginResult> reports = repository.findAll();
        if (reports.isEmpty()) { System.out.println("No reports to export."); return; }
        MarginResult last = reports.get(reports.size() - 1);
        invoker.run(new ExportReportCommand(last));
        new CsvReportGenerator(last.getIncome(), last.getExpenses(), last.getSavingsGoal()).generate();
    }
}