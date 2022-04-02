package com.cosc2288.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * OverlappingDiscountTests
 *
 * v1.0
 *
 * 2022-03-20
 *
 * © 2022 Matthew Kellock
 */

/** Series of OverlappingDiscount tests. */
public class OverlappingDiscountTests {

    @Test
    void shouldCreateExceptionAndReturnDescription() {
        final String exceptionMessage = "Test description";

        OverlappingDiscount overlappingDiscounts =
            new OverlappingDiscount(exceptionMessage);
        Assertions.assertEquals(
            exceptionMessage,
            overlappingDiscounts.getMessage()
        );
    }
}
