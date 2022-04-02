package com.cosc2288;

/**
 * App
 *
 * v1.0
 *
 * 2022-03-20
 *
 * © 2022 Matthew Kellock
 */

import com.cosc2288.controllers.DiscountsController;
import com.cosc2288.controllers.RestaurantsController;
import com.cosc2288.exceptions.InvalidCommandLineArg;
import com.cosc2288.models.Restaurant.Category;
import com.cosc2288.models.Discounts;
import com.cosc2288.models.FileConfig;
import com.cosc2288.models.Menu;
import com.cosc2288.models.MenuItem;
import com.cosc2288.models.Restaurant;
import com.cosc2288.models.Restaurants;
import com.cosc2288.views.MenuView;
import com.cosc2288.views.RestaurantMenuView;
import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

public final class App {
    private static final String BORDER =
        "-------------------------------------------------------\n";
    private static final String TITLE = "Welcome to Melbourne Eats";
    private static Menu mainMenu;
    private static Menu restaurantCategoriesMenu;
    private static FileConfig fileConfig = new FileConfig();
    private static Discounts discounts = new Discounts();
    private static DiscountsController discountsController =
        new DiscountsController(discounts);
    private static Restaurants restaurants = new Restaurants();
    private static RestaurantsController restaurantsController =
        new RestaurantsController(restaurants);
    private static final Scanner scanner = new Scanner(System.in);

    private App() {

    }

    /**
     * Says hello to the world.
     *
     * @param args The arguments of the program.
     */
    public static void main(String[] args)
        throws java.io.FileNotFoundException, java.io.IOException {
        Boolean leaveApp = false;
        
        // Print the title
        System.out.println(TITLE);

        // Load the command line args
        try {
            System.out.print(loadCommandLineArgs(args));
        } catch (InvalidCommandLineArg e) {
            System.out.println(e.getMessage());
        }

        // Load the discounts file
        File discountsFile = new File(fileConfig.getDiscountsPath());
        BufferedReader discountsFileBufferedReader = new BufferedReader(
            new FileReader(discountsFile)
        );
        discountsController.loadDiscounts(discountsFileBufferedReader);

        // Load the restaurant file
        File restaurantsFile = new File(fileConfig.getRestaurantsPath());
        BufferedReader restaurantsFileBufferedReader =
            new BufferedReader(new FileReader(restaurantsFile)
        );
        restaurantsController.loadRestaurants(restaurantsFileBufferedReader);
        
        // Set the main menu items
        LinkedList<MenuItem> menuItems = new LinkedList<MenuItem>();
        menuItems.add(new MenuItem("Browse by category"));
        menuItems.add(new MenuItem("Search by restaurant"));
        menuItems.add(new MenuItem("Checkout"));
        menuItems.add(new MenuItem("Exit"));
        mainMenu = new Menu("Select from main menu", menuItems);

        LinkedList<MenuItem> restaurantCategories = new LinkedList<MenuItem>();
        restaurantCategories.add(new MenuItem("Restaurant"));
        restaurantCategories.add(new MenuItem("Cafe"));
        restaurantCategories.add(new MenuItem("Fast food"));
        restaurantCategories.add(new MenuItem("Exit"));
        restaurantCategoriesMenu =
            new Menu("Select by category", restaurantCategories);

        // Loop showing the menu infinately
        while (!leaveApp) {
            System.out.print(MenuView.DisplayMenu(mainMenu, BORDER));
            Integer selection =
                MenuView.MenuSelection(
                    scanner,
                    mainMenu.getMenuItems().size()
                );
            
            switch (selection) {
                case 1:
                case 2:
                    Restaurant restaurantSelection = null;

                    // If we're searching by category
                    if (selection == 1) {
                        // Display the restaurant categories
                        System.out.print(
                            MenuView.DisplayMenu(
                                restaurantCategoriesMenu,
                                BORDER
                            )
                        );

                        // Retrieve the category selection
                        Integer categorySelection =
                            MenuView.MenuSelection(
                                scanner,
                                Category.values().length + 1
                            );

                        // Transpose the category selection into a category
                        if (categorySelection <= Category.values().length) {
                            Category category;
                            category = Category.values()[categorySelection - 1];

                            // Retrieve the restaurant selection
                            restaurantSelection =
                                RestaurantMenuView.RestaurantSelection(
                                    scanner,
                                    restaurants,
                                    category,
                                    BORDER
                            );

                            System.out.print(restaurantSelection.getName());
                        }
                    } else { // If we're searching by text input
                        restaurantSelection =
                            RestaurantMenuView.RestaurantSearch(
                                scanner,
                                restaurants,
                                BORDER
                        );
                    }

                    // If we have selected a restaurant
                    if (restaurantSelection != null) {
                        System.out.print(restaurantSelection.getName());
                    }

                    break;
                case 3:
                    System.out.println("Not yet commissioned...");
                    break;
                case 4:
                    // Exit the applicaton
                    leaveApp = true;
            }
        }
    }

    private static String loadCommandLineArgs(String[] args)
        throws InvalidCommandLineArg {
        final String runHelpSuggestion =
            ", run --help/-h to see valid command line arguements";
        final String invalidCommandLineArg =
            "\"%s\" is an invalid command line arguement" + runHelpSuggestion;
        final String missingCommandLineArg =
            "You are missing a command line arguement" + runHelpSuggestion;
        final String helpCommand = ""
            + "Here are the commands available to run this application:\n"
            + "  --help/-h       : This help document\n"
            + "  --discounts/-d  : Location of discounts file (required)\n"
            + "  --restaurants/-r : Location of restaurants file (required)\n"
            + BORDER;
        Boolean settingArg = false;

        // Loop through the command line arguements
        for (int i = 0; i < args.length; i++) {
            // If we're not grabbing a command line value
            if (!settingArg) {
                switch (args[i]) {
                    case "--help": // Help command
                    case "-h":
                        return helpCommand;
                    case "--discounts": // Discounts file location
                    case "-d":
                        if (args.length >= i + 1) {
                            fileConfig.setDiscountsPath(args[i + 1]);
                            settingArg = true;
                        }
                        break;
                    case "--restaurants": // Restaurants file location
                    case "-r":
                        if (args.length >= i + 1) {
                            fileConfig.setRestaurantsPath(args[i + 1]);
                            settingArg = true;
                        }
                        break;
                    default: // Unexpected command line arg
                        throw new InvalidCommandLineArg(
                            String.format(invalidCommandLineArg, args[i])
                        );
                }
            } else { // Reset to look for command line key
                settingArg = false;
            }
        }

        // If the discounts or restaurants value are empty
        if (fileConfig.getDiscountsPath().isEmpty() || 
            fileConfig.getRestaurantsPath().isEmpty()) {
            throw new InvalidCommandLineArg(missingCommandLineArg);
        }

        // Return an empty string is all loaded OK
        return "";
    }
}
