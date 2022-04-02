package com.cosc2288.models;

/**
 * Restaurant
 *
 * v1.0
 *
 * 2022-03-20
 *
 * © 2022 Matthew Kellock
 */

import java.util.List;

/** Restaurant object is used in the construction of Returants and their
 * functions. */
public class Restaurant {
    private final List<RestaurantMenuItem> restaurantMenuItems;
    private final String name;
    private final Category category;
    private final Double deliveryFee;

    /** The category of restaurant. */
    public enum Category {
        /** A traditional restaurant. */
        RESTAURANT,
        /** A cafe. */
        CAFE,
        /** A fast food outlet. */
        FAST_FOOD
    }

    /** Constructor to instantiate a restaurant.
     *
     * @param name                  The name of the restaurant
     * @param category              The restaurant's category
     * @param deliveryFee           The restaurant's delivery fee
     * @param restaurantMenuItems    The restaurant's menu offerings
     */
    public Restaurant(String name, Category category, Double deliveryFee,
        List<RestaurantMenuItem> restaurantMenuItems) {
        this.name = name;
        this.category = category;
        this.deliveryFee = deliveryFee;
        this.restaurantMenuItems = restaurantMenuItems;
    }

    /**
     * Property to return the name of the restaurant.
     * @return  The restaurant's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Property to return the restaurant's category.
     * @return  The restaurant's category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Property to return the restaurant's delivery fee.
     * @return  The restaurant's delivery fee
     */
    public Double getDeliveryFee() {
        return deliveryFee;
    }

    /**
     * Property to return the restaurant's menu items.
     * @return  The restaurant's menu items.
     */
    public List<RestaurantMenuItem> getRestaurantMenuItems() {
        return restaurantMenuItems;
    }
}
