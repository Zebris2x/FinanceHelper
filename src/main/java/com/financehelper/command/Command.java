package com.financehelper.command;

/**
 * PATTERN 3: Command
 * Encapsulates a request as an object, enabling undo history.
 */
public interface Command {
    void execute();
    void undo();
}
