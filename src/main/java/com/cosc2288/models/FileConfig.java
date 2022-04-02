package com.cosc2288.models;

/**
 * FileConfig
 *
 * v1.0
 *
 * 2022-03-20
 *
 * © 2022 Matthew Kellock
 */

/**
 * The FileConfig object is used in storing and retriving the location of
 * configuration files.
 */
public class FileConfig {
    private String discountsPath;
    private String restaurantsPath;

    /**
     * Sets the path of the discounts file.
     * @param path  The path to the discounts file
     */
    public void setDiscountsPath(String path) {
        discountsPath = path;
    }

    /**
     * Gets the path of the discounts file.
     * @return  The path to the discounts file
     */
    public String getDiscountsPath() {
        return discountsPath;
    }

    /**
     * Sets the path of the restaurants file.
     * @param path  The path to the restaurants file
     */
    public void setRestaurantsPath(String path) {
        restaurantsPath = path;
    }

    /**
     * Gets the path of the restaurants file.
     * @return  The path to the restaurants file
     */
    public String getRestaurantsPath() {
        return restaurantsPath;
    }
}
