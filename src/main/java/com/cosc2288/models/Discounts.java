package com.cosc2288.models;

/**
 * Discounts
 *
 * v1.0
 *
 * 2022-03-20
 *
 * © 2022 Matthew Kellock
 */

import java.util.LinkedList;

/**
 * The Discounts object is used in the collation and computation of discounts.
 */
public class Discounts {
    private LinkedList<DiscountItem> discountItems;
    private int deliveryDiscountRestaurantCount;
    private int deliveryDiscountRestaurantPercent;

    /**
     * Property to retrieve the discount items.
     * @return  Collection of discount items
     */
    public LinkedList<DiscountItem> getDiscountItems() {
        return this.discountItems;
    }

    /**
     * Property to set the discount items.
     * @param discountItems Collection of discount items
     */
    public void setDiscountItems(LinkedList<DiscountItem> discountItems) {
        this.discountItems = discountItems;
    }

    /**
     * Property to set the number of deliveries needed to apply a discount
     * @param count
     */
    public void setDeliveryDiscountRestaurantCount(int count) {
        deliveryDiscountRestaurantCount = count;
    }

    /**
     * Property to retrieve the number of deliveries needed to apply a discount
     * @return  The number of deliveries needed
     */
    public int getDeliveryDiscountRestaurantCount() {
        return deliveryDiscountRestaurantCount;
    }

    /**
     * Property to set the discount percentage when a discount is applied
     * @param percent
     */
    public void setDeliveryDiscountRestaurantPercent(int percent) {
        deliveryDiscountRestaurantPercent = percent;
    }

    /**
     * Property to retrieve the discount percentage when a discount is applied
     * @return  The discount percentage
     */
    public int getDeliveryDiscountRestaurantPercent() {
        return deliveryDiscountRestaurantPercent;
    }
}
