package com.restaurantfinder.services.restaurant;

import com.google.common.base.MoreObjects;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Contains data result from google.
 * Jackson parses the json text to this class.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleLocationResult {
    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("results", results)
                .toString();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Geometry {
        private Location location;

        public Location getLocation() {
            return location;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("location", location)
                    .toString();
        }
    }

    public static class Location {
        private double lat;
        private double lng;

        public double getLat() {
            return lat;
        }

        public double getLng() {
            return lng;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("lat", lat)
                    .add("lng", lng)
                    .toString();
        }
    }

    public static class OpenHours {
        boolean open_now;

        public boolean isOpen_now() {
            return open_now;
        }


        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("open_now", open_now)
                    .toString();
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result {
        private Geometry geometry;
        private String name;
        private OpenHours opening_hours;
        private int price_level;
        private double rating;

        public Geometry getGeometry() {
            return geometry;
        }

        public String getName() {
            return name;
        }

        public OpenHours getOpening_hours() {
            return opening_hours;
        }

        public int getPrice_level() {
            return price_level;
        }


        public double getRating() {
            return rating;
        }


        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("geometry", geometry)
                    .add("name", name)
                    .add("opening_hours", opening_hours)
                    .add("price_level", price_level)
                    .add("rating", rating)
                    .toString();
        }
    }

}