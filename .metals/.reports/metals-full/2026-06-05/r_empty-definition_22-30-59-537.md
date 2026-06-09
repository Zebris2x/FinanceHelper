error id: file://<HOME>/Desktop/SE%20350/FinanceHelper/src/main/java/com/financehelper/service/MarginCalculatorService.java:_empty_/MarginObserver#onMarginCalculated#
file://<HOME>/Desktop/SE%20350/FinanceHelper/src/main/java/com/financehelper/service/MarginCalculatorService.java
empty definition using pc, found symbol in pc: _empty_/MarginObserver#onMarginCalculated#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 1125
uri: file://<HOME>/Desktop/SE%20350/FinanceHelper/src/main/java/com/financehelper/service/MarginCalculatorService.java
text:
```scala
package com.financehelper.service;

import com.financehelper.model.Expense;
import com.financehelper.model.MarginResultBuilder;
import com.financehelper.observer.MarginObserver;

import java.util.ArrayList;
import java.util.List;

public class MarginCalculatorService {
    private final List<MarginObserver> observers = new ArrayList<>();

    public void addObserver(MarginObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(MarginObserver observer) {
        observers.remove(observer);
    }

    public MarginResultBuilder calculate(double income, List<Expense> expenses, double savingsGoal) {
        double totalExpenses = expenses.stream().mapToDouble(Expense::getAmount).sum();
        double margin        = income - totalExpenses - savingsGoal;
        double marginPct     = income == 0 ? 0 : (margin / income) * 100;

        MarginResultBuilder result = new MarginResultBuilder(income, totalExpenses, savingsGoal,
                                               margin, marginPct, expenses);
        for (MarginObserver observer : observers) {
            observer.onMargi@@nCalculated(result);
        }
        return result;
    }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/MarginObserver#onMarginCalculated#