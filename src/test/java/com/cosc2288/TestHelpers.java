package com.cosc2288;

import java.lang.reflect.Method;

/**
 * TestHelpers
 *
 * v1.0
 *
 * 2022-03-20
 *
 * © 2022 Matthew Kellock
 */

/** Methods to help unit test operations. */
public abstract class TestHelpers {
    /**
     * Retrieves a method instance based on method name, this is a workaround
     * for getDeclaredMethod which seems to be having issues finding private
     * methods.
     */
    public static <T> Method returnPrivateMethod(
        Class<T> object, String methodName) {
        Method method = null;

        // For some reason, getDeclaredMethod
        // doesn't seem to work as expected, workaround below to find the method
        // instance
        for (int i = 0; i < object.getDeclaredMethods().length; i++) {
            if (object.getDeclaredMethods()[i].getName().equals(methodName)) {
                method = object.getDeclaredMethods()[i];
                break;
            }
        }

        return method;
    }
}
