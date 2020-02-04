package com.restaurantfinder.api;

import com.restaurantfinder.domain.dto.RestaurantDTO;
import com.restaurantfinder.domain.requests.FindRestaurantsRequest;
import com.restaurantfinder.domain.requests.SortBy;
import com.restaurantfinder.controllers.RestaurantController;
import com.restaurantfinder.exceptions.ApiException;
import com.restaurantfinder.exceptions.BadRequestException;
import com.restaurantfinder.exceptions.ServerException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Recieves data, check if valid then returns the data
 */
@RestController
@RequestMapping(value = "/api/v1/restaurants", method = RequestMethod.GET)
public class RestaurantEndpoint {

    private RestaurantController restaurantController;

    public RestaurantEndpoint() {
        this.restaurantController = new RestaurantController();
    }

    @GetMapping
    public List<RestaurantDTO> findRestaurants(
            @RequestParam(name = "lat", required = true) double lat,
            @RequestParam(name = "lng", required = true) double lng,
            @RequestParam(name = "radius", required = false, defaultValue = "100") int radius,
            @RequestParam(name = "sortBy", required = false, defaultValue = "price") SortBy sortBy)
            throws Exception
    {
        FindRestaurantsRequest request = new FindRestaurantsRequest(lat, lng, radius, sortBy);
        try {
            isValidFindRestaurantRequest(request);
            return restaurantController.findRestaurants(request);
        } catch (ApiException e) {
            //ApiExceptions are considered safe to expose to clients
            throw e;
        } catch (Exception e) {
            //Re-throw non-api exceptions since we don't want to expose them to clients
            throw new ServerException();
        }
    }

    private static void isValidFindRestaurantRequest(FindRestaurantsRequest request) {

        if (request.getLat() < -180 || request.getLat() > 180) {
            throw new BadRequestException("Lat value needs to be between -180 and 180");
        }

        if (request.getLng() < -90 || request.getLng() > 90) {
            throw new BadRequestException("Lng value needs to be between -90 and 90");
        }

        if (request.getRadius() < 10 || request.getRadius() > 500) {

            throw new BadRequestException("Radius value needs to be between 10 and 500");
        }
    }
}
