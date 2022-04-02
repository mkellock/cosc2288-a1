package com.cosc2288.models;

/**
 * DiscountItem
 *
 * v1.0
 *
 * 2022-03-20
 *
 * © 2022 Matthew Kellock
 */

/** DiscountItem object is used in the creation of applicable discounts. */
public class DiscountItem {
    private final int min;
    private final int max;
    private final int percent;

    /**
     * Initialises the discount item with min range for discount value, and the
     * percentage discount to be applied.
     * @param min       The minimum price range for the discount
     * @param percent   The discount percentage to be applied
     */
    public DiscountItem(int min, int percent) {
        this.min = min;
        this.max = Integer.MAX_VALUE;
        this.percent = percent;
    }

    /**
     * Initialises the discount item with min range for discount value, max
     * range for discount value, and the percentage discount to be applied.
     * @param min       The minimum price range for the discount
     * @param max       The maximum (exclusive) price range for the discount
     * @param percent   The discount percentage to be applied
     */
    public DiscountItem(int min, int max, int percent) {
        this.min = min;
        this.max = max;
        this.percent = percent;
    }

    /**
     * Returns the minimum price range for the discount
     * @return  The minimum price range for the discount
     */
    public int getMin() {
        return min;
    }

    /**
     * Returns the maximum price range for the discount
     * @return  The maximum price range for the discount
     */
    public int getMax() {
        return max;
    }

    /**
     * Returns the percentage price range for the discount
     * @return  The percentage price range for the discount
     */
    public int getPercent() {
        return percent;
    }
}
