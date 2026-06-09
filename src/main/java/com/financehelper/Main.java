package com.financehelper;

import com.financehelper.decorator.LoggingObserverDecorator;
import com.financehelper.decorator.ThrottledObserverDecorator;
import com.financehelper.observer.*;
import com.financehelper.service.MarginCalculatorService;
import com.financehelper.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        MarginCalculatorService service = new MarginCalculatorService();

        // Observer pattern — wrap two observers with Decorator pattern
        service.addObserver(new LoggingObserverDecorator(new SummaryObserver()));
        service.addObserver(new ThrottledObserverDecorator(new LowMarginObserver(), 3000));
        service.addObserver(new NegativeMarginObserver());

        new ConsoleUI(service).start();
    }
}
