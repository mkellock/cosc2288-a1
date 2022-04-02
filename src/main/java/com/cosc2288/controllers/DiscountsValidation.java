package com.cosc2288.controllers;

import com.cosc2288.exceptions.OverlappingDiscount;
import com.cosc2288.models.DiscountItem;
import com.cosc2288.models.Discounts;

/**
 * DiscountsValidation
 *
 * v1.0
 *
 * 2022-03-20
 *
 * © 2022 Matthew Kellock
 */

/** Methods to test the validity of discounts. */
public class DiscountsValidation {
    private final Discounts discounts;

    /**
     * Initialises the DiscountsValidation and sets the appropriate discounts.
     * @param discounts The discounts object to peform the validation on
     */
    public DiscountsValidation(Discounts discounts) {
        // Assign the discount value
        this.discounts = discounts;
    }

    /**
     * Validates that the supplied discounts have logical values.
     * @throws OverlappingDiscount
     */
    public void validateDiscounts() throws OverlappingDiscount {
        // Check for min equals or less than max discount items
        validateMaxLessThanOrEqualToMinDiscounts();

        // Check for overlapping min discounts, because the items are 
        // presorted, we only need to check for two min values that are equal
        validateMinDiscounts();

        // Check for overlapping max discounts
        validateMaxDiscounts();

        // Check for overlapping min/max discounts, equals are fine, but less
        // than is not
        validateMinMaxDiscounts();
    }

    /**
     * Check for min equals or less than max discount items.
     * @throws OverlappingDiscount
     */
    private void validateMaxLessThanOrEqualToMinDiscounts() throws 
        OverlappingDiscount {
        final String maxLessThanOrEqual = "The min discount %s cannot be "
            + "greater than or equal to the max discount %s";

        for (DiscountItem discount : discounts.getDiscountItems()) {
            if (discount.getMin() >= discount.getMax()) {
                throw new OverlappingDiscount(
                    String.format(maxLessThanOrEqual, discount.getMin(), 
                    discount.getMax())
                    );
            }
        }
    }

    /**
     * Check for overlapping min discounts, because the items are presorted,
     * we only need to check for two min values that are equal.
     * @throws OverlappingDiscount
     */
    private void validateMinDiscounts() throws OverlappingDiscount {
        int lastDiscount = Integer.MIN_VALUE;
        final String overlappingMinDiscount = "The min discount value %s "
            + "cannot be equal to the min discount value %s";

        for (DiscountItem discount : discounts.getDiscountItems()) {
            if (lastDiscount == Integer.MIN_VALUE) {
                lastDiscount = discount.getMin();
            } else if (discount.getMin() == lastDiscount) {
                throw new OverlappingDiscount(
                    String.format(overlappingMinDiscount, discount.getMin(), 
                    lastDiscount)
                    );
            } else {
                lastDiscount = discount.getMin();
            }
        }
    }

    /**
     * Check for overlapping max discounts.
     * @throws OverlappingDiscount
     */
    private void validateMaxDiscounts() throws OverlappingDiscount {
        int lastDiscount = Integer.MAX_VALUE;
        final String overlappingMaxDiscount = "The max discount value %s "
            + "cannot be less than or equal to the max "
                + "discount value %s";

        for (DiscountItem discount : discounts.getDiscountItems()) {
            if (lastDiscount == Integer.MAX_VALUE) {
                lastDiscount = discount.getMax();
            } else if (discount.getMax() <= lastDiscount) {
                throw new OverlappingDiscount(
                    String.format(overlappingMaxDiscount, discount.getMax(), 
                    lastDiscount)
                    );
            } else {
                lastDiscount = discount.getMax();
            }
        }
    }

    /**
     * Check for overlapping min/max discounts, equals are fine, but less than
     * is not.
     * @throws OverlappingDiscount
     */
    private void validateMinMaxDiscounts() throws OverlappingDiscount {
        int lastMaxDiscount = Integer.MAX_VALUE;
        final String overlappingMinMaxDiscount = "The max discount value %s "
            + "cannot be greater than the min discount value %s";

        for (DiscountItem discount : discounts.getDiscountItems()) {
            if (lastMaxDiscount == Integer.MAX_VALUE) {
                lastMaxDiscount = discount.getMax();
            } else if (discount.getMin() < lastMaxDiscount) {
                throw new OverlappingDiscount(
                        String.format(overlappingMinMaxDiscount, 
                        lastMaxDiscount, discount.getMin())
                        );
            } else {
                lastMaxDiscount = discount.getMax();
            }
        }
    }
}
