package com.techelevator;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditLog {

    private int newInstance;

    public AuditLog() {
        this.newInstance = 1;
    }

    public void addAuditEntry(String transaction, BigDecimal startingBalance, BigDecimal endingBalance, String logFile) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        File file = new File(logFile);
        PrintWriter printWriter = null;
        String auditEntry = currentDateTime.format(targetFormat) + " " + transaction + " $" +
                startingBalance.setScale(2, RoundingMode.UNNECESSARY) + " $" +
                endingBalance.setScale(2, RoundingMode.UNNECESSARY) + "\n";
        if (this.newInstance != 1) {
            try {FileOutputStream outputStream = new FileOutputStream(file, true);
                printWriter = new PrintWriter(outputStream);
            } catch (FileNotFoundException e) {
                System.out.println("Could not write to Audit Log");
            }
        } else {
            try { printWriter = new PrintWriter(file);
                this.newInstance = 0;
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        }
        if (printWriter != null) {
            printWriter.append(auditEntry);
            printWriter.flush();
            printWriter.close();
        }
    }
}
