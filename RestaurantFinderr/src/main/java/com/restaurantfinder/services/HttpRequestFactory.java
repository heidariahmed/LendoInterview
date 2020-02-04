package com.restaurantfinder.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * A additional class for making a http request and parsing the result.
 */
public class HttpRequestFactory {

    /**
     *  ObjectMapper is a jsonparser. By making it static we only creating it once.
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Factory function that creates new http -requests.
     * @return
     */
    public static HttpRequest buildRequest() {
        return new HttpRequest(objectMapper.reader());
    }

    /**
     * HttpRequest is a request-object. This class executes a request towards a server.
     *
     */
    public static class HttpRequest implements AutoCloseable {
        private final ObjectReader reader;
        private HttpsURLConnection connection;
        private InputStream inputStream;

        private HttpRequest(ObjectReader reader) {
            this.reader = reader;
        }
        // Open a socket to communicate. Using GET method, and get the response.
        public int doGet(String url) throws MalformedURLException, IOException {
            connection = (HttpsURLConnection) new URL(url).openConnection();

            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            inputStream = connection.getInputStream();

            return responseCode;
        }

        /**
         * Get the request result and parse it as an object.
         *<T> T is returned by the parser, T is the class that is specified. This is done
         * to be typesafe.
         * @return Body of the response serialized to the specified object type.
         * @throws IOException If an IOException occurs
         */
        public <T> T respons(Class<T> type) throws IOException {
            if (inputStream == null) {
                throw new IOException("no connection");
            }
            return reader.forType(type).readValue(inputStream);

        }

        @Override
        public void close() throws Exception {
            if (inputStream != null) {
                inputStream.close();

            }

        }

    }
}
