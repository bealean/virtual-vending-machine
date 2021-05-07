package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class GumTest {

    @Test
    public void test_constructor() {
        Gum testObject = new Gum("U-Chews", new BigDecimal("0.85"), "D1");
        String actual = testObject + testObject.getMessage();
        String expected = "D1\t0.85\t5\tU-ChewsChew Chew, Yum!";
        Assert.assertEquals(expected, actual);
    }
}
