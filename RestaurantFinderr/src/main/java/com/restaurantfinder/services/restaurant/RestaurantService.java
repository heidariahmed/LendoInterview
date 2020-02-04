package com.restaurantfinder.services.restaurant;
import com.restaurantfinder.domain.dto.RestaurantDTO;
import com.restaurantfinder.domain.requests.FindRestaurantsRequest;
import java.util.List;

/**
 *  Interface for Restaurant request. This is done for simple modify in the future.
 */

public interface RestaurantService {
    List<RestaurantDTO> findRestaurantsNearLocation(FindRestaurantsRequest request);
}
