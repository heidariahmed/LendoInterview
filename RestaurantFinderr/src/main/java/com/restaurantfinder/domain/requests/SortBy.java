package com.restaurantfinder.domain.requests;

public enum SortBy
{
    price("price"),
    rating("rating"),
    name("name");

    private String item;

    SortBy(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }
}