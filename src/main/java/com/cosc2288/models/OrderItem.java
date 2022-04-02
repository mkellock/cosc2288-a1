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

public class OrderItem {
    private final Restaurant restaurant;
    private final RestaurantMenuItem restaurantMenuItem;
    private Integer quantity;

    public OrderItem(
        Restaurant restaurant,
        RestaurantMenuItem restaurantMenuItem,
        Integer quantity) {
            this.restaurant = restaurant;
            this.restaurantMenuItem = restaurantMenuItem;
            this.quantity = quantity;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public RestaurantMenuItem getRestaurantMenuItem() {
        return restaurantMenuItem;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
