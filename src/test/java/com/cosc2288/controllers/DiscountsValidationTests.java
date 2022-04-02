package com.cosc2288.controllers;

import com.cosc2288.TestHelpers;
import com.cosc2288.exceptions.OverlappingDiscount;
import com.cosc2288.models.DiscountItem;
import com.cosc2288.models.Discounts;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * DiscountsValidationTests
 *
 * v1.0
 *
 * 2022-03-20
 *
 * © 2022 Matthew Kellock
 */

/** Series of DiscountsValidation tests. */
public class DiscountsValidationTests {

    @Test
    void shouldNotThrowOverlappingDiscountException()
            throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        // Set up the discount items for the test
        // Should be in position 0 with lowest min
        final DiscountItem item1 = new DiscountItem(0, 20, 5);
        // Should be in position 1 due to greater min
        final DiscountItem item2 = new DiscountItem(20, 40, 10);
        // Should be in position 2 due to greater max
        final DiscountItem item3 = new DiscountItem(40, 60, 15);
        LinkedList<DiscountItem> discountItems = new LinkedList<DiscountItem>();

        // Add the default items to the discount item list
        discountItems.addAll(Arrays.asList(item1, item2, item3));

        // Create a new instance of the discounts object and discounts
        // controller
        Discounts discounts = new Discounts();
        discounts.setDiscountItems(discountItems);
        DiscountsValidation discountsValidation =
            new DiscountsValidation(discounts);

