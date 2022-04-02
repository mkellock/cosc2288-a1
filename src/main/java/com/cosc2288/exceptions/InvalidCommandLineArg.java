package com.cosc2288.exceptions;

/**
 * InvalidCommandLineArg
 *
 * v1.0
 *
 * 2022-03-20
 *
 * © 2022 Matthew Kellock
 */

/** Exception for invalid command line arguements that dont conform to the
 * expected inputs. */
public class InvalidCommandLineArg extends Exception {
    /**
     * Initialises the InvalidCommandLineArg exception
     * @param message   The exception message
     */
    public InvalidCommandLineArg(String message) {
        super(message);
    }
}
