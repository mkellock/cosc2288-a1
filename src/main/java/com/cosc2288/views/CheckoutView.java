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

    public static String printCheckout(Order order, String border) {
        StringBuilder returnVal = new StringBuilder();
        final String TOTAL_FORMAT = "%-35s %10s%n";
        final String CURRENCY_FORMAT = "$%.2f";

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
                        String.format("%2s %-32s %10s%n",
                            orderItem.getQuantity(),
                            orderItem.getRestaurantMenuItem().getDescription(),
                            String.format(CURRENCY_FORMAT, 
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
            String.format(TOTAL_FORMAT,
                "Order price:", String.format(CURRENCY_FORMAT, order.orderPrice())
            )
        );

        // Add the delivery fee
        returnVal.append(
            String.format(TOTAL_FORMAT,
                "Delivery fee:", String.format(CURRENCY_FORMAT, order.deliveryFee())
            )
        );

        // Add the savings
        returnVal.append(
            String.format(TOTAL_FORMAT,
                "You have saved:", String.format(CURRENCY_FORMAT, order.savings())
            )
        );

        // Add the total
        returnVal.append(
            String.format(TOTAL_FORMAT,
                "Total amount to pay:", String.format(CURRENCY_FORMAT, order.total())
            )
        );

        returnVal.append(border);

        // Add the salutation
        returnVal.append("Thanks for ordering with Melbourne Eats. " + 
            "Enjoy your meal.\n");

        return returnVal.toString();
    }
}
