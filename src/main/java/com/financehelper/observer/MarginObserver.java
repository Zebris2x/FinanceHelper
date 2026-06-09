package com.financehelper.observer;

import com.financehelper.model.MarginResult;

/**
 * PATTERN 4 (Observer): Receives notification after every margin calculation.
 */
public interface MarginObserver {
    void onMarginCalculated(MarginResult result);
}
