package com.aptech.myapp.dtos.responses;
import com.aptech.myapp.models.User;

import org.json.JSONObject;
public class LoginResponse {
    private String message;
    private User user;
    private String token;

    public static LoginResponse fromJson(String jsonString) {
        try {
            JSONObject json = new JSONObject(jsonString);
            LoginResponse response = new LoginResponse();
            response.message = json.getString("message");
            JSONObject data = json.getJSONObject("data");

            if (data.has("user")) {
                JSONObject userJson = data.getJSONObject("user");
                response.user = new User(
                        userJson.getInt("id"),
                        userJson.getString("email"),
                        userJson.optString("name"),
                        userJson.optInt("role"),
                        userJson.optString("avatar", null),
                        userJson.optString("phone", null)
                );
            }

            response.token = data.getString("token");
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }
}
