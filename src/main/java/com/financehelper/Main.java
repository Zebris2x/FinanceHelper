package com.financehelper;

import com.financehelper.observer.LowMarginObserver;
import com.financehelper.observer.NegativeMarginObserver;
import com.financehelper.observer.SummaryObserver;
import com.financehelper.service.MarginCalculatorService;
import com.financehelper.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        MarginCalculatorService calculatorService = new MarginCalculatorService();
        calculatorService.addObserver(new SummaryObserver());
        calculatorService.addObserver(new NegativeMarginObserver());
        calculatorService.addObserver(new LowMarginObserver());

        new ConsoleUI(calculatorService).start();
    }
}
