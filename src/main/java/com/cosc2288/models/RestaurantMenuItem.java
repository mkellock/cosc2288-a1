package com.cosc2288.models;

/**
 * RestaurantMenuItem
 *
 * v1.0
 *
 * 2022-03-20
 *
 * © 2022 Matthew Kellock
 */

/** RestaurantMenuItem object is used in the creation of restaurant menus. */
public class RestaurantMenuItem {

    private final String description;
    private final double price;

    /**
     * Initialises a restaurant menu item including description and price
     * @param description   The description of the menu item
     * @param price         The menu item's price
     */
    public RestaurantMenuItem(String description, double price) {
        this.description = description;
        this.price = price;
    }

    /**
     * Retrieves the description for the menu item
     * @return  The menu item's description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves the price of the menu item
     * @return  The menu item's price
     */
    public double getPrice() {
        return price;
    }

}
