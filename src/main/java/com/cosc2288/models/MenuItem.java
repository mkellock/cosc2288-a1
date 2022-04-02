package com.cosc2288.models;

/**
 * MenuItem
 *
 * v1.0
 *
 * 2022-03-20
 *
 * © 2022 Matthew Kellock
 */

/** MenuItem object is used in the creation of UI menus. */
public class MenuItem {
    private final String title;

    /**
     * Initialises a menu item
     * @param title The menu item's title
     */
    public MenuItem(String title) {
        this.title = title;
    }

    /**
     * Retrieves the menu item's title
     * @return  The menu item's title
     */
    public String getTitle() {
        return this.title;
    }
}
