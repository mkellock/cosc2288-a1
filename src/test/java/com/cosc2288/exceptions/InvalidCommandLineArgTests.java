package com.cosc2288.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * InvalidCommandLineArgTests
 *
 * v1.0
 *
 * 2022-03-20
 *
 * © 2022 Matthew Kellock
 */

/** Series of InvalidCommandLineArg tests. */
class InvalidCommandLineArgTests {

    @Test
    void shouldCreateExceptionAndReturnDescription() {
        final String exceptionMessage = "Test description";

        InvalidCommandLineArg overlappingDiscounts =
            new InvalidCommandLineArg(exceptionMessage);
        Assertions.assertEquals(
            exceptionMessage,
            overlappingDiscounts.getMessage()
        );
    }
}
