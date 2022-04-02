package com.cosc2288.views;

import com.cosc2288.models.Menu;
import com.cosc2288.models.MenuItem;
import com.cosc2288.models.Restaurant;
import com.cosc2288.models.Restaurants;
import com.cosc2288.models.Restaurant.Category;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.MenuSelectionManager;

public class MenuView {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String BOLD_TEXT = "\033[0;1m";

    /**
     * Instansiates the menu from a series of resturants
     * @param restaurants   List of resturants
     */
    public static String ListRestaurants(Restaurants restaurants,
        String border) {
        LinkedList<MenuItem> menuItems = new LinkedList<MenuItem>();

        // Loop through the restaurants
        for (Restaurant restaurant : restaurants.getRestaurants()) {
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
     * Instansiates the menu from a series of resturants for a particular
     * category
     * @param restaurants   List of resturants
     * @param category      The category we want to limit the list to
     */
    public static String ListRestaurants(Restaurants restaurants,
        Category category, String border) {
        LinkedList<MenuItem> menuItems = new LinkedList<MenuItem>();

        for (Restaurant restaurant : restaurants.getRestaurants()) {
            if (category == restaurant.getCategory()) {
                menuItems.add(new MenuItem(restaurant.getName()));
            }
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
     * Generates a string representation of the menu
     * @param divider   The horizontal divider for the menu
     * @param title     The menu's title
     * @return
     */
    public static String DisplayMenu(Menu menu, String border) {
        Integer itemCounter = 1;

        // Generate the menu title
        String returnVal = border + "> " + menu.getMenuTitle() + 
            "\n" + border;

        // Loop through the menu items
        for (MenuItem menuItem : menu.getMenuItems()) {
            if (itemCounter == menu.getMenuItems().size()) {
                returnVal += BOLD_TEXT;
            }

            returnVal += 
                String.format("%3s)" + ANSI_RESET + " %s\n", 
                    itemCounter, 
                    menuItem.getTitle()
                );

            itemCounter++;
        }

        // Return the completed menu
        return returnVal;
    }

    /**
     * Prompts the user for a menu selection based on valid inputs
     * @return  Returns the selection
     */
    public static int MenuSelection(Scanner scanner, int maxItems) {
        int returnVal;
        String selection = "";

        do {
            // Prompt for a selection
            System.out.print("Please select: " + BOLD_TEXT);

            // Grab the selection
            selection = scanner.nextLine();

            // Reset the colour
            System.out.print(ANSI_RESET);

            // Valid selection
            returnVal = ValidateSelection(selection, maxItems);
            
            // Invalid selection
            if (returnVal == 0) {
                System.out.print("Please select a valid menu option.\n");
            }
        } while (returnVal == 0);

        // Return the selection
        return returnVal;
    }

    public static int ValidateSelection(String selection, int maxItems) {
        int returnVal = 0;

        if (selection.matches("\\d+") 
            && Integer.parseInt(selection) > 0
            && Integer.parseInt(selection) <= maxItems + 1) { 
            returnVal = Integer.parseInt(selection);
        } else if (selection.isBlank()) { // Blank selection
            returnVal = maxItems + 1;
        }

        return returnVal;
    }
}
