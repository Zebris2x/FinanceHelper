package org.example.model;

public class Income {
    private String source;
    private double amount;

    public Income(String source, double amount) {
        this.source = source;
        this.amount = amount;
    }

    public String getSource() { return source; }
    public double getAmount() { return amount; }

    @Override
    public String toString() {
        return String.format("Income{source='%s', amount=%.2f}", source, amount);
    }
}
