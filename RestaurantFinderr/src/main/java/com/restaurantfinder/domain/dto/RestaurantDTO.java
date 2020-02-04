package com.restaurantfinder.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.MoreObjects;

public class RestaurantDTO {
    /**
     * RestaurantDTO contains only data.
     * It displays the data to the client.
     *
     * enum Price is only done to get a more clean output.
     */
    public enum Price {
        Free, Inexpensive, Moderate, Expensive, Very_Expensive;
    }

    private String name;
    private Price price;
    private double rating;
    private boolean isOpen;
    private double lat;
    private double lng;

    public RestaurantDTO(String name, int price, double rating, boolean isOpen, double lat,
                         double lng) {
        this.name = name;
        this.price = Price.values()[price];
        this.rating = rating;
        this.isOpen = isOpen;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }

    @JsonIgnore
    public int getPriceNumber() {
        return price.ordinal();
    }

    public double getRating() {
        return rating;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("price", price)
                .add("rating", rating)
                .add("isOpen", isOpen)
                .add("lat", lat)
                .add("lng", lng)
                .toString();
    }
}
