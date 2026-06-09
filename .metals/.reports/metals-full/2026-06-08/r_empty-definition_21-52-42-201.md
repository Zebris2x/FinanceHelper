error id: file://<HOME>/Desktop/SE%20350/FinanceHelper/src/main/java/com/financehelper/command/DeleteReportCommand.java:com/financehelper/repository/ReportRepository#
file://<HOME>/Desktop/SE%20350/FinanceHelper/src/main/java/com/financehelper/command/DeleteReportCommand.java
empty definition using pc, found symbol in pc: com/financehelper/repository/ReportRepository#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 117
uri: file://<HOME>/Desktop/SE%20350/FinanceHelper/src/main/java/com/financehelper/command/DeleteReportCommand.java
text:
```scala
package com.financehelper.command;

import com.financehelper.model.MarginResult;
import com.financehelper.repository.@@ReportRepository;

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

```


#### Short summary: 

empty definition using pc, found symbol in pc: com/financehelper/repository/ReportRepository#