package com.cosc2288.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * FileConfigTests
 *
 * v1.0
 *
 * 2022-03-20
 *
 * © 2022 Matthew Kellock
 */

/** Series of FileConfig tests. */
class FileConfigTests {
    private static final String DISCOUNTS_PATH = "/Some/Path/Discounts.csv";
    private static final String RESTAURANTS_PATH = "/Some/Path/Discounts.csv";

    @Test
    void shouldConstructAndSetPlusGetFileConfigValues() {
        FileConfig fileConfig = new FileConfig();

        // Set the paths
        fileConfig.setDiscountsPath(DISCOUNTS_PATH);
        fileConfig.setRestaurantsPath(RESTAURANTS_PATH);

        // Check the path values
        Assertions.assertEquals(DISCOUNTS_PATH, fileConfig.getDiscountsPath());
        Assertions.assertEquals(
            RESTAURANTS_PATH,
            fileConfig.getRestaurantsPath()
        );
    }
}
