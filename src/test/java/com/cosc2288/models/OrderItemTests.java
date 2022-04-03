package com.cosc2288.models;

/**
 * OrderItemTests
 *
 * v1.0
 *
 * 2022-04-02
 *
 * © 2022 Matthew Kellock
 */

import com.cosc2288.models.Restaurant;
import com.cosc2288.models.Restaurant.Category;
import java.util.Arrays;
import java.util.LinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/** Series of OrderItemTests tests. */
class OrderItemTests {
    private static final String NAME = "Some Restaurant Name";
    private static final Category CATEGORY = Category.RESTAURANT;
    private static final Double DELIVERY_FEE = 5.50;
    private static final RestaurantMenuItem ITEM1 =
        new RestaurantMenuItem("Description 1", 1.00);
    private static final RestaurantMenuItem ITEM2 =
        new RestaurantMenuItem("Description 2", 2.00);
    private static final RestaurantMenuItem ITEM3 =
        new RestaurantMenuItem("Description 3", 3.00);
    private static final LinkedList<RestaurantMenuItem> MENU_ITEMS =
        new LinkedList<RestaurantMenuItem>();
    private static final Restaurant RESTAURANT =
            new Restaurant(NAME, CATEGORY, DELIVERY_FEE, MENU_ITEMS);
    private static Integer QUANTITY = 1;

    @BeforeAll
    static void init() {
        // Add the restaurant menu items to the constant menu item list
        MENU_ITEMS.addAll(
            Arrays.asList(ITEM1, ITEM2, ITEM3)
        );
    }

    @Test
    void shouldConstructAndSetPlusGetOrderItemValues() {
        // Create a new menu item
        OrderItem orderItem = new OrderItem(RESTAURANT, ITEM1, QUANTITY);

        // Assert that the restaurant is returned correctly
        Assertions.assertEquals(RESTAURANT, orderItem.getRestaurant());

        // Assert that the restaurant menu item is returned correctly
        Assertions.assertEquals(ITEM1, orderItem.getRestaurantMenuItem());

        // Assert that the quantity is correct
        Assertions.assertEquals(QUANTITY, orderItem.getQuantity());
    }
}
