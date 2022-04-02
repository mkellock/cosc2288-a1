package com.cosc2288.controllers;

import com.cosc2288.TestHelpers;
import com.cosc2288.models.DiscountItem;
import com.cosc2288.models.Discounts;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * DiscountsControllerTests
 *
 * v1.0
 *
 * 2022-03-20
 *
 * © 2022 Matthew Kellock
 */

/** Series of DiscountsController tests. */
public class DiscountsControllerTests {

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

    /**
     * Tests that discounts are successfully sorted. Sort order is by min,
     * max and then percentage.
     *
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    void shouldSortDiscounts()
            throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        // Set up the discount items for the test
        LinkedList<DiscountItem> discountItems = new LinkedList<DiscountItem>();

        // Add the discount items
        discountItems.addAll(Arrays.asList(ITEM_4, ITEM_1, ITEM_2, ITEM_3));

        // Create a new instance of the discounts object and discounts
        // controller
        Discounts discounts = new Discounts();
        discounts.setDiscountItems(discountItems);
        DiscountsController discountsController = new DiscountsController(
            discounts
        );

        // Retrieve the private method sortDiscounts and make accessible
        Method sortDiscounts = TestHelpers.returnPrivateMethod(
            DiscountsController.class,
            "sortDiscounts"
        );
        sortDiscounts.setAccessible(true);
        sortDiscounts.invoke(discountsController);

        // Assert that the list is now ordered correctly
        Assertions.assertEquals(Arrays.asList(
            ITEM_1, ITEM_2, ITEM_3, ITEM_4
            ),
            discounts.getDiscountItems()
        );
    }

    @Test
    void shouldLoadDiscounts() throws java.io.IOException {
        final String sampleDiscounts =
            "[0,20),5%\n[20,40),10%\n[40,60),15%\n[60,),20%\n2,50%";
        final Reader sampleDiscountsReader = new StringReader(sampleDiscounts);
        final BufferedReader sampleDiscountsBufferedReader =
            new BufferedReader(sampleDiscountsReader);

        LinkedList<DiscountItem> discountItems = new LinkedList<DiscountItem>();
        Discounts discounts = new Discounts();
        discounts.setDiscountItems(discountItems);
        DiscountsController discountsController =
            new DiscountsController(discounts);
        discountsController.loadDiscounts(sampleDiscountsBufferedReader);

        // We are converting to JSON here to compare values and not the object
        // instances themselves
        Assertions.assertEquals(
            new Gson().toJson(DISCOUNT_ITEMS),
            new Gson().toJson(discounts.getDiscountItems())
        );
        Assertions.assertEquals(
            DELIVERY_DISCOUNT_RESTAURANT_COUNT,
            discounts.getDeliveryDiscountRestaurantCount()
        );
        Assertions.assertEquals(
            DELIVERY_DISCOUNT_RESTAURANT_PERCENTAGE,
            discounts.getDeliveryDiscountRestaurantPercent()
        );
    }
}
