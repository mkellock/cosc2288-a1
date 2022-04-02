package com.cosc2288.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * MenuItemTests
 *
 * v1.0
 *
 * 2022-03-20
 *
 * © 2022 Matthew Kellock
 */

/** Series of MenuItem tests. */
public class MenuItemTests {
    private static final String TITLE = "Item 1";

    @Test
    void shouldConstructAndGetMenuItemValues() {
        MenuItem menuItem = new MenuItem(TITLE);
        Assertions.assertEquals(TITLE, menuItem.getTitle());
    }
}
