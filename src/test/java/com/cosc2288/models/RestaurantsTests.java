package com.cosc2288.models;

import com.cosc2288.models.Restaurant;
import com.cosc2288.models.Restaurant.Category;
import com.cosc2288.models.Restaurants;
import java.util.Arrays;
import java.util.LinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class RestaurantsTests {
    private static final Restaurant RESTAURANT_1 = 
        new Restaurant("Restaurant 1", Category.FAST_FOOD, 1.10, 
            new LinkedList<RestaurantMenuItem>()
        );
    private static final Restaurant RESTAURANT_2 = 
        new Restaurant("Restaurant 2", Category.RESTAURANT, 2.20, 
            new LinkedList<RestaurantMenuItem>()
        );
    private static final Restaurant RESTAURANT_3 = 
        new Restaurant("Restaurant 3", Category.CAFE, 3.30, 
            new LinkedList<RestaurantMenuItem>()
        );
    private static final LinkedList<Restaurant> RESTAURANTS =
        new LinkedList<Restaurant>();

    @BeforeAll
    static void init() {
        // Add the discount items to the constant discount item list
        RESTAURANTS.addAll(
            Arrays.asList(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3)
        );
    }

    @Test
    void shouldConstructAndSetAndGetRestaurants() {
        Restaurants restaurantList = new Restaurants();
        restaurantList.setRestaurants(RESTAURANTS);
        Assertions.assertEquals(RESTAURANTS, restaurantList.getRestaurants());
    }
}
