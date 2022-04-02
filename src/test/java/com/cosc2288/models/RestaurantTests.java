package com.cosc2288.models;

import com.cosc2288.models.Restaurant.Category;
import java.util.Arrays;
import java.util.LinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * RestaurantTests
 *
 * v1.0
 *
 * 2022-03-20
 *
 * © 2022 Matthew Kellock
 */

/** Series of Restaurant tests. */
public class RestaurantTests {
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

    @BeforeAll
    public static void init() {
        // Add the menu items to the constant restaurant menu items
        MENU_ITEMS.addAll(Arrays.asList(ITEM1, ITEM2, ITEM3));
    }

    @Test
    public void shouldConstructAndGetMenuValues() {
        Restaurant restaurant =
            new Restaurant(NAME, CATEGORY, DELIVERY_FEE, MENU_ITEMS);
        Assertions.assertEquals(NAME, restaurant.getName());
        Assertions.assertEquals(CATEGORY, restaurant.getCategory());
        Assertions.assertEquals(DELIVERY_FEE, restaurant.getDeliveryFee());
        Assertions.assertEquals(MENU_ITEMS, 
            restaurant.getRestaurantMenuItems()
        );
    }

}