        // Assert that we do not receive an exception
        Assertions.assertDoesNotThrow(() -> 
            discountsValidation.validateDiscounts()
        );
    }

    @Test
    void shouldNotThrowOverlappingMaxDiscount()
            throws IllegalAccessException, IllegalArgumentException, 
            InvocationTargetException {
        // Set up the discount items for the test
        // Should be in position 0 with lowest min
        final DiscountItem item1 = new DiscountItem(0, 20, 5);
        // Should be in position 1 due to greater min
        final DiscountItem item2 = new DiscountItem(20, 40, 10);
        // Should be in position 2 due to greater max
        final DiscountItem item3 = new DiscountItem(40, 60, 15);
        LinkedList<DiscountItem> discountItems = new LinkedList<DiscountItem>();

        // Add the default items to the discount item list
        discountItems.addAll(Arrays.asList(item1, item2, item3));

        // Create a new instance of the discounts object and discounts
        // controller
        Discounts discounts = new Discounts();
        discounts.setDiscountItems(discountItems);
        DiscountsValidation discountsValidation =
            new DiscountsValidation(discounts);

        // Retrieve the private method validateMaxLessThanOrEqualToMinDiscounts
        // and make accessible
        Method validateMaxLessThanOrEqualToMinDiscounts = 
            TestHelpers.returnPrivateMethod(
                DiscountsValidation.class,
                "validateMaxLessThanOrEqualToMinDiscounts"
            );
            validateMaxLessThanOrEqualToMinDiscounts.setAccessible(true);

        // Assert that we do not receive an exception
        Assertions.assertDoesNotThrow(() -> 
            validateMaxLessThanOrEqualToMinDiscounts.invoke(
                discountsValidation
            )
        );
    }

    @Test
    void shouldThrowOverlappingMaxDiscount()
            throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        // Set up the discount items for the test
        // Should be in position 0 with lowest min
        final DiscountItem item1 = new DiscountItem(0, 20, 5);
        // Should be in position 1 due to greater min
        final DiscountItem item2 = new DiscountItem(20, 10, 10);
        // Should be in position 2 due to greater max
        final DiscountItem item3 = new DiscountItem(40, 60, 15);
        LinkedList<DiscountItem> discountItems = new LinkedList<DiscountItem>();

        // Add the default items to the discount item list
        discountItems.addAll(Arrays.asList(item1, item2, item3));

        // Create a new instance of the discounts object and discounts
        // controller
        Discounts discounts = new Discounts();
        discounts.setDiscountItems(discountItems);
        DiscountsValidation discountsValidation =
            new DiscountsValidation(discounts);

        // Retrieve the private method validateMaxLessThanOrEqualToMinDiscounts
        // and make accessible
        Method validateMaxLessThanOrEqualToMinDiscounts =
            TestHelpers.returnPrivateMethod(
                DiscountsValidation.class,
                "validateMaxLessThanOrEqualToMinDiscounts"
            );
            validateMaxLessThanOrEqualToMinDiscounts.setAccessible(true);

        // Run the method through a try catch (otherwise we get an
        // InvocationException)
        Class<? extends Throwable> caughtException = null;

        try {
            validateMaxLessThanOrEqualToMinDiscounts.invoke(
                discountsValidation
            );
        } catch (InvocationTargetException ex) {
            if (ex.getCause() instanceof OverlappingDiscount) {
                caughtException = ex.getCause().getClass();
            }
        }

        // Assert that we receive the OverlappingDiscount exception
        Assertions.assertEquals(OverlappingDiscount.class, caughtException);
    }

    @Test
    void shouldNotThrowOverlappingDiscountValidatingMinDiscounts()
            throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        // Set up the discount items for the test
        // Should be in position 0 with lowest min
        final DiscountItem item1 = new DiscountItem(0, 20, 5);
        // Should be in position 1 due to greater min
        final DiscountItem item2 = new DiscountItem(20, 40, 10);
        // Should be in position 2 due to greater max
        final DiscountItem item3 = new DiscountItem(40, 60, 15);
        LinkedList<DiscountItem> discountItems = new LinkedList<DiscountItem>();

        // Add the default items to the discount item list
        discountItems.addAll(Arrays.asList(item1, item2, item3));

        // Create a new instance of the discounts object and discounts
        // controller
        Discounts discounts = new Discounts();
        discounts.setDiscountItems(discountItems);
        DiscountsValidation discountsValidation =
            new DiscountsValidation(discounts);

        // Retrieve the private method validateMinDiscounts and make accessible
        Method validateMaxLessThanOrEqualToMinDiscounts =
            TestHelpers.returnPrivateMethod(
                DiscountsValidation.class,
                "validateMinDiscounts"
            );
            validateMaxLessThanOrEqualToMinDiscounts.setAccessible(true);

        // Assert that we do not receive an exception
        Assertions.assertDoesNotThrow(() ->
            validateMaxLessThanOrEqualToMinDiscounts.invoke(
                discountsValidation)
            );
    }

    @Test
    void shouldThrowOverlappingDiscountValidatingMinDiscounts()
            throws IllegalAccessException, IllegalArgumentException,
                InvocationTargetException {
        // Set up the discount items for the test
        // Should be in position 0 with lowest min
        final DiscountItem item1 = new DiscountItem(0, 20, 5);
        // Should be in position 1 due to greater min
        final DiscountItem item2 = new DiscountItem(20, 40, 10);
        // Should be in position 2 due to greater max
        final DiscountItem item3 = new DiscountItem(20, 60, 15);
        LinkedList<DiscountItem> discountItems = new LinkedList<DiscountItem>();

        // Add the default items to the discount item list
        discountItems.addAll(Arrays.asList(item1, item2, item3));

        // Create a new instance of the discounts object and discounts
        // controller
        Discounts discounts = new Discounts();
        discounts.setDiscountItems(discountItems);
        DiscountsValidation discountsValidation =
            new DiscountsValidation(discounts);

        // Retrieve the private method validateMinDiscounts and make
        // accessible
        Method validateMaxLessThanOrEqualToMinDiscounts =
            TestHelpers.returnPrivateMethod(
                DiscountsValidation.class, "validateMinDiscounts"
            );
            validateMaxLessThanOrEqualToMinDiscounts.setAccessible(true);

        // Run the method through a try catch (otherwise we get an
        // InvocationException)
        Class<? extends Throwable> caughtException = null;

        try {
            validateMaxLessThanOrEqualToMinDiscounts.invoke(
                discountsValidation
            );
        } catch (InvocationTargetException ex) {
            if (ex.getCause() instanceof OverlappingDiscount) {
                caughtException = ex.getCause().getClass();
            }
        }

        // Assert that we receive the OverlappingDiscount exception
        Assertions.assertEquals(OverlappingDiscount.class, caughtException);
    }

    @Test
    void shouldNotThrowOverlappingDiscountValidatingMaxDiscounts()
            throws IllegalAccessException, IllegalArgumentException,
                InvocationTargetException {
        // Set up the discount items for the test
        // Should be in position 0 with lowest min
        final DiscountItem item1 = new DiscountItem(0, 20, 5);
        // Should be in position 1 due to greater min
        final DiscountItem item2 = new DiscountItem(20, 40, 10);
        // Should be in position 2 due to greater max
        final DiscountItem item3 = new DiscountItem(40, 60, 15);
        LinkedList<DiscountItem> discountItems = new LinkedList<DiscountItem>();

        // Add the default items to the discount item list
        discountItems.addAll(Arrays.asList(item1, item2, item3));

        // Create a new instance of the discounts object and discounts
        // controller
        Discounts discounts = new Discounts();
        discounts.setDiscountItems(discountItems);
        DiscountsValidation discountsValidation =
            new DiscountsValidation(discounts);

        // Retrieve the private method validateMaxDiscounts and make accessible
        Method validateMaxLessThanOrEqualToMinDiscounts = 
            TestHelpers.returnPrivateMethod(
                DiscountsValidation.class,
                "validateMaxDiscounts"
            );
            validateMaxLessThanOrEqualToMinDiscounts.setAccessible(true);

        // Assert that we do not receive an exception
        Assertions.assertDoesNotThrow(() ->
            validateMaxLessThanOrEqualToMinDiscounts.invoke(
                discountsValidation)
            );
    }

    @Test
    void shouldThrowOverlappingDiscountValidatingMaxDiscounts()
            throws IllegalAccessException, IllegalArgumentException,
                InvocationTargetException {
        // Set up the discount items for the test
        // Should be in position 0 with lowest min
        final DiscountItem item1 = new DiscountItem(0, 20, 5);
        // Should be in position 1 due to greater min
        final DiscountItem item2 = new DiscountItem(20, 45, 10);
        // Should be in position 2 due to greater max
        final DiscountItem item3 = new DiscountItem(40, 45, 15);
        LinkedList<DiscountItem> discountItems = new LinkedList<DiscountItem>();

        // Add the default items to the discount item list
        discountItems.addAll(Arrays.asList(item1, item2, item3));

        // Create a new instance of the discounts object and discounts
        // controller
        Discounts discounts = new Discounts();
        discounts.setDiscountItems(discountItems);
        DiscountsValidation discountsValidation =
            new DiscountsValidation(discounts);

        // Retrieve the private method validateMaxDiscounts and make accessible
        Method validateMaxLessThanOrEqualToMinDiscounts =
            TestHelpers.returnPrivateMethod(
                DiscountsValidation.class,
                "validateMaxDiscounts"
            );
            validateMaxLessThanOrEqualToMinDiscounts.setAccessible(true);

        // Run the method through a try catch (otherwise we get an
        // InvocationException)
        Class<? extends Throwable> caughtException = null;

        try {
            validateMaxLessThanOrEqualToMinDiscounts.invoke(
                discountsValidation
            );
        } catch (InvocationTargetException ex) {
            if (ex.getCause() instanceof OverlappingDiscount) {
                caughtException = ex.getCause().getClass();
            }
        }

        // Assert that we receive the OverlappingDiscount exception
        Assertions.assertEquals(OverlappingDiscount.class, caughtException);
    }

    @Test
    void shouldNotThrowOverlappingDiscountValidatingMinMaxDiscounts()
            throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        // Set up the discount items for the test
        // Should be in position 0 with lowest min
        final DiscountItem item1 = new DiscountItem(0, 20, 5);
        // Should be in position 1 due to greater min
        final DiscountItem item2 = new DiscountItem(20, 40, 10);
        // Should be in position 2 due to greater max
        final DiscountItem item3 = new DiscountItem(40, 60, 15);
        LinkedList<DiscountItem> discountItems = new LinkedList<DiscountItem>();

        // Add the default items to the discount item list
        discountItems.addAll(Arrays.asList(item1, item2, item3));

        // Create a new instance of the discounts object and discounts
        // controller
        Discounts discounts = new Discounts();
        discounts.setDiscountItems(discountItems);
        DiscountsValidation discountsValidation = new DiscountsValidation(
            discounts
            );

        // Retrieve the private method validateMinMaxDiscounts and make
        // accessible
        Method validateMaxLessThanOrEqualToMinDiscounts =
            TestHelpers.returnPrivateMethod(
                DiscountsValidation.class,
                "validateMinMaxDiscounts"
            );
        validateMaxLessThanOrEqualToMinDiscounts.setAccessible(true);

        // Assert that we do not receive an exception
        Assertions.assertDoesNotThrow(() ->
            validateMaxLessThanOrEqualToMinDiscounts.invoke(
                discountsValidation)
            );
    }

    @Test
    void shouldThrowOverlappingDiscountValidatingMinMaxDiscounts()
            throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        // Set up the discount items for the test
        // Should be in position 0 with lowest min
        final DiscountItem item1 = new DiscountItem(0, 20, 5);
        // Should be in position 1 due to greater min
        final DiscountItem item2 = new DiscountItem(20, 50, 10);
        // Should be in position 2 due to greater max
        final DiscountItem item3 = new DiscountItem(40, 60, 15);
        LinkedList<DiscountItem> discountItems = new LinkedList<DiscountItem>();

        // Add the default items to the discount item list
        discountItems.addAll(Arrays.asList(item1, item2, item3));

        // Create a new instance of the discounts object and discounts
        // controller
        Discounts discounts = new Discounts();
        discounts.setDiscountItems(discountItems);
        DiscountsValidation discountsValidation =
            new DiscountsValidation(discounts);

        // Retrieve the private method validateMinMaxDiscounts and make
        // accessible
        Method validateMaxLessThanOrEqualToMinDiscounts =
            TestHelpers.returnPrivateMethod(
                DiscountsValidation.class,
                "validateMinMaxDiscounts"
            );
            validateMaxLessThanOrEqualToMinDiscounts.setAccessible(true);

        // Run the method through a try catch (otherwise we get an
        // InvocationException)
        Class<? extends Throwable> caughtException = null;

        try {
            validateMaxLessThanOrEqualToMinDiscounts.invoke(
                discountsValidation
            );
        } catch (InvocationTargetException ex) {
            if (ex.getCause() instanceof OverlappingDiscount) {
                caughtException = ex.getCause().getClass();
            }
        }

        // Assert that we receive the OverlappingDiscount exception
        Assertions.assertEquals(OverlappingDiscount.class, caughtException);
    }
}
