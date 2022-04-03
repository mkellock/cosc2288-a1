package com.cosc2288.models;

/**
 * OrderItem
 *
 * v1.0
 *
 * 2022-04-02
 *
 * © 2022 Matthew Kellock
 */

 /* The model for an order item */
public class OrderItem {
    private final Restaurant restaurant;
    private final RestaurantMenuItem restaurantMenuItem;
    private Integer quantity;

    /**
     * Initialises an order item
     * @param restaurant The restaurant for the order item
     * @param restaurantMenuItem The restaurant menu item for the order item
     * @param quantity The quantity of the menu item
     */
    public OrderItem(
        Restaurant restaurant,
        RestaurantMenuItem restaurantMenuItem,
        Integer quantity) {
            this.restaurant = restaurant;
            this.restaurantMenuItem = restaurantMenuItem;
            this.quantity = quantity;
    }

    /**
     * Gets the order item's restaurant
     * @return returns the restaurant
     */
    public Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     * Gets the order item's menu item
     * @return returns the menu item
     */
    public RestaurantMenuItem getRestaurantMenuItem() {
        return restaurantMenuItem;
    }

    /**
     * Gets the order item's quantity
     * @return returns the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the order item's quantity
     * @param quantity set the quantity of the menu item
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
