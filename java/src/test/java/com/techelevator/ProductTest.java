package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ProductTest {

    @Test
    public void testGets() {
        Product testObject = new Chip("Test", new BigDecimal("1.00"), "A1");
        String actual = testObject.getName() + "_" + testObject.getPrice() + "_" + testObject.getLocation() + "_" +
                testObject.getQuantity() + "_" + testObject.getMessage();
        String expected = "Test_1.00_A1_5_Crunch Crunch, Yum!";
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testToStringForSoldOut() {
        Product testObject = new Chip("Test", new BigDecimal("1.00"), "A1");
        for (int i = 0; i < 5; i++) {
            testObject.decreasedQuantity();
        }
        String actual = testObject.toString();
        Assert.assertTrue(actual.contains("SOLD OUT"));
    }
    @Test
    public void testToStringForNotSoldOut() {
        Product testObject = new Chip("Test", new BigDecimal("1.00"), "A1");
        String actual = testObject.toString();
        String expected = "A1\t1.00\t5\tTest";
        Assert.assertEquals(expected,actual);
    }
}