package com.financehelper.command;

import com.financehelper.model.MarginResult;

public class ExportReportCommand implements Command {
    private final MarginResult report;
    private boolean exported = false;

    public ExportReportCommand(MarginResult report) {
        this.report = report;
    }

    @Override
    public void execute() {
        // In a real app this would write to a file or external system
        System.out.printf("[Command] Exported report: income=$%.2f, margin=%.1f%%%n",
            report.getIncome(), report.getMarginPercentage());
        exported = true;
    }

    @Override
    public void undo() {
        if (exported) {
            System.out.println("[Command] Export undone (file would be deleted).");
            exported = false;
        }
    }
}
