package com.restaurantfinder.services.restaurant;

import com.restaurantfinder.domain.dto.RestaurantDTO;
import com.restaurantfinder.domain.requests.FindRestaurantsRequest;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.restaurantfinder.services.restaurant.GoogleLocationResult.Result;
import com.restaurantfinder.services.HttpRequestFactory;
import com.restaurantfinder.services.HttpRequestFactory.HttpRequest;
import com.restaurantfinder.services.exceptions.ServiceException;

/**
 *  This class ask google for data, check if the data is OK --> get the result that is parsed from google.
 */
public class RestaurantServiceImpl implements RestaurantService {

    private static final String GOOGLE_API_KEY = System.getenv("GoogleApiKey");
    private static final String GOOGLE_ENDPOINT = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%f,%f&radius=%d&type=restaurant&key=%s";

    @Override
    public List<RestaurantDTO> findRestaurantsNearLocation(FindRestaurantsRequest request) {
        try (HttpRequest req = HttpRequestFactory.buildRequest()) {
            int status = req.doGet(getURLString(request.getLat(), request.getLng(), request.getRadius()));

            if (status == 200) {
                GoogleLocationResult result = req.respons(GoogleLocationResult.class);
                return result.getResults().stream()
                        .filter(this::isOpen)
                        .map(this::convertResultToRestaurant)
                        .collect(Collectors.toList());

            } else {
                throw new ServiceException(String.format("Error response from server status code: %d", status));
            }

        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    private boolean isOpen(Result r) {
        return r.getOpening_hours() != null && r.getOpening_hours().isOpen_now();
    }

    private RestaurantDTO convertResultToRestaurant(Result r) {
        return new RestaurantDTO(r.getName(), r.getPrice_level(), r.getRating(), r.getOpening_hours().isOpen_now(), r.getGeometry().getLocation().getLat(), r.getGeometry().getLocation().getLng());
    }

    private String getURLString(double lat, double lng, int radius) {
        return String.format(Locale.US, GOOGLE_ENDPOINT, lat, lng, radius, GOOGLE_API_KEY);
    }

}


