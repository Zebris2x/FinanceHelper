package com.financehelper.template;

import com.financehelper.model.Expense;
import com.financehelper.model.MarginResult;
import com.financehelper.model.MarginResultBuilder;

import java.util.List;

/**
 * PATTERN 6: Template Method
 * Defines the fixed skeleton: collectData → calculate → format → output.
 * Subclasses override only the steps they need to change.
 */
public abstract class ReportGenerator {

    /** Final template method — sequence is locked here. */
    public final void generate() {
        List<Expense> expenses = collectExpenses();
        double income          = collectIncome();
        double savingsGoal     = collectSavingsGoal();

        MarginResult result = new MarginResultBuilder()
            .income(income)
            .expenses(expenses)
            .savingsGoal(savingsGoal)
            .build();

        String formatted = format(result);
        output(formatted);
    }

    /** Step 1: gather expenses from the source. */
    protected abstract List<Expense> collectExpenses();

    /** Step 2: gather income from the source. */
    protected abstract double collectIncome();

    /** Step 3: gather savings goal (default 0 — subclasses may override). */
    protected double collectSavingsGoal() { return 0; }

    /** Step 4: format the result into a string. */
    protected abstract String format(MarginResult result);

    /** Step 5: output the formatted string (print, write file, etc.). */
    protected abstract void output(String formatted);
}
