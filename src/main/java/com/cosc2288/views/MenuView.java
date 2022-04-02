package com.cosc2288.views;

import com.cosc2288.models.Menu;
import com.cosc2288.models.MenuItem;
import com.cosc2288.models.Restaurant;
import com.cosc2288.models.Restaurants;
import com.cosc2288.models.Restaurant.Category;
import java.util.LinkedList;
import java.util.Scanner;

public class MenuView {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String BOLD_TEXT = "\033[0;1m";

    /**
     * Generates a string representation of the menu
     * @param menu      The menu items that are going to be displayed
     * @param border    The border used in constructing the menu
     * @return  Returns the constructed menu
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
     * @param scanner   The scaner object for retrieving user input
     * @param maxItems  The maximum amount of menu items used to validate input
     * @return  Returns the validated selection
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

    /**
     * Validates the user's menu selection
     * @param selection The input being validated
     * @param maxItems  The maximum amount of menu items used to validate input
     * @return  Returns the selection, if invalid, returns 0
     */
    public static int ValidateSelection(String selection, int maxItems) {
        int returnVal = 0;

        // Numeric input within range
        if (selection.matches("\\d+") 
            && Integer.parseInt(selection) > 0
            && Integer.parseInt(selection) <= maxItems) { 
            returnVal = Integer.parseInt(selection);
        } else if (selection.isBlank()) {
            // Blank selection, return the last menu item
            returnVal = maxItems;
        }

        return returnVal;
    }
}
