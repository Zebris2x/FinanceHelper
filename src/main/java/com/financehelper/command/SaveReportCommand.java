package com.financehelper.command;

import com.financehelper.model.MarginResult;
import com.financehelper.repository.ReportRepository;

public class SaveReportCommand implements Command {
    private final ReportRepository repository;
    private final MarginResult report;

    public SaveReportCommand(ReportRepository repository, MarginResult report) {
        this.repository = repository;
        this.report     = report;
    }

    @Override
    public void execute() {
        repository.save(report);
        System.out.println("[Command] Report saved.");
    }

    @Override
    public void undo() {
        repository.delete(report);
        System.out.println("[Command] Save undone — report removed.");
    }
}
