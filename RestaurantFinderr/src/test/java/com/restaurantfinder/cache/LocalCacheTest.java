package com.restaurantfinder.cache;

import org.junit.Assert;
import org.junit.Test;

public class LocalCacheTest {

    private LocalCache cache = new LocalCache();

    @Test
    public void testCache() {
        cache.add("test", "cached", 10000L);
        Assert.assertEquals("cached", cache.get("test"));
    }

    @Test
    public void testCacheExpired() throws InterruptedException {
        cache.add("test", "cached", 1000L);
        Thread.sleep(2000L);
        Assert.assertNotEquals("cached", cache.get("test"));
    }

}
