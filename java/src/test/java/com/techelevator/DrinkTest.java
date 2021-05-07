package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class DrinkTest {

    @Test
    public void test_constructor() {
        Drink testObject = new Drink("Cola", new BigDecimal("1.25"), "C1");
        String actual = testObject + testObject.getMessage();
        String expected = "C1\t1.25\t5\tColaGlug Glug, Yum!";
        Assert.assertEquals(expected, actual);
    }

}