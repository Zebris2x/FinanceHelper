package org.example.patterns;

import com.margincalc.strategy.MarginStrategy;
import com.margincalc.strategy.StandardMarginStrategy;

public class MarginCalculatorService {
    private MarginStrategy strategy;

    public MarginCalculatorService() {
        this.strategy = new StandardMarginStrategy();
    }

    public void setStrategy(MarginStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculateMargin(double income, double expenses) {
        return strategy.calculate(income, expenses);
    }

    public double calculateMarginPercentage(double income, double margin) {
        if (income == 0) return 0;
        return (margin / income) * 100;
    }
}
