package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class CandyTest {

    @Test
    public void test_constructor() {
        Candy testObject = new Candy("Crunchie", new BigDecimal("1.75"), "B4");
        String actual = testObject + testObject.getMessage();
        String expected = "B4\t1.75\t5\tCrunchieMunch Munch, Yum!";
        Assert.assertEquals(expected, actual);
    }

}