package com.cosc2288.models;

import java.util.Arrays;
import java.util.LinkedList;

import com.cosc2288.models.Restaurant.Category;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/** Series of OrderTests tests. */
class OrderTests {
    private static final DiscountItem ITEM_1 = new DiscountItem(0, 20, 5);
    private static final DiscountItem ITEM_2 = new DiscountItem(20, 40, 10);
    private static final DiscountItem ITEM_3 = new DiscountItem(40, 60, 15);
    private static final DiscountItem ITEM_4 = new DiscountItem(60, 20);
    private static final LinkedList<DiscountItem> DISCOUNT_ITEMS =
        new LinkedList<DiscountItem>();
    private static final int DELIVERY_DISCOUNT_RESTAURANT_COUNT = 2;
    private static final int DELIVERY_DISCOUNT_RESTAURANT_PERCENTAGE = 25;
    private static final Discounts DISCOUNTS = new Discounts();

    private static final String NAME = "Some Restaurant Name";
    private static final Category CATEGORY = Category.RESTAURANT;
    private static final Double DELIVERY_FEE = 5.50;
    private static final RestaurantMenuItem ITEM_1_1 =
        new RestaurantMenuItem("Description 1", 5.0);
    private static final RestaurantMenuItem ITEM_1_2 =
        new RestaurantMenuItem("Description 2", 2.5);
    private static final RestaurantMenuItem ITEM_1_3 =
        new RestaurantMenuItem("Description 3", 1.25);
    private static final LinkedList<RestaurantMenuItem> MENU_ITEMS_1 =
        new LinkedList<RestaurantMenuItem>();

    private static final RestaurantMenuItem ITEM_2_1 =
        new RestaurantMenuItem("Description 1", 4.0);
    private static final RestaurantMenuItem ITEM_2_2 =
        new RestaurantMenuItem("Description 2", 1.35);
    private static final RestaurantMenuItem ITEM_2_3 =
        new RestaurantMenuItem("Description 3", 3.0);
    private static final LinkedList<RestaurantMenuItem> MENU_ITEMS_2 =
        new LinkedList<RestaurantMenuItem>();

    private static final  Restaurant RESTAURANT_1 =
            new Restaurant(NAME, CATEGORY, DELIVERY_FEE, MENU_ITEMS_1);
    private static final  Restaurant RESTAURANT_2 =
            new Restaurant(NAME, CATEGORY, DELIVERY_FEE, MENU_ITEMS_2);

    private static final OrderItem ORDER_ITEM_1 =
        new OrderItem(RESTAURANT_1, ITEM_1_1, 1);
    private static final OrderItem ORDER_ITEM_2 =
        new OrderItem(RESTAURANT_1, ITEM_1_2, 2);
    private static final OrderItem ORDER_ITEM_3 =
        new OrderItem(RESTAURANT_1, ITEM_1_3, 3);

    private static final OrderItem ORDER_ITEM_4 =
        new OrderItem(RESTAURANT_2, ITEM_2_1, 4);
    private static final OrderItem ORDER_ITEM_5 =
        new OrderItem(RESTAURANT_2, ITEM_2_2, 5);
    private static final OrderItem ORDER_ITEM_6 =
        new OrderItem(RESTAURANT_2, ITEM_2_3, 6);

    private static final LinkedList<OrderItem> ORDER_ITEMS =
        new LinkedList<OrderItem>();

    @BeforeAll
    static void init() {
        // Add the discount items to the constant discount item list
        DISCOUNT_ITEMS.addAll(Arrays.asList(ITEM_1, ITEM_2, ITEM_3, ITEM_4));

        // Set the discounts
        DISCOUNTS.setDiscountItems(DISCOUNT_ITEMS);
        DISCOUNTS.setDeliveryDiscountRestaurantCount(
            DELIVERY_DISCOUNT_RESTAURANT_COUNT
        );
        DISCOUNTS.setDeliveryDiscountRestaurantPercent(
            DELIVERY_DISCOUNT_RESTAURANT_PERCENTAGE
        );

        // Add the restaurant menu items to the constant menu item list
        MENU_ITEMS_1.addAll(
            Arrays.asList(ITEM_1_1, ITEM_1_2, ITEM_1_3)
        );
        MENU_ITEMS_2.addAll(
            Arrays.asList(ITEM_2_1, ITEM_2_2, ITEM_2_3)
        );

        // Add the discount items to the constant discount item list
        ORDER_ITEMS.addAll(
            Arrays.asList(ORDER_ITEM_1, ORDER_ITEM_2, ORDER_ITEM_3,
                ORDER_ITEM_4, ORDER_ITEM_5, ORDER_ITEM_6
            )
        );
    }

    @Test
    void shouldConstructAndSetPlusGetOrderValues() {
        // New instance of an order
        Order order = new Order(DISCOUNTS);

        // Add an order item
        for (OrderItem orderItem : ORDER_ITEMS) {
            order.addOrderItem(orderItem);
        }

        // Check that the order items match
        Assertions.assertEquals(
            ORDER_ITEMS.size(),
            order.getOrderItems().size()
        );

        for (OrderItem orderItem : ORDER_ITEMS) {
            Assertions.assertTrue(order.getOrderItems().contains(orderItem));
        }

        // Check that the restaurant matches
        Assertions.assertEquals(
            Arrays.asList(RESTAURANT_1, RESTAURANT_2),
            order.getResturants()
        );

        // Assert that the order price is correct
        Assertions.assertEquals(46.33, order.orderPrice());

        // Assert that the delivery fee is correct
        Assertions.assertEquals(8.25, order.deliveryFee());

        // Assert that the total is correct
        Assertions.assertEquals(54.58, order.total());

        // Assert that the savings is correct
        Assertions.assertEquals(10.92, order.savings());
    }
}
