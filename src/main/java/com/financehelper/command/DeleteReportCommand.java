package com.financehelper.command;

import com.financehelper.model.MarginResult;
import com.financehelper.repository.ReportRepository;

public class DeleteReportCommand implements Command {
    private final ReportRepository repository;
    private final MarginResult report;

    public DeleteReportCommand(ReportRepository repository, MarginResult report) {
        this.repository = repository;
        this.report     = report;
    }

    @Override
    public void execute() {
        repository.delete(report);
        System.out.println("[Command] Report deleted.");
    }

    @Override
    public void undo() {
        repository.save(report);
        System.out.println("[Command] Delete undone -- report restored.");
    }
}
