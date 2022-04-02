package com.cosc2288.controllers;

/**
 * DiscountsController
 *
 * v1.0
 *
 * 2022-03-20
 *
 * © 2022 Matthew Kellock
 */

import com.cosc2288.models.DiscountItem;
import com.cosc2288.models.Discounts;
import java.io.BufferedReader;
import java.util.Collections;
import java.util.LinkedList;

/** Controller to manage the behaviour of the Discounts model. */
public class DiscountsController {
    private Discounts discounts;

    /**
     * Initialises the DiscountsController and sets the appropriate discounts.
     * @param discounts The discounts object
     */
    public DiscountsController(Discounts discounts) {
        // Assign the discount value
        this.discounts = discounts;

        // Sort the discounts
        sortDiscounts();
    }

    private void sortDiscounts() {
        LinkedList<DiscountItem> discountItems = discounts.getDiscountItems();

        // If we have discounts
        if (discounts.getDiscountItems() != null) {
            // Sort the discounts
            Collections.sort(discountItems, (item1, item2) -> {
                int result = item1.getMin() - item2.getMin();

                if (result == 0) {
                    result = item1.getMax() - item2.getMax();
                }

                if (result == 0) {
                    result = item1.getPercent() - item2.getPercent();
                }

                return result;
            });

            // Set the discounted items
            discounts.setDiscountItems(discountItems);
        }
    }

    /**
     * Interprets and loads the discounts from a buffered reader.
     * @param reader    The buffered reader used in reading the discounts
     * @throws java.io.IOException
     */
    public void loadDiscounts(BufferedReader reader) 
        throws java.io.IOException {
        String readerLine;
        LinkedList<DiscountItem> discountItems = new LinkedList<DiscountItem>();

        while ((readerLine = reader.readLine()) != null) {
            String[] splitLine = readerLine.split(",");

            // A standard discount line
            if (readerLine.matches("\\[\\d+,\\d*\\),\\d+\\%")) {
                if (!splitLine[1].equals(")")) {
                    discountItems.add(new DiscountItem(
                        Integer.parseInt(splitLine[0].substring(1)),
                        Integer.parseInt(
                            splitLine[1].substring(0, splitLine[1].length() - 1)
                            ),
                        Integer.parseInt(
                            splitLine[2].substring(0, splitLine[2].length() - 1)
                            )
                    ));
                } else {
                    discountItems.add(new DiscountItem(
                        Integer.parseInt(splitLine[0].substring(1)),
                        Integer.parseInt(
                            splitLine[2].substring(0, splitLine[2].length() - 1)
                            )
                    ));
                }
            //
            } else if (readerLine.matches("\\d+,\\d+\\%")) {
                discounts.setDeliveryDiscountRestaurantCount(
                    Integer.parseInt(splitLine[0]));
                discounts.setDeliveryDiscountRestaurantPercent(
                    Integer.parseInt(
                        splitLine[1].substring(0, splitLine[1].length() - 1))
                );
            }
        }

        // Sort the discounts
        sortDiscounts();

        // Add the discount items
        discounts.setDiscountItems(discountItems);

        // Close the reader
        reader.close();
    }
}
