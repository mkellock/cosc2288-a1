package com.cosc2288.models;

/**
 * MenuTests
 *
 * v1.0
 *
 * 2022-03-20
 *
 * © 2022 Matthew Kellock
 */

import java.util.Arrays;
import java.util.LinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/** Series of Menu tests. */
class MenuTests {
    private static final String MENU_TITLE = "Menu Title";
    private static final MenuItem ITEM_1 = new MenuItem("Item 1");
    private static final MenuItem ITEM_2 = new MenuItem("Item 2");
    private static final MenuItem ITEM_3 = new MenuItem("Item 3");
    private static final LinkedList<MenuItem> MENU_ITEMS =
        new LinkedList<MenuItem>();

    @BeforeAll
    static void init() {
        // Add the menu items to the constant menu item list
        MENU_ITEMS.addAll(Arrays.asList(ITEM_1, ITEM_2, ITEM_3));
    }

    @Test
    void shouldConstructAndGetMenuValues() {
        Menu menu = new Menu(MENU_TITLE, MENU_ITEMS);
        Assertions.assertEquals(MENU_TITLE, menu.getMenuTitle());
        Assertions.assertEquals(MENU_ITEMS, menu.getMenuItems());
    }
}
