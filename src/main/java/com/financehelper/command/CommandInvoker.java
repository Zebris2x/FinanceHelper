package com.financehelper.command;

import java.util.ArrayDeque;
import java.util.Deque;

public class CommandInvoker {
    private final Deque<Command> history = new ArrayDeque<>();

    public void run(Command command) {
        command.execute();
        history.push(command);
    }

    public void undoLast() {
        if (history.isEmpty()) {
            System.out.println("[Invoker] Nothing to undo.");
            return;
        }
        history.pop().undo();
    }

    public int historySize() {
        return history.size();
    }
}
