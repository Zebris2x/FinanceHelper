package com.financehelper.observer;

import com.financehelper.model.MarginResult;

public interface MarginObserver {
    void onMarginCalculated(MarginResult result);
}
