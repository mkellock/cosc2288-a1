package com.cosc2288.views;

/**
 * CheckoutView
 *
 * v1.0
 *
 * 2022-04-03
 *
 * © 2022 Matthew Kellock
 */

import com.cosc2288.models.Order;
import com.cosc2288.models.OrderItem;
import com.cosc2288.models.Restaurant;

public class CheckoutView {
    /**
     * Prevents instansiation of a static class
     * @throws IllegalStateException
     */
    private CheckoutView() {
        throw new IllegalStateException("Utility class");
    }

    public static String PrintCheckout(Order order, String border) {
        StringBuilder returnVal = new StringBuilder();

        // Add the title
        returnVal.append(border);
        returnVal.append("You have ordered the following item\n");
        returnVal.append(border);

        // Loop through the resturants
        for (Restaurant restaurant : order.getResturants()) {
            returnVal.append(restaurant.getName() + "\n");

            // Loop through the menu items
            for (OrderItem orderItem : order.getOrderItems()) {
                // If the restaurant equals the restaurant in the order item
                if (orderItem.getRestaurant() == restaurant) {
                    // Output the order item
                    returnVal.append(
                        String.format("%2s %-32s %10s\n",
                            orderItem.getQuantity(),
                            orderItem.getRestaurantMenuItem().getDescription(),
                            String.format("$%.2f", 
                                orderItem.getQuantity() * 
                                orderItem.getRestaurantMenuItem().getPrice()
                            )
                        )
                    );
                }
            }

            returnVal.append(border);
        }

        // Add the order price
        returnVal.append(
            String.format("%-35s %10s\n",
                "Order price:", String.format("$%.2f", order.orderPrice())
            )
        );

        // Add the delivery fee
        returnVal.append(
            String.format("%-35s %10s\n",
                "Delivery fee:", String.format("$%.2f", order.deliveryFee())
            )
        );

        // Add the savings
        returnVal.append(
            String.format("%-35s %10s\n",
                "You have saved:", String.format("$%.2f", order.savings())
            )
        );

        // Add the total
        returnVal.append(
            String.format("%-35s %10s\n",
                "Total amount to pay:", String.format("$%.2f", order.total())
            )
        );

        returnVal.append(border);

        // Add the salutation
        returnVal.append("Thanks for ordering with Melbourne Eats. " + 
            "Enjoy your meal.\n");

        return returnVal.toString();
    }
}
