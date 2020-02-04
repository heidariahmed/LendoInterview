package com.restaurantfinder.domain.requests;

/**
 *Request that comes from client to backend
 * Latitude and longitude most be chosen.
 * Radius does not has to be chosen, default value is 100.
 * sortBy does not have to be chosen, default value is sortBy "price"
 */
public class FindRestaurantsRequest {
	
	private double lat;
	private double lng;
	private int radius;
	private SortBy sortBy;

	public FindRestaurantsRequest(double lat, double lng, int radius, SortBy sortBy) {
		this.lat = lat;
		this.lng = lng;
		this.radius=radius;
		this.sortBy=sortBy;
	}

	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}

	public int getRadius() {
		return radius;
	}

	public SortBy getSortBy() {
		return sortBy;
	}

	@Override
	public String toString() {
		return "FindRestaurantsRequest{" +
				"lat=" + lat +
				", lng=" + lng +
				", radius=" + radius +
				", sortBy=" + sortBy +
				'}';
	}
}
