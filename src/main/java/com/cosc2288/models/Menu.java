package com.cosc2288.models;

/**
 * Menu
 *
 * v1.0
 *
 * 2022-03-20
 *
 * © 2022 Matthew Kellock
 */

import java.util.LinkedList;

/** Menu object is used in the creation of UI menus. */
public class Menu {
    private final String menuTitle;
    private final LinkedList<MenuItem> menuItems;

    /**
     * Initialises the menu with a collection of menu items
     * @param menuItems
     */
    public Menu(String menuTitle, LinkedList<MenuItem> menuItems) {
        this.menuTitle = menuTitle;
        this.menuItems = menuItems;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    /**
     * Retrieves the menu items
     * @return  The list of menu items
     */
    public LinkedList<MenuItem> getMenuItems() {
        return this.menuItems;
    }
}
