package com.cosc2288.models;

import com.cosc2288.controllers.DiscountsController;
import java.util.Arrays;
import java.util.LinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * DiscountsTests
 *
 * v1.0
 *
 * 2022-03-20
 *
 * © 2022 Matthew Kellock
 */

/** Series of Discounts tests. */
public class DiscountsTests {
    private static final DiscountItem ITEM_1 = new DiscountItem(0, 20, 5);
    private static final DiscountItem ITEM_2 = new DiscountItem(20, 40, 10);
    private static final DiscountItem ITEM_3 = new DiscountItem(40, 60, 15);
    private static final DiscountItem ITEM_4 = new DiscountItem(60, 20);
    private static final LinkedList<DiscountItem> DISCOUNT_ITEMS =
        new LinkedList<DiscountItem>();
    private static final int DELIVERY_DISCOUNT_RESTAURANT_COUNT = 2;
    private static final int DELIVERY_DISCOUNT_RESTAURANT_PERCENTAGE = 50;

    @BeforeAll
    public static void init() {
        // Add the discount items to the constant discount item list
        DISCOUNT_ITEMS.addAll(Arrays.asList(ITEM_1, ITEM_2, ITEM_3, ITEM_4));
    }

    @Test
    void shouldConstructAndGetDiscountValues() {
        Discounts discounts = new Discounts();
        discounts.setDiscountItems(DISCOUNT_ITEMS);
        Assertions.assertEquals(DISCOUNT_ITEMS, discounts.getDiscountItems());
    }

    @Test
    void shouldConstructAndSetPlusGetDiscountValues() {
        Discounts discounts = new Discounts();
        discounts.setDiscountItems(DISCOUNT_ITEMS);
        Assertions.assertEquals(DISCOUNT_ITEMS, discounts.getDiscountItems());
    }

    @Test
    void shouldSetAndGetDeliveryDiscountRestaurantCount() {
        Discounts discounts = new Discounts();
        discounts.setDeliveryDiscountRestaurantCount(
            DELIVERY_DISCOUNT_RESTAURANT_COUNT
        );
        Assertions.assertEquals(DELIVERY_DISCOUNT_RESTAURANT_COUNT,
            discounts.getDeliveryDiscountRestaurantCount()
        );
    }

    @Test
    void shouldSetAndGetDeliveryDiscountRestaurantPercentage() {
        Discounts discounts = new Discounts();
        discounts.setDeliveryDiscountRestaurantPercent(
            DELIVERY_DISCOUNT_RESTAURANT_PERCENTAGE
        );
        Assertions.assertEquals(DELIVERY_DISCOUNT_RESTAURANT_PERCENTAGE,
            discounts.getDeliveryDiscountRestaurantPercent());
    }
}
