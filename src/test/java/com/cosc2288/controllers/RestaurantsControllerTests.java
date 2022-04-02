package com.cosc2288.controllers;

import com.cosc2288.TestHelpers;
import com.cosc2288.controllers.RestaurantsController;
import com.cosc2288.models.DiscountItem;
import com.cosc2288.models.Discounts;
import com.cosc2288.models.Restaurant;
import com.cosc2288.models.Restaurant.Category;
import com.cosc2288.models.RestaurantMenuItem;
import com.cosc2288.models.Restaurants;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * RestaurantsControllerTests
 *
 * v1.0
 *
 * 2022-03-20
 *
 * © 2022 Matthew Kellock
 */

/** Series of DiscountsController tests. */
public class RestaurantsControllerTests {

    private static final RestaurantMenuItem ITEM_1_1 =
        new RestaurantMenuItem("Item 1-1", 1.00);
    private static final RestaurantMenuItem ITEM_1_2 =
        new RestaurantMenuItem("Item 1-2", 2.00);
    private static final RestaurantMenuItem ITEM_2_1 =
        new RestaurantMenuItem("Item 2-1", 3.00);
    private static final RestaurantMenuItem ITEM_2_2 = 
        new RestaurantMenuItem("Item 2-2", 4.00);
    private static final RestaurantMenuItem ITEM_3_1 =
        new RestaurantMenuItem("Item 3-1", 5.00);
    private static final RestaurantMenuItem ITEM_3_2 =
        new RestaurantMenuItem("Item 3-2", 6.00);

    private final Restaurant restaurant1;
    private final Restaurant restaurant2;
    private final Restaurant restaurant3;
    
    private final LinkedList<Restaurant> restaurantList =
        new LinkedList<Restaurant>();
    private final Restaurants restaurants = new Restaurants();

    public RestaurantsControllerTests() {
        LinkedList<RestaurantMenuItem> restaurant1Menu =
            new LinkedList<RestaurantMenuItem>();
        LinkedList<RestaurantMenuItem> restaurant2Menu =
            new LinkedList<RestaurantMenuItem>();
        LinkedList<RestaurantMenuItem> restaurant3Menu =
            new LinkedList<RestaurantMenuItem>();
        
        // Add restaurant 1's menu items
        restaurant1Menu.add(ITEM_1_1);
        restaurant1Menu.add(ITEM_1_2);

        // Add restaurant 2's menu items
        restaurant2Menu.add(ITEM_2_1);
        restaurant2Menu.add(ITEM_2_2);

        // Add restaurant 3's menu items
        restaurant3Menu.add(ITEM_3_1);
        restaurant3Menu.add(ITEM_3_2);

        // Instansiate restaurant 1
        restaurant1 = new Restaurant(
            "Restaurant 1", 
            Category.FAST_FOOD, 
            1.10, 
            restaurant1Menu
        );

        // Instansiate restaurant 2
        restaurant2 = new Restaurant(
            "Restaurant 2", 
            Category.RESTAURANT, 
            2.20, 
            restaurant2Menu
        );

        // Instansiate restaurant 3
        restaurant3 = new Restaurant(
            "Restaurant 3", 
            Category.CAFE, 
            3.30, 
            restaurant3Menu
        );

        // Add the restaurants
        restaurantList.addAll(
            Arrays.asList(restaurant1, restaurant2, restaurant3)
        );

        // Set the list to the restaurants object
        restaurants.setRestaurants(restaurantList);
    }

    @Test
    void shouldLoadRestaurants() throws java.io.IOException {
        final String sampleRestaurants =
            "Restaurant 1,Fast food,$1.10,Item 1-1-$1.00,Item 1-2-$2.00\n"
            + "Restaurant 2,Restaurant,$2.20,Item 2-1-$3.00,Item 2-2-$4.00\n"
            + "Restaurant 3,Cafe,$3.30,Item 3-1-$5.00,Item 3-2-$6.00\n";
        final Reader sampleRestaurantsReader =
            new StringReader(sampleRestaurants);
        final BufferedReader sampleRestaurantsBufferedReader =
            new BufferedReader(sampleRestaurantsReader);

        Restaurants testRestaurants = new Restaurants();
        RestaurantsController testRestaurantsController =
            new RestaurantsController(testRestaurants);
        testRestaurantsController.loadRestaurants(
            sampleRestaurantsBufferedReader
        );

        // We are converting to JSON here to compare values and not the object
        // instances themselves
        Assertions.assertEquals(
            new Gson().toJson(testRestaurants.getRestaurants()),
            new Gson().toJson(restaurantList)
        );
    }
}
