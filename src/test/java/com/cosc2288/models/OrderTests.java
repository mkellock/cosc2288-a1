package com.cosc2288.models;

import java.util.Arrays;
import java.util.LinkedList;

import com.cosc2288.models.Restaurant.Category;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class OrderTests {
    private static final String NAME = "Some Restaurant Name";
    private static final Category CATEGORY = Category.RESTAURANT;
    private static final Double DELIVERY_FEE = 5.50;
    private static final RestaurantMenuItem ITEM1 =
        new RestaurantMenuItem("Description 1", 1.00);
    private static final RestaurantMenuItem ITEM2 =
        new RestaurantMenuItem("Description 2", 2.00);
    private static final RestaurantMenuItem ITEM3 =
        new RestaurantMenuItem("Description 3", 3.00);
    private static final LinkedList<RestaurantMenuItem> MENU_ITEMS =
        new LinkedList<RestaurantMenuItem>();
    private static final  Restaurant RESTAURANT =
            new Restaurant(NAME, CATEGORY, DELIVERY_FEE, MENU_ITEMS);
    private static final OrderItem ORDER_ITEM_1 =
        new OrderItem(RESTAURANT, ITEM1, 1);
    private static final OrderItem ORDER_ITEM_2 =
        new OrderItem(RESTAURANT, ITEM2, 2);
    private static final OrderItem ORDER_ITEM_3 =
        new OrderItem(RESTAURANT, ITEM3, 3);
    private static final LinkedList<OrderItem> ORDER_ITEMS =
        new LinkedList<OrderItem>();

    @BeforeAll
    static void init() {
        // Add the restaurant menu items to the constant menu item list
        MENU_ITEMS.addAll(
            Arrays.asList(ITEM1, ITEM2, ITEM3)
        );

        // Add the discount items to the constant discount item list
        ORDER_ITEMS.addAll(
            Arrays.asList(ORDER_ITEM_1, ORDER_ITEM_2, ORDER_ITEM_3)
        );
    }

    @Test
    void shouldConstructAndSetPlusGetOrderItemValues() {
        // New instance of an order
        Order order = new Order();

        // Set order items
        order.setOrderItems(ORDER_ITEMS);

        // Check that the order items match
        Assertions.assertEquals(ORDER_ITEMS, order.getOrderItems());
    }
}
