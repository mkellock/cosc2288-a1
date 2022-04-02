package com.cosc2288.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * RestaurantMenuItem
 *
 * v1.0
 *
 * 2022-03-20
 *
 * © 2022 Matthew Kellock
 */

/** Series of RestaurantMenuItem tests. */
class RestaurantMenuItemTests {
    private static final String DESCRIPTION = "Description 1";
    private static final double PRICE = 123.45;

    @Test
    void shouldConstructAndRestaurantMenuItemValues() {
        RestaurantMenuItem restaurantMenuItem = new RestaurantMenuItem(
            DESCRIPTION,
            PRICE);
        Assertions.assertEquals(
            DESCRIPTION,
            restaurantMenuItem.getDescription()
        );
        Assertions.assertEquals(PRICE, restaurantMenuItem.getPrice());
    }
}
