package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Scanner;

public class AuditLogTest {
    private static final String AUDIT_LOG_FILE = "Log.txt";

    @Test
    public void testAddAuditEntryNotAppendedForNewInstance() {
        AuditLog testObject = new AuditLog();
        testObject.addAuditEntry("test", new BigDecimal("5"), new BigDecimal("2.50"), AUDIT_LOG_FILE);
        File auditFile = new File("Log.txt");

        int lines = 0;
        try (Scanner readFile = new Scanner(auditFile)) {

            while (readFile.hasNextLine()) {
                readFile.nextLine();
                lines++;
            }
        } catch (FileNotFoundException e) {
            Assert.fail("File not found.");
        }
        Assert.assertEquals(1, lines);
    }

    @Test
    public void testAddAuditEntryAppendedForExistingInstance() {
        AuditLog testObject = new AuditLog();
        testObject.addAuditEntry("test", new BigDecimal("5"), new BigDecimal("2.50"), AUDIT_LOG_FILE);
        testObject.addAuditEntry("test2", new BigDecimal("6"), new BigDecimal("3.50"), AUDIT_LOG_FILE);
        File auditFile = new File("Log.txt");

        int lines = 0;
        try (Scanner readFile = new Scanner(auditFile)) {

            while (readFile.hasNextLine()) {
                readFile.nextLine();
                lines++;
            }
        } catch (FileNotFoundException e) {
            Assert.fail("File not found.");
        }
        Assert.assertEquals(2, lines);
    }

    @Test
    public void testAddAuditEntryContent() {
        AuditLog testObject = new AuditLog();
        testObject.addAuditEntry("test", new BigDecimal("5"), new BigDecimal("2.50"), AUDIT_LOG_FILE);
        File auditFile = new File("Log.txt");
        String actualContent = "";
        try (Scanner readFile = new Scanner(auditFile)) {
            while (readFile.hasNextLine()) {
                actualContent = readFile.nextLine();
            }
        } catch (FileNotFoundException e) {
            Assert.fail("File not found.");
        }
        String expectedContent = "test $5.00 $2.50";
        Assert.assertTrue(actualContent.contains(expectedContent));
    }

    @Test
    public void testAddAuditEntryFileNotFoundNewInstance() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        AuditLog testObject = new AuditLog();
        testObject.addAuditEntry("test", new BigDecimal("5"), new BigDecimal("2.50"), "");
        boolean hasExpected = false;
        String soutContent = outContent.toString();
        if (soutContent.contains("File not found")) {
            hasExpected = true;
        }
        Assert.assertTrue(hasExpected);

        System.setOut(originalOut);
    }

    @Test
    public void testAddAuditEntryFileNotFoundExistingInstance() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        AuditLog testObject = new AuditLog();
        testObject.addAuditEntry("test", new BigDecimal("5"), new BigDecimal("2.50"), AUDIT_LOG_FILE);
        testObject.addAuditEntry("test2", new BigDecimal("6"), new BigDecimal("3.50"), "");
        boolean hasExpected = false;
        String soutContent = outContent.toString();
        if (soutContent.contains("Could not write to Audit Log")) {
            hasExpected = true;
        }
        Assert.assertTrue(hasExpected);

        System.setOut(originalOut);
    }
}