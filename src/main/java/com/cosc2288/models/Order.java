package com.cosc2288.models;

/**
 * Order
 *
 * v1.0
 *
 * 2022-04-02
 *
 * © 2022 Matthew Kellock
 */

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderItem> orderItems;
    private List<Restaurant> resturants;

    public Order() {
        orderItems = new ArrayList<OrderItem>();
        resturants = new ArrayList<Restaurant>();
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public List<Restaurant> getResturants() {
        return resturants;
    }

    public void addOrderItem(OrderItem orderItem) {
        // Loop through the order items
        for (OrderItem tempOrderItem : orderItems) {
            // If the item already exists
            if (tempOrderItem.getRestaurantMenuItem() == 
                // Add the quantity to the existing item
                orderItem.getRestaurantMenuItem()) {
                tempOrderItem.setQuantity(
                    tempOrderItem.getQuantity() + orderItem.getQuantity()
                );

                return;
            }
        }

        // Else, add the new item
        orderItems.add(orderItem);

        // And, add the restaurant if it doesn't exist
        if (!resturants.contains(orderItem.getRestaurant())) {
            resturants.add(orderItem.getRestaurant());
        }
    }
}
