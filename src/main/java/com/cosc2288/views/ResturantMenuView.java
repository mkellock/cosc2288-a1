package com.cosc2288.views;

import com.cosc2288.models.Menu;
import com.cosc2288.models.MenuItem;
import com.cosc2288.models.Restaurant;
import com.cosc2288.models.Restaurants;
import com.cosc2288.models.Restaurant.Category;
import com.cosc2288.views.MenuView;
import java.util.LinkedList;
import java.util.Scanner;

public class ResturantMenuView extends MenuView {
    /**
     * Instansiates the menu from a series of resturants
     * @param restaurants   List of resturants
     * @param border        The border used in constructing the menu
     * @return  Returns the constructed menu
     */
    private static String ListRestaurants(LinkedList<Restaurant> restaurants,
        String border) {
        LinkedList<MenuItem> menuItems = new LinkedList<MenuItem>();

        // Loop through the restaurants
        for (Restaurant restaurant : restaurants) {
            menuItems.add(new MenuItem(restaurant.getName()));
        }

        // Add the main menu item
        menuItems.add(new MenuItem("Go to main menu"));

        // Display a menu based off the restaurant items
        return DisplayMenu(
            new Menu("Select from restaurant list", menuItems),
            border
        );
    }

    /**
     * Give the user the ability to search for restaurants and returns a
     * selection
     * @param scanner       The scaner object for retrieving user input
     * @param restaurants   List of resturants
     * @param category      The category selection
     * @param border        The border used in constructing the menu
     * @return  Returns the validated resturant selection
     */
    public static Restaurant RestaurantSelection(Scanner scanner, 
        Restaurants restaurants, Category category, String border) {
        LinkedList<Restaurant> resturants = new LinkedList<Restaurant>();

        for (Restaurant restaurant : restaurants.getRestaurants()) {
            if (category == restaurant.getCategory()) {
                resturants.add(restaurant);
            }
        }

        // Display a menu based off the restaurant items
        System.out.print(
            ListRestaurants(resturants, border)
        );

        return resturants.get(
            MenuSelection(scanner, resturants.size()) - 1
        );
    }

    /**
     * Give the user the ability to search for restaurants and returns a
     * selection
     * @param scanner       The scaner object for retrieving user input
     * @param restaurants   List of resturants
     * @param border        The border used in constructing the menu
     * @return  Returns the validated resturant selection
     */
    public static Restaurant RestaurantSearch(Scanner scanner, 
        Restaurants restaurants, String border) {
            LinkedList<Restaurant> resturants = new LinkedList<Restaurant>();
            String selection = "";

            do {
                // Prompt for a selection
                System.out.print(
                    "Please enter a restaurant name: " + BOLD_TEXT
                    );

                // Grab the selection
                selection = scanner.nextLine();

                if (selection != "") {
                    for (Restaurant restaurant : restaurants.getRestaurants()) {
                        if (restaurant.getName().toLowerCase()
                            .contains(selection.toLowerCase())) {
                            resturants.add(restaurant);
                        }
                    }

                    // Display a menu based off the restaurant items
                    System.out.print(
                        ListRestaurants(resturants, border)
                    );

                    return resturants.get(
                        MenuSelection(scanner, resturants.size()) - 1
                    );
                }

            } while (selection == "");

            return null;
    }
}
