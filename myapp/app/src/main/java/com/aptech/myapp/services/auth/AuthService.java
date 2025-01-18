package com.aptech.myapp.services.auth;

import android.util.Log;

import com.aptech.myapp.dtos.requests.LoginRequest;
import com.aptech.myapp.dtos.responses.LoginResponse;
import com.aptech.myapp.services.ApiCallback;
import com.aptech.myapp.utils.Utils;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AuthService {
    private static final String TAG = "AuthService";
    private static final String LOGIN_ENDPOINT = "https://your-api-prefix.com/users/login";

    public void login(LoginRequest loginRequest, ApiCallback<LoginResponse> callback) {
        new Thread(() -> {
            try {
                // Set up connection
                URL url = new URL(LOGIN_ENDPOINT);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);
                connection.setDoOutput(true);

                // Create JSON payload
                JSONObject jsonPayload = new JSONObject();
                jsonPayload.put("email", loginRequest.getEmail());
                jsonPayload.put("password", loginRequest.getPassword());

                // Write payload
                try (OutputStream os = connection.getOutputStream()) {
                    os.write(jsonPayload.toString().getBytes());
                    os.flush();
                }

                // Handle response
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    String responseJson = Utils.readStream(connection.getInputStream());
                    LoginResponse response = LoginResponse.fromJson(responseJson);
                    callback.onSuccess(response);
                } else {
                    String errorJson = Utils.readStream(connection.getErrorStream());
                    callback.onError("Error: " + responseCode + " - " + errorJson);
                }
            } catch (Exception e) {
                Log.e(TAG, "Login error", e);
                callback.onError(e.getMessage());
            }
        }).start();
    }
}