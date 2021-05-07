package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ListOfItemsTest {

    private static final String INPUT_FILE = "vendingmachine.csv";

    @Test
    public void testAddProduct() {
        ListOfItems testObject = new ListOfItems();
        Chip testChip = new Chip("Pringles", new BigDecimal("1.75"), "A1");
        testObject.addProducts(testChip);
        List<Product> testList = new ArrayList<>();
        testList.add(testChip);
        Assert.assertEquals(testList, testObject.getProducts());
    }

    @Test
    public void testPrintAllProducts() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        ListOfItems testObject = new ListOfItems();
        Chip testChip = new Chip("Pringles", new BigDecimal("1.75"), "A1");
        Chip testChip2 = new Chip("Ruffles", new BigDecimal("1.25"), "A2");
        testObject.addProducts(testChip);
        testObject.addProducts(testChip2);
        testObject.printAllProducts();

        String soutContent = outContent.toString();
        boolean hasExpected = false;
        if (soutContent.contains(testChip.toString() + "\r\n" + testChip2.toString() + "\r\n")) {
            hasExpected = true;
        }
        Assert.assertTrue(hasExpected);
        System.setOut(originalOut);
    }

    @Test
    public void testArrayOfProducts() {
        ListOfItems testObject = new ListOfItems();
        Chip testChip = new Chip("Pringles", new BigDecimal("1.75"), "A1");
        Chip testChip2 = new Chip("Ruffles", new BigDecimal("1.25"), "A2");
        testObject.addProducts(testChip);
        testObject.addProducts(testChip2);
        Product[] actualArray = testObject.arrayOfProducts();
        Product[] expectedArray = {testChip, testChip2};
        Assert.assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void testReadInItems() {
        ListOfItems testObject = new ListOfItems();
        testObject.readInItems(INPUT_FILE);
        Assert.assertTrue(testObject.getProducts().size() > 0);
    }

    @Test
    public void testReadInItemsInvalidFile() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        ListOfItems testObject = new ListOfItems();
        testObject.readInItems("Bad");

        String soutContent = outContent.toString();
        Assert.assertTrue(soutContent.contains("SOLD OUT"));
        System.setOut(originalOut);
    }
}