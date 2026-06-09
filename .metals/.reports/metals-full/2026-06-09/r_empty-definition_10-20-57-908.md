error id: file://<HOME>/Desktop/SE%20350/FinanceHelper/src/main/java/com/financehelper/ui/ConsoleUI_usage.java:_empty_/FixedExpenseFactory#
file://<HOME>/Desktop/SE%20350/FinanceHelper/src/main/java/com/financehelper/ui/ConsoleUI_usage.java
empty definition using pc, found symbol in pc: _empty_/FixedExpenseFactory#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 635
uri: file://<HOME>/Desktop/SE%20350/FinanceHelper/src/main/java/com/financehelper/ui/ConsoleUI_usage.java
text:
```scala
package com.financehelper.ui;

// Replace the old static call:
//   expenses.add(ExpenseFactory.create(type, name, amount));
//
// With the Factory Method pattern:

import com.financehelper.factory.ExpenseFactory;
import com.financehelper.factory.FixedExpenseFactory;
import com.financehelper.factory.VariableExpenseFactory;
import com.financehelper.factory.RecurringExpenseFactory;

// Inside calculateAndSave(), replace the expense-collection block with:

System.out.print("  Type (F/V/R): ");
String type = scanner.nextLine().trim().toUpperCase();

ExpenseFactory factory = switch (type) {
    case "F", "FIXED"     -> new FixedExpe@@nseFactory();
    case "V", "VARIABLE"  -> new VariableExpenseFactory();
    case "R", "RECURRING" -> new RecurringExpenseFactory();
    default               -> new FixedExpenseFactory();
};

expenses.add(factory.create(name, amount));

// The caller now depends only on the ExpenseFactory interface.
// Adding a new expense type = add a new factory class, touch nothing else.

```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/FixedExpenseFactory#