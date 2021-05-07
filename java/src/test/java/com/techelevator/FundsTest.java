package com.techelevator;

import org.junit.Assert;
import org.junit.Test;
import java.math.BigDecimal;

public class FundsTest {

    @Test
    public void testAddGetFunds() {
        Funds testObject = new Funds();
        testObject.addFunds(new BigDecimal("10"));
        BigDecimal actual = testObject.getFunds();
        BigDecimal expected = new BigDecimal("10");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDecreaseFunds() {
        Funds testObject = new Funds();
        testObject.addFunds(new BigDecimal("10"));
        testObject.decreaseFunds(new BigDecimal("1.75"));
        BigDecimal actual = testObject.getFunds();
        BigDecimal expected = new BigDecimal("8.25");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void changeOneOfEach() {
        Funds testObject = new Funds();
        testObject.addFunds(new BigDecimal(".40"));
        String actual = testObject.change();
        String expected = "Here is your change:\n1 quarter\n1 dime\n1 nickel\n";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void changeTwoQuartersTwoDimes() {
        Funds testObject = new Funds();
        testObject.addFunds(new BigDecimal(".70"));
        String actual = testObject.change();
        String expected = "Here is your change:\n2 quarters\n2 dimes\n";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void changeNoChangeDue() {
        Funds testObject = new Funds();
        testObject.addFunds(new BigDecimal("0"));
        String actual = testObject.change();
        String expected = "No change due.\n";
        Assert.assertEquals(expected, actual);
    }
}