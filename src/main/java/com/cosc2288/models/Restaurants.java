package com.cosc2288.models;

/**
 * Restaurants
 *
 * v1.0
 *
 * 2022-03-20
 *
 * © 2022 Matthew Kellock
 */

import java.util.List;

/**
 * The Restaurants object is used in maintaining a collection of restaurants.
 */
public class Restaurants {
    private List<Restaurant> restaurantsList;

    /**
     * Property to retrieve the restaurant items.
     * @return  Collection of restaurants
     */
    public List<Restaurant> getRestaurants() {
        return this.restaurantsList;
    }

    /**
     * Property to set the restaurant items.
     * @param restaurants Collection of restaurants
     */
    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurantsList = restaurants;
    }
}
