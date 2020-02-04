# Restaurant API

# How to start with docker

1. docker build . -t lendo
2. docker run -p 8080:8080 -e "KEY" lendo

## Introduction
The application uses Google Places API. This is needed to find the restaurants 
within a certain radius. With parameters as latitude, longitude, radius the application is going to give
a list with the restaurant found within the radius.  The application also uses a local cache, and it uses cache for atleast 
two reasons. 

1. When sending request to Google API it cost money. With the local cache the client does´nt have to send anymore request 
to google,it stores the data for 60s. This can be modified if wanted.
2. When sending a request the first time it takes roughly 432ms to get the response, with the local cache
it narrows it down to about 13ms. There we see a huge different in time. 
## Methods
To find restaurants the application needs a latitude and a longitude, a radius and a sortBy parameter.
If no radius is selected the default value of a radius is '100m'.

A client can also choose to get the result sorted by price, name and rating. If none is chosen 
the default value for sorting is 'price'. 

### Restaurants
*Method:GET

*URL:/api/v1/restaurants/{lat}&{lng}&{radius}&{sortBy}

*Sucess Response: 200 OK, Content: Result of a list that contains name of the restaurant, price,
rating,latitude and longitude value and if the restaurant is open or not.

*Error Response: 400 Bad Request with a message to the client, 500 Internal Server Error with error code and message.

### Input exceptions
Latitude has to be between -180 and 180. 
Longitude has to be between -90 and 90.
Radius has to be between 10 and 500. Radius can be modified as wanted, just remember that it takes more time if a bigger request 
to Googles Places API is done.

### Examples

Sucessful response that is sorted by rating:
```
  {
        "name": "McDonald's",
        "price": "Inexpensive",
        "rating": 3.4,
        "lat": 59.33312649999999,
        "lng": 18.0642486,
        "open": true
    },
    {
        "name": "Pong Asian Restaurant",
        "price": "Moderate",
        "rating": 3.7,
        "lat": 59.3302332,
        "lng": 18.0640253,
        "open": true
    },
    {
        "name": "Ristorante Rodolfino since 1972",
        "price": "Moderate",
        "rating": 4.0,
        "lat": 59.3258217,
        "lng": 18.0669153,
        "open": true
    },
    {
        "name": "Berns",
        "price": "Free",
        "rating": 4.1,
        "lat": 59.3322436,
        "lng": 18.0733643,
        "open": true
    },
    {
        "name": "Pickwick Restaurang & Pub",
        "price": "Moderate",
        "rating": 4.1,
        "lat": 59.3291948,
        "lng": 18.0663622,
        "open": true
    },
    {
        "name": "Lebanon Meza Lounge",
        "price": "Moderate",
        "rating": 4.1,
        "lat": 59.33304669999999,
        "lng": 18.0740911,
        "open": true
    }
```

Example Bad Request response with radius to big.
```
{
    "timestamp": 1580676485920,
    "status": 400,
    "error": "Bad Request",
    "exception": "com.restaurantfinder.exceptions.BadRequestException",
    "message": "Radius value needs to be between 10 and 500",
    "path": "/api/v1/restaurants"
}
```
