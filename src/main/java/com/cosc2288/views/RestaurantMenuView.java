package com.cosc2288.views;

/**
 * RestaurantMenuView
 *
 * v1.0
 *
 * 2022-04-02
 *
 * © 2022 Matthew Kellock
 */

import com.cosc2288.models.Menu;
import com.cosc2288.models.MenuItem;
import com.cosc2288.models.Restaurant;
import com.cosc2288.models.Restaurants;
import com.cosc2288.models.Restaurant.Category;
import java.util.LinkedList;
import java.util.Scanner;

/* The view for the restaurant menu */
public class RestaurantMenuView {

    /**
     * Prevents instansiation of a static class
     * @throws IllegalStateException
     */
    private RestaurantMenuView() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Instansiates the menu from a series of restaurants
     * @param restaurants   List of restaurants
     * @param border        The border used in constructing the menu
     * @return  Returns the constructed menu
     */
    private static String listRestaurants(LinkedList<Restaurant> restaurants,
        String border) {
        LinkedList<MenuItem> menuItems = new LinkedList<MenuItem>();

        // Loop through the restaurants
        for (Restaurant restaurant : restaurants) {
            menuItems.add(new MenuItem(restaurant.getName()));
        }

        // Add the main menu item
        menuItems.add(new MenuItem("Go to main menu"));

        // Display a menu based off the restaurant items
        return MenuView.displayMenu(
            new Menu("Select from restaurant list", menuItems),
            border
        );
    }

    /**
     * Give the user the ability to search for restaurants and returns a
     * selection
     * @param scanner       The scaner object for retrieving user input
     * @param restaurants   List of restaurants
     * @param category      The category selection
     * @param border        The border used in constructing the menu
     * @return  Returns the validated restaurant selection
     */
    public static Restaurant restaurantSelection(Scanner scanner, 
        Restaurants restaurants, Category category, String border) {
        LinkedList<Restaurant> filteredRestaurants =
            new LinkedList<Restaurant>();

        for (Restaurant restaurant : restaurants.getRestaurants()) {
            if (category == restaurant.getCategory()) {
                filteredRestaurants.add(restaurant);
            }
        }

        // Display a menu based off the restaurant items
        System.out.print(
            listRestaurants(filteredRestaurants, border)
        );

        Integer itemSelection = MenuView.menuSelection(scanner, 
            filteredRestaurants.size() + 1);

        if (itemSelection <= filteredRestaurants.size()) {
            return filteredRestaurants.get(
                itemSelection - 1
            );
        } else {
            return null;
        }
    }

    /**
     * Give the user the ability to search for restaurants and returns a
     * selection
     * @param scanner       The scaner object for retrieving user input
     * @param restaurants   List of restaurants
     * @param border        The border used in constructing the menu
     * @return  Returns the validated restaurant selection
     */
    public static Restaurant restaurantSearch(Scanner scanner, 
        Restaurants restaurants, String border) {
            LinkedList<Restaurant> filteredRestaurants =
                new LinkedList<Restaurant>();
            String selection = "";

            do {
                // Prompt for a selection
                System.out.print(
                    "Please enter a restaurant name: " + MenuView.BOLD_TEXT
                    );

                // Grab the selection
                selection = scanner.nextLine();

                if (!selection.equals("")) {
                    for (Restaurant restaurant : restaurants.getRestaurants()) {
                        if (restaurant.getName().toLowerCase()
                                .contains(selection.toLowerCase())) {
                            filteredRestaurants.add(restaurant);
                        }
                    }

                    // Display a menu based off the restaurant items
                    System.out.print(
                        listRestaurants(filteredRestaurants, border)
                    );

                    Integer itemSelection = MenuView.menuSelection(scanner, 
                        filteredRestaurants.size() + 1);

                    if (itemSelection <= filteredRestaurants.size()) {
                        return filteredRestaurants.get(
                            itemSelection - 1
                        );
                    } else {
                        return null;
                    }
                }

            } while (selection.equals(""));

            return null;
    }
}
