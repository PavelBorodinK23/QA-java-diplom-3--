package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.User;

import static io.restassured.RestAssured.given;

public class AuthClient {
    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site";

    static {
        RestAssured.baseURI = BASE_URI;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    public static Response register(User user) {
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/api/auth/register");
    }

    public static Response login(User user) {
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/api/auth/login");
    }

    public static Response delete(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user");
    }

    public static String extractAccessToken(Response response) {
        return response.jsonPath().getString("accessToken");
    }

    public static void registerUser(User testUser) {

    }
}