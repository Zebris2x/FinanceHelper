package com.financehelper.observer;

import com.financehelper.model.MarginResult;

public class NegativeMarginObserver implements MarginObserver {

    @Override
    public void onMarginCalculated(MarginResult r) {
        if (r.getMargin() < 0)
            System.out.printf("[ALERT] Negative margin ($%.2f) — spending exceeds income!%n",
                r.getMargin());
    }
}
