package com.example.demo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class HttpController {
    /*
    Makes an HTTP GET Request to the given URI address, returns the response body
     */
    public static HttpResponse<String> getRequest(String uri) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json; charset=UTF-8")
                .GET()
                .build();

        try {

            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    /*
     Makes an HTTP PUT Request to the given URI address without any additional information, return the response body
      */
    public static HttpResponse<String> putRequest(String uri) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json; charset=UTF-8")
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    /*
    Makes an HTTP PUT Request to the given URI address with given arguments/parameters, returns the response body.
    args needs to be a Map<String, Object> where the key is the name of the argument/parameter and the value is the
     value of the given parameter
     */
    public static HttpResponse<String> putRequestWithArgs(String uri, Map<String, Object> args) {

        String argsString = argsFromMap(args);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri +"?"+ argsString))
                .header("Content-Type", "application/json; charset=UTF-8")
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    Makes a HTTP PUT Request to the given URI address with a JSON body, returns the response body
    The body needs to be formatted as a JSON string.
     */
    public static HttpResponse<String> putRequestWithBody(String uri, String json) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json; charset=UTF-8")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    /*
Makes a HTTP PUT Request to the given uri adress with a x-www-form-urlencoded data, returns the response body
The body needs to be formated as a json string.
*/
    public static HttpResponse<String> putRequestWithData(String uri, Map<String, Object> args) {
        String data = argsFromMap(args);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .PUT(HttpRequest.BodyPublishers.ofString(data))
                .build();

        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    /*
    Makes a HTTP POST Request to the given uri adress without any additional information, return the response body
  */
    public static HttpResponse<String> postRequest(String uri) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json; charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    /*
      Makes an HTTP POST Request to the given URI address with given arguments/parameters, returns the response
       body
      args needs to be a Map<String, Object> where the key is the name of the argument/parameter and the value is the
       value of the given parameter
       */
    public static HttpResponse<String> postRequestWithArgs(String uri, Map<String, Object> args) {

        String argsString = argsFromMap(args);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri +"?"+ argsString))
                .header("Content-Type", "application/json; charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    /*
     Makes a HTTP POST Request to the given uri adress with a json body, returns the response body
     The body needs to be formated as a json string.
      */
    public static HttpResponse<String> postRequestWithBody(String uri, String jsonBody) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json; charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    /*
 Makes a HTTP POST Request to the given URI address with an x-www-form-urlencoded data, returns the response body
 The body needs to be formatted as a JSON string.
  */
    public static HttpResponse<String> postRequestWithData(String uri, Map<String, Object> args) {
        String data = argsFromMap(args);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();

        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static HttpResponse<String> postRequestWithDataString(String uri, String arg) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(arg))
                .build();

        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    /*
    Converts the Map<String, Object> to a String format that can be sent as a parameter in an HTTP Request
     */
    public static String argsFromMap(Map<String, Object> args) {
        StringBuilder argsString = new StringBuilder();
        for (String arg : args.keySet()) {
            argsString.append(arg).append("=").append(args.get(arg).toString()).append("&");
        }
        return argsString.toString();
    }

}
