package utils;

import config.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestBuilder {

    public static RequestSpecification getRequest() {
        return new RequestSpecBuilder()
                .setContentType("application/json")
                .addHeader("x-api-key", ConfigManager.get("api.key"))
                .addFilter(new RequestLoggingFilter(System.out))
                .addFilter(new ResponseLoggingFilter(System.out))
                .build();
    }
}
