error id: file://<HOME>/Desktop/SE%20350/FinanceHelper/src/main/java/com/financehelper/model/MarginResultBuilder.java:_empty_/Expense#
file://<HOME>/Desktop/SE%20350/FinanceHelper/src/main/java/com/financehelper/model/MarginResultBuilder.java
empty definition using pc, found symbol in pc: _empty_/Expense#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 841
uri: file://<HOME>/Desktop/SE%20350/FinanceHelper/src/main/java/com/financehelper/model/MarginResultBuilder.java
text:
```scala
package com.financehelper.model;

import java.util.List;

public class MarginResultBuilder {

    private final double income;
    private final double totalExpenses;
    private final double savingsGoal;
    private final double margin;
    private final double marginPercentage;
    private final List<Expense> expenses;

    private MarginResultBuilder(Builder builder) {
        this.income           = builder.income;
        this.totalExpenses    = builder.totalExpenses;
        this.savingsGoal      = builder.savingsGoal;
        this.margin           = builder.margin;
        this.marginPercentage = builder.marginPercentage;
        this.expenses         = builder.expenses;
    }

    public MarginResultBuilder(double income2, double totalExpenses2, double savingsGoal2, double margin2,
            double marginPct, List<Expen@@se> expenses2) {
                this.expenses = null;
        //TODO Auto-generated constructor stub
    }

    public double getIncome()           { return income; }
    public double getTotalExpenses()    { return totalExpenses; }
    public double getSavingsGoal()      { return savingsGoal; }
    public double getMargin()           { return margin; }
    public double getMarginPercentage() { return marginPercentage; }
    public List<Expense> getExpenses()  { return expenses; }

    public static class Builder {

        private double income;
        private double totalExpenses;
        private double savingsGoal;
        private double margin;
        private double marginPercentage;
        private List<Expense> expenses;

        public Builder income(double income) {
            this.income = income;
            return this;
        }
        public Builder totalExpenses(double totalExpenses) {
            this.totalExpenses = totalExpenses;
            return this;
        }
        public Builder savingsGoal(double savingsGoal) {
            this.savingsGoal = savingsGoal;
            return this;
        }
        public Builder margin(double margin) {
            this.margin = margin;
            return this;
        }
        public Builder marginPercentage(double marginPercentage) {
            this.marginPercentage = marginPercentage;
            return this;
        }
        public Builder expenses(List<Expense> expenses) {
            this.expenses = expenses;
            return this;
        }

        public MarginResultBuilder build() {
            return new MarginResultBuilder(this);
        }
    }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/Expense#