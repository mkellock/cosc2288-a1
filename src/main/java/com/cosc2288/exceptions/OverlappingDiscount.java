package com.cosc2288.exceptions;

/**
 * OverlappingDiscount
 *
 * v1.0
 *
 * 2022-03-20
 *
 * © 2022 Matthew Kellock
 */

/** Exception for discounts that have overlapping ranges. */
public class OverlappingDiscount extends Exception {
    /**
     * Initialises the OverlappingDiscount exception
     * @param message   The exception message
     */
    public OverlappingDiscount(String message) {
        super(message);
    }
}
