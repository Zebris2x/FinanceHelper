package com.financehelper.repository;

import com.financehelper.model.Expense;
import com.financehelper.model.MarginResult;
import com.financehelper.model.MarginResultBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * PATTERN 4: Singleton
 * One shared in-memory store for all MarginResult records.
 * All parts of the app read/write to the same instance.
 */
public class ReportRepository {
    private static ReportRepository instance;
    private final List<MarginResult> reports = new ArrayList<>();
    // Storage path is configurable via system property 'financehelper.storage'
    // or environment variable 'FINANCEHELPER_STORAGE'. Defaults to ~/.financehelper/reports.json
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private ReportRepository() {}

    public static ReportRepository getInstance() {
        if (instance == null) {
            instance = new ReportRepository();
            instance.loadFromDisk();
        }
        return instance;
    }

    public void save(MarginResult report) {
        reports.add(report);
        persistToDisk();
    }

    public void delete(MarginResult report) {
        reports.remove(report);
        persistToDisk();
    }

    public List<MarginResult> findAll() {
        return Collections.unmodifiableList(reports);
    }

    public int count() {
        return reports.size();
    }

    private void loadFromDisk() {
        Path storage = getStoragePath();
        if (!Files.exists(storage)) return;
        try (Reader r = Files.newBufferedReader(storage)) {
            JsonArray arr = gson.fromJson(r, JsonArray.class);
            if (arr == null) return;
            for (JsonElement el : arr) {
                try {
                    JsonObject obj = el.getAsJsonObject();
                    double income = obj.has("income") ? obj.get("income").getAsDouble() : 0.0;
                    double savingsGoal = obj.has("savingsGoal") ? obj.get("savingsGoal").getAsDouble() : 0.0;
                    List<Expense> expenses = new ArrayList<>();
                    if (obj.has("expenses") && obj.get("expenses").isJsonArray()) {
                        for (JsonElement e : obj.getAsJsonArray("expenses")) {
                            JsonObject eo = e.getAsJsonObject();
                            String name = eo.has("name") ? eo.get("name").getAsString() : "";
                            double amount = eo.has("amount") ? eo.get("amount").getAsDouble() : 0.0;
                            String category = eo.has("category") ? eo.get("category").getAsString() : "";
                            expenses.add(new Expense(name, amount, category));
                        }
                    }
                    MarginResult mr = new MarginResultBuilder()
                        .income(income)
                        .savingsGoal(savingsGoal)
                        .expenses(expenses)
                        .build();
                    reports.add(mr);
                } catch (JsonParseException jpe) {
                    jpe.printStackTrace();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void persistToDisk() {
        JsonArray arr = new JsonArray();
        for (MarginResult r : reports) {
            JsonObject o = new JsonObject();
            o.addProperty("income", r.getIncome());
            o.addProperty("savingsGoal", r.getSavingsGoal());
            o.addProperty("totalExpenses", r.getTotalExpenses());
            o.addProperty("margin", r.getMargin());
            o.addProperty("marginPercentage", r.getMarginPercentage());
            JsonArray expArr = new JsonArray();
            if (r.getExpenses() != null) {
                for (Expense e : r.getExpenses()) {
                    JsonObject eo = new JsonObject();
                    eo.addProperty("name", e.getName());
                    eo.addProperty("amount", e.getAmount());
                    eo.addProperty("category", e.getCategory());
                    expArr.add(eo);
                }
            }
            o.add("expenses", expArr);
            arr.add(o);
        }
        Path storage = getStoragePath();
        try {
            Files.createDirectories(storage.getParent());
        } catch (IOException e) {
            // ignore - will fail when writing if necessary
        }
        try (Writer w = Files.newBufferedWriter(storage)) {
            gson.toJson(arr, w);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private Path getStoragePath() {
        String prop = System.getProperty("financehelper.storage");
        if (prop == null || prop.isBlank()) prop = System.getenv("FINANCEHELPER_STORAGE");
        if (prop != null && !prop.isBlank()) return Paths.get(prop);
        String userHome = System.getProperty("user.home");
        Path dir = Paths.get(userHome, ".financehelper");
        return dir.resolve("reports.json");
    }
}
