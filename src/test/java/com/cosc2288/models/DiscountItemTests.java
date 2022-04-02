package com.cosc2288.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * DiscountItemTests
 *
 * v1.0
 *
 * 2022-03-20
 *
 * © 2022 Matthew Kellock
 */

/** Series of DiscountItem tests. */
public class DiscountItemTests {
    private static final int MIN = 0;
    private static final int MAX = 10;
    private static final int PERCENT = 20;

    @Test
    public void shouldConstructAndGetDiscountItemValuesNoMax() {
        DiscountItem discountItem = new DiscountItem(MIN, PERCENT);
        Assertions.assertEquals(MIN, discountItem.getMin());
        Assertions.assertEquals(Integer.MAX_VALUE, discountItem.getMax());
        Assertions.assertEquals(PERCENT, discountItem.getPercent());
    }

    @Test
    public void shouldConstructAndGetDiscountItemValues() {
        DiscountItem discountItem = new DiscountItem(MIN, MAX, PERCENT);
        Assertions.assertEquals(MIN, discountItem.getMin());
        Assertions.assertEquals(MAX, discountItem.getMax());
        Assertions.assertEquals(PERCENT, discountItem.getPercent());
    }

}
