package com.cosc2288.controllers;

/**
 * RestaurantsController
 *
 * v1.0
 *
 * 2022-03-20
 *
 * © 2022 Matthew Kellock
 */

import com.cosc2288.models.RestaurantMenuItem;
import com.cosc2288.models.Restaurants;
import com.cosc2288.models.Restaurant;
import com.cosc2288.models.Restaurant.Category;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RestaurantsController {
    private Restaurants restaurants;

    /**
     * Initialises the RestaurantsContoller and sets the appropriate returants.
     * @param restaurants The restaurants object
     */
    public RestaurantsController(Restaurants restaurants) {
        // Assign the restaurant value
        this.restaurants = restaurants;
    }

    /**
     * Interprets and loads the restaurants from a buffered reader.
     * @param reader    The buffered reader used in reading the restaurants
     * @throws java.io.IOException
     */
    public void loadRestaurants(BufferedReader reader) 
        throws java.io.IOException {
        String readerLine;

        // Our restaurants
        LinkedList<Restaurant> restaurantsList =
            new LinkedList<Restaurant>();

        // Regex pattern for menu items
        Pattern patternRestaurant =
            Pattern.compile("[^,]+,[^,]+,\\$\\d+.\\d+,");
        Pattern patternMenuItem = Pattern.compile("([^,]*)-\\$[\\d.]+");

        // Loop through each line provided by the buffered reader
        while ((readerLine = reader.readLine()) != null) {
            // Split the line via commas
            String[] splitLine = readerLine.split(",");

            // Match the restaurants information
            if (patternRestaurant.matcher(readerLine).find()) {
                // Create a new list for the menu items
                LinkedList<RestaurantMenuItem> restaurantMenuItems = 
                    new LinkedList<RestaurantMenuItem>();
                
                // Match all instances of the menu items
                Matcher matcher = patternMenuItem.matcher(readerLine);

                // Loop through all the metches
                while (matcher.find()) {
                    // Split the menu item via the last "-" char and add a menu
                    // item
                    restaurantMenuItems.add(new RestaurantMenuItem(
                        matcher.group().substring(0, 
                            matcher.group().lastIndexOf("-")
                        ),
                        Double.parseDouble(
                            matcher.group().substring(
                                matcher.group().lastIndexOf("-") + 2
                            )
                        )
                    ));
                }

                restaurantsList.add(new Restaurant(
                    splitLine[0],
                    Category.valueOf(
                        splitLine[1].toUpperCase().replace(" ", "_")
                        ),
                    Double.parseDouble(
                        splitLine[2].substring(1, splitLine[2].length())
                        ),
                    restaurantMenuItems
                ));
            }
        }

        // Add the restaurant items
        restaurants.setRestaurants(restaurantsList);

        // Close the reader
        reader.close();
    }
}
