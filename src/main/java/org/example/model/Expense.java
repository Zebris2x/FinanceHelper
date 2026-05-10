package org.example.model;

public class Expense {
    private String name;
    private double amount;
    private String category;

    public Expense(String name, double amount, String category) {
        this.name = name;
        this.amount = amount;
        this.category = category;
    }

    public String getName() { return name; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }

    @Override
    public String toString() {
        return String.format("Expense{name='%s', amount=%.2f, category='%s'}", name, amount, category);
    }
}
