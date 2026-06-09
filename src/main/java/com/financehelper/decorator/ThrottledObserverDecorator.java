package com.financehelper.decorator;

import com.financehelper.model.MarginResult;
import com.financehelper.observer.MarginObserver;

/**
 * PATTERN 5: Decorator (second example)
 * Suppresses repeated notifications within a cooldown window (milliseconds).
 *
 * Usage:
 *   service.addObserver(new ThrottledObserverDecorator(new LowMarginObserver(), 5000));
 */
public class ThrottledObserverDecorator implements MarginObserver {
    private final MarginObserver wrapped;
    private final long cooldownMs;
    private long lastFiredAt = 0;

    public ThrottledObserverDecorator(MarginObserver wrapped, long cooldownMs) {
        this.wrapped    = wrapped;
        this.cooldownMs = cooldownMs;
    }

    @Override
    public void onMarginCalculated(MarginResult result) {
        long now = System.currentTimeMillis();
        if (now - lastFiredAt >= cooldownMs) {
            wrapped.onMarginCalculated(result);
            lastFiredAt = now;
        } else {
            System.out.printf("[THROTTLED] %s suppressed (cooldown: %dms)%n",
                wrapped.getClass().getSimpleName(), cooldownMs);
        }
    }
}
