package com.financehelper.strategy;

import com.financehelper.model.Expense;
import java.util.List;

/**
 * PATTERN 1: Strategy
 * Defines the algorithm contract for calculating margin.
 */
public interface MarginStrategy {
    double calculate(double income, List<Expense> expenses, double savingsGoal);
}
