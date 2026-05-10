package org.example.patterns;

import com.margincalc.model.Expense;
import com.margincalc.model.MonthlyReport;
import com.margincalc.observer.ReportNotifier;
import com.margincalc.repository.ReportRepository;

import java.util.List;

public class ReportService {
    private final MarginCalculatorService calculator;
    private final ReportRepository repository;
    private final ReportNotifier notifier;

    public ReportService(MarginCalculatorService calculator,
                         ReportRepository repository,
                         ReportNotifier notifier) {
        this.calculator = calculator;
        this.repository = repository;
        this.notifier = notifier;
    }

    public MonthlyReport createReport(double income, List<Expense> expenses) {
        MonthlyReport report = new MonthlyReport();
        report.setIncome(income);
        report.setExpenses(expenses);

        double totalExpenses = report.calculateTotalExpenses();
        double margin = calculator.calculateMargin(income, totalExpenses);
        double marginPct = calculator.calculateMarginPercentage(income, margin);

        report.setMargin(margin);
        report.setMarginPercentage(marginPct);

        notifier.notifyObservers(report);
        return report;
    }

    public void saveReport(MonthlyReport report) {
        repository.save(report);
    }

    public List<MonthlyReport> getReports() {
        return repository.findAll();
    }
}
