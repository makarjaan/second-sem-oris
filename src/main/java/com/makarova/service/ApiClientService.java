package com.makarova.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class ApiClientService {

    private final String token = "Bearer 8de12263815e53cef3329392ffa1d6957840342fe8aeed3c25384ffedb2fbee2";
    private final String baseUrl = "https://gorest.co.in/public/v2/users/";
    private final String mimeType = "application/json";

    public String sendGetRequest(String id) {
        try {
            URL url = new URL(baseUrl + "?id=" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", mimeType);
            connection.setRequestProperty("Accept", mimeType);
            System.out.println(connection.getResponseCode());
            String response = connection.getResponseMessage() + " " + readResponse(connection);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send GET request", e);
        }
    }

    public String sendPostRequest(String userJson) {
        try {
            System.out.println(userJson);
            URL url = new URL(baseUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", mimeType);
            connection.setRequestProperty("Accept", mimeType);
            connection.setRequestProperty("Authorization", token);
            connection.setDoOutput(true);
            try (OutputStream outputStream = connection.getOutputStream()) {
                byte[] input = userJson.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }
            System.out.println(connection.getResponseCode());
            String response = connection.getResponseMessage() + " " + readResponse(connection);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send POST request", e);
        }
    }

    public String sendPutRequest(String id, String userJson) {
        try {
            URL url = new URL(baseUrl + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", mimeType);
            connection.setRequestProperty("Accept", mimeType);
            connection.setRequestProperty("Authorization", token);
            connection.setDoOutput(true);
            try (OutputStream outputStream = connection.getOutputStream()) {
                byte[] input = userJson.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }
            System.out.println(connection.getResponseCode());
            String response = connection.getResponseMessage() + " " + readResponse(connection);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send PUT request", e);
        }
    }

    public String sendDeleteRequest(String id) {
        try {
            URL url = new URL(baseUrl + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Content-Type", mimeType);
            connection.setRequestProperty("Accept", mimeType);
            connection.setRequestProperty("Authorization", token);
            System.out.println(connection.getResponseCode());
            String response = connection.getResponseMessage() + " " + readResponse(connection);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send DELETE request", e);
        }
    }

    private static String readResponse(HttpURLConnection connection) throws IOException {
        if (connection != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input);
                }
                return content.toString();
            }
        }
        return null;
    }
}
