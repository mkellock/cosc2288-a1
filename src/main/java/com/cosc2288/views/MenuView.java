package com.cosc2288.views;

/**
 * MenuView
 *
 * v1.0
 *
 * 2022-04-02
 *
 * © 2022 Matthew Kellock
 */

import com.cosc2288.models.Menu;
import com.cosc2288.models.MenuItem;
import java.util.Scanner;

/* The view for the user menu */
public class MenuView {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String BOLD_TEXT = "\033[0;1m";

    private MenuView() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Generates a string representation of the menu
     * @param menu      The menu items that are going to be displayed
     * @param border    The border used in constructing the menu
     * @return  Returns the constructed menu
     */
    public static String displayMenu(Menu menu, String border) {
        Integer itemCounter = 1;

        // Generate the menu title
        StringBuilder returnVal = new StringBuilder();
        returnVal.append(border);
        returnVal.append("> ");
        returnVal.append(menu.getMenuTitle());
        returnVal.append("\n");
        returnVal.append(border);

        // Loop through the menu items
        for (MenuItem menuItem : menu.getMenuItems()) {
            if (itemCounter == menu.getMenuItems().size()) {
                returnVal.append(BOLD_TEXT);
            }

            returnVal.append(
                String.format("%3s)" + ANSI_RESET + " %s\n", 
                    itemCounter, 
                    menuItem.getTitle()
                )
            );

            itemCounter++;
        }

        // Return the completed menu
        return returnVal.toString();
    }

    /**
     * Prompts the user for a menu selection based on valid inputs
     * @param scanner   The scaner object for retrieving user input
     * @param maxItems  The maximum amount of menu items used to validate input
     * @return  Returns the validated selection
     */
    public static int menuSelection(Scanner scanner, int maxItems) {
        // Call the menuSelection function with default
        return menuSelection(scanner, maxItems, "Please select: ",
            "Please select a valid menu option.");
    }

    /**
     * Prompts the user for a menu selection based on valid inputs
     * @param scanner   The scaner object for retrieving user input
     * @param maxItems  The prompt for the user
     * @return  Returns the validated selection
     */
    public static int menuSelection(Scanner scanner, int maxItems,
        String prompt, String error) {
            
        int returnVal;
        String selection = "";

        do {
            // Prompt for a selection
            System.out.print(prompt + BOLD_TEXT);

            // Grab the selection
            selection = scanner.nextLine();

            // Reset the colour
            System.out.print(ANSI_RESET);

            // Valid selection
            returnVal = validateSelection(selection, maxItems);
            
            // Invalid selection
            if (returnVal == 0) {
                System.out.print(error + "\n");
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
    public static int validateSelection(String selection, int maxItems) {
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
