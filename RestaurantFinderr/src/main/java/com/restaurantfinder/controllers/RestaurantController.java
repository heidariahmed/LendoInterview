package com.restaurantfinder.controllers;

import com.restaurantfinder.domain.dto.RestaurantDTO;
import com.restaurantfinder.domain.requests.FindRestaurantsRequest;
import com.restaurantfinder.domain.requests.SortBy;
import com.restaurantfinder.cache.LocalCache;
import com.restaurantfinder.exceptions.BadRequestException;
import com.restaurantfinder.exceptions.ServerException;
import com.restaurantfinder.services.exceptions.ServiceException;
import com.restaurantfinder.services.restaurant.RestaurantService;
import com.restaurantfinder.services.restaurant.RestaurantServiceImpl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Get the result by checking the cache, if cache is empty then get the result from google.
 */
public class RestaurantController {

    private RestaurantService restaurantService;
    private LocalCache cache;

    public RestaurantController() {
        this.restaurantService = new RestaurantServiceImpl();
        this.cache = new LocalCache();
    }

    public List<RestaurantDTO> findRestaurants(FindRestaurantsRequest request) {
        try {
            String key = request.toString();
            List<RestaurantDTO> result = (List<RestaurantDTO>) cache.get(key);

            if (result == null) {
                List<RestaurantDTO> restaurantResult = restaurantService.findRestaurantsNearLocation(request);
                List<RestaurantDTO> sortedList = sortList(restaurantResult, request.getSortBy());

                cache.add(key, sortedList, 600000L);

                return sortedList;
            } else {
                return result;
            }
        } catch (ServiceException e) {
            throw new ServerException();
        } catch (IllegalArgumentException e) {
            throw new BadRequestException();
        }
    }

    // This is public only to be tested, i prefer it to be private.
    public static List<RestaurantDTO> sortList(List<RestaurantDTO> restaurants, SortBy sortBy) {
        Stream<RestaurantDTO> convertedList = restaurants.stream();
        switch (sortBy) {
            case name:
                return convertedList
                        .sorted(Comparator.comparing(RestaurantDTO::getName))
                        .collect(Collectors.toList());
            case rating:
                return convertedList
                        .sorted(Comparator.comparingDouble(RestaurantDTO::getRating))
                        .collect(Collectors.toList());
            default:
                return convertedList
                        .sorted(Comparator.comparingInt(RestaurantDTO::getPriceNumber))
                        .collect(Collectors.toList());
        }
    }

}
