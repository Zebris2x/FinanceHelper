package com.financehelper.observer;

import com.financehelper.model.MarginResult;

public class LowMarginObserver implements MarginObserver {
    private static final double THRESHOLD = 10.0;

    @Override
    public void onMarginCalculated(MarginResult r) {
        if (r.getMarginPercentage() < THRESHOLD)
            System.out.printf("%n[ALERT] Margin %.1f%% is below %.0f%% — review your expenses.%n",
                r.getMarginPercentage(), THRESHOLD);
    }
}
