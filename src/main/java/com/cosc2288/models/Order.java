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

import javax.tools.ForwardingFileObject;

/* The model for a order */
public class Order {
    private List<OrderItem> orderItems;
    private List<Restaurant> resturants;
    private final Discounts discounts;

    /**
     * Initialises an order
     * @param discounts The discounts configuration
     */
    public Order(Discounts discounts) {
        orderItems = new ArrayList<OrderItem>();
        resturants = new ArrayList<Restaurant>();
        this.discounts = discounts;
    }

    /**
     * Gets the order items
     * @return returns the order items
     */
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    /**
     * Gets the resturants
     * @return returns the resturants
     */
    public List<Restaurant> getResturants() {
        return resturants;
    }

    /**
     * Gets the food total
     * @return returns the food total
     */
    private double orderTotal() {
        double total = 0.0;

        // Total the pruchase amount
        for (OrderItem orderItem : orderItems) {
            total += orderItem.getRestaurantMenuItem().getPrice() *
                orderItem.getQuantity();
        }

        // Return the total
        return total;
    }

    /**
     * Gets the food total with discounts
     * @return returns the food total with discounts
     */
    public double orderPrice() {
        double total = orderTotal();

        // Loop through each discount row
        for (DiscountItem discountItem: discounts.getDiscountItems()) {
            // If the total is in range
            if (total >= discountItem.getMin() &&
                total < discountItem.getMax()) {
                // Apply the discount
                total *= 1.0 - (discountItem.getPercent() / 100.0);
            }
        }

        // Return the total
        return Math.round(total * 100.0) / 100.0;
    }

    /**
     * Gets the delivery total
     * @return returns the delivery total
     */
    private double deliveryTotal() {
        double total = 0;

        // Total the delivery fees
        for (Restaurant resturant : resturants) {
            total += resturant.getDeliveryFee();
        }

        // Return the total
        return total;
    }

    /**
     * Gets the delivery total with discounts
     * @return returns the delivery total with discounts
     */
    public double deliveryFee() {
        double total = deliveryTotal();

        // If we have the required amount of resturants we're ordering from,
        // apply the discount
        if (resturants.size() >=
            discounts.getDeliveryDiscountRestaurantCount()) {
            // Apply the discount
            total *= 1.0 - 
                (discounts.getDeliveryDiscountRestaurantPercent() / 100.0);
        }

        // Return the total
        return Math.round(total * 100.0) / 100.0;
    }

    /**
     * Gets the order total with discounts
     * @return returns the order total with discounts
     */
    public double total() {
        return orderPrice() + deliveryFee();
    }

    /**
     * Gets the order discounts
     * @return returns the discounts
     */
    public double savings() {
        return Math.round(
                (
                    (orderTotal() + deliveryTotal()) - total()
                ) * 100.0
            ) / 100.0;
    }

    /**
     * Gets the order discounts
     * @param orderItem The order item to add to the order
     */
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
