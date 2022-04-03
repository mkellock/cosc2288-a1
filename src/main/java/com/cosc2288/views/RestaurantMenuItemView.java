package com.cosc2288.views;

/**
 * RestaurantMenuItemView
 *
 * v1.0
 *
 * 2022-04-02
 *
 * © 2022 Matthew Kellock
 */

import com.cosc2288.models.Menu;
import com.cosc2288.models.MenuItem;
import com.cosc2288.models.OrderItem;
import com.cosc2288.models.Restaurant;
import com.cosc2288.models.RestaurantMenuItem;
import java.util.LinkedList;
import java.util.Scanner;

/* The view for the restaurant menu item */
public class RestaurantMenuItemView {

    /**
     * Prevents instansiation of a static class
     * @throws IllegalStateException
     */
    private RestaurantMenuItemView() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Instansiates the menu from a series of restaurants
     * @param restaurant    The restaurant we're grabbing menu items from
     * @param border        The border used in constructing the menu
     * @return  Returns the constructed menu
     */
    private static String listRestaurantMenuItem(Restaurant restaurant,
        String border) {
        LinkedList<MenuItem> menuItems = new LinkedList<MenuItem>();

        // Loop through the restaurant menu items
        for (RestaurantMenuItem restaurantMenuItem : 
                restaurant.getRestaurantMenuItems()) {
            menuItems.add(
                new MenuItem(
                    String.format("%-30s %10s", 
                        restaurantMenuItem.getDescription(),
                        String.format("$%.2f", restaurantMenuItem.getPrice())
                    )
                )
            );
        }

        // Add the main menu item
        menuItems.add(new MenuItem("No more"));

        // Display a menu based off the restaurant items
        return MenuView.displayMenu(
            new Menu(
                "Select a food item from " + restaurant.getName(), 
                menuItems
            ), border);
    }

    /**
     * Give the user the ability to search for restaurants and returns a
     * selection
     * @param scanner       The scaner object for retrieving user input
     * @param restaurant    The restaurant we're grabbing menu items from
     * @param border        The border used in constructing the menu
     * @return  Returns the validated restaurant selection
     */
    public static OrderItem restaurantMenuItemSelection(
        Scanner scanner, Restaurant restaurant, String border) {
        RestaurantMenuItem restaurantMenuItem;
        Integer restaurantMenuItemNo;
        Integer quantity;

        // Display a menu based off the restaurant items
        System.out.print(
            listRestaurantMenuItem(restaurant, border)
        );

        // Grab the menu item selection
        restaurantMenuItemNo = MenuView.menuSelection(
                scanner,
                restaurant.getRestaurantMenuItems().size() + 1);

        // Grab the restaurant menu item
        if (restaurantMenuItemNo <= 
            restaurant.getRestaurantMenuItems().size()) {
            // If we're selecting a menu item, grab the menu item
            restaurantMenuItem = restaurant.getRestaurantMenuItems()
                .get(restaurantMenuItemNo - 1);

            // Grab the quantity from the user
            quantity = MenuView.menuSelection(
                scanner, 100, "Please enter an amount: ",
                "Please enter a valid quantity.");
        } else {
            return null;
        }

        return new OrderItem(restaurant, restaurantMenuItem, quantity);
    }
}
