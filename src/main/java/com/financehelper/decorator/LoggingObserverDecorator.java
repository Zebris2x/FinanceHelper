package com.financehelper.decorator;

import com.financehelper.model.MarginResult;
import com.financehelper.observer.MarginObserver;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * PATTERN 5: Decorator
 * Wraps any MarginObserver to add timestamped logging before/after delegation.
 * The wrapped observer still looks like a plain MarginObserver to the rest of the system.
 *
 * Usage:
 *   service.addObserver(new LoggingObserverDecorator(new SummaryObserver()));
 */
public class LoggingObserverDecorator implements MarginObserver {
    private final MarginObserver wrapped;
    private static final DateTimeFormatter FMT =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LoggingObserverDecorator(MarginObserver wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void onMarginCalculated(MarginResult result) {
        String timestamp = LocalDateTime.now().format(FMT);
        System.out.printf("[LOG %s] Notifying %s%n", timestamp, wrapped.getClass().getSimpleName());
        wrapped.onMarginCalculated(result);
        System.out.printf("[LOG %s] %s completed.%n", timestamp, wrapped.getClass().getSimpleName());
    }
}
