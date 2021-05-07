package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ChipTest {

    @Test
    public void test_constructor() {
        Chip testObject = new Chip("Stackers", new BigDecimal("1.45"), "A2");
        String actual = testObject + testObject.getMessage();
        String expected = "A2\t1.45\t5\tStackersCrunch Crunch, Yum!";
        Assert.assertEquals(expected, actual);
    }

}