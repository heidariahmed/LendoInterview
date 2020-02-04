package com.restaurantfinder.controllers;

import com.restaurantfinder.domain.dto.RestaurantDTO;
import com.restaurantfinder.domain.requests.SortBy;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class RestaurantControllerTest {


    @Test
    public void testSortList() {
        List<RestaurantDTO> testList = new ArrayList<>();

        testList.add(new RestaurantDTO("Test1", 2, 3.4, true, 59.06, 18.03));
        testList.add(new RestaurantDTO("Test2", 1, 3.0, true, 59.06, 18.03));
        testList.add(new RestaurantDTO("Test3", 3, 3.2, true, 59.06, 18.03));
        List<RestaurantDTO> sortedList = RestaurantController.sortList(testList, SortBy.price);
        Assert.assertEquals(1, sortedList.get(0).getPriceNumber());
        Assert.assertEquals(2, sortedList.get(1).getPriceNumber());
        Assert.assertEquals(3, sortedList.get(2).getPriceNumber());
    }

    @Test
    public void testSortListFail(){
        List<RestaurantDTO> testList = new ArrayList<>();

        testList.add(new RestaurantDTO("Test1", 2, 3.4, true, 59.06, 18.03));
        testList.add(new RestaurantDTO("Test2", 1, 3.0, true, 59.06, 18.03));
        testList.add(new RestaurantDTO("Test3", 3, 3.2, true, 59.06, 18.03));
        List<RestaurantDTO> sortedList = RestaurantController.sortList(testList, SortBy.name);
        Assert.assertNotEquals(1, sortedList.get(0).getPriceNumber());
        Assert.assertNotEquals(2, sortedList.get(1).getPriceNumber());
        Assert.assertNotEquals(4, sortedList.get(2).getPriceNumber());

    }


}
