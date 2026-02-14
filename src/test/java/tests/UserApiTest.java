package tests;

import base.BaseTest;
import io.restassured.response.Response;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.LoggerUtil;
import utils.RequestBuilder;

import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class UserApiTest extends BaseTest {

    private static final Logger log = LoggerUtil.getLogger(UserApiTest.class);

    private String userId;
    private User user = new User("Mostafa", "QA Engineer", 30);

    // ===================== Positive Scenarios =====================

    @Test(priority = 1)
    public void createUser() {
        log.info("=== Creating new user ===");

        try {
            Response response = given()
                    .spec(RequestBuilder.getRequest())
                    .body(user)
                    .when()
                    .post("/api/users")
                    .then()
                    .extract().response();

            log.info("Create User Response: " + response.getBody().asString());
            Assert.assertEquals(response.statusCode(), 201, "User creation status code mismatch");

            userId = response.path("id").toString();
            log.info("Created User ID: " + userId);
            Assert.assertNotNull(userId, "User ID should not be null");

        } catch (Exception e) {
            log.error("Create user failed", e);
            Assert.fail("Create user failed: " + e.getMessage());
        }
    }

    @Test(priority = 2, dependsOnMethods = "createUser")
    public void retrieveUser() {
        log.info("=== Retrieving user with ID: " + userId + " ===");

        try {
            Response response = given()
                    .spec(RequestBuilder.getRequest())
                    .pathParam("id", userId)
                    .when()
                    .get("/api/users/{id}")
                    .then()
                    .extract().response();

            log.info("Retrieve User Response: " + response.getBody().asString());
            Assert.assertEquals(response.statusCode(), 200, "User retrieval status code mismatch");

        } catch (Exception e) {
            log.error("Retrieve user failed", e);
            Assert.fail("Retrieve user failed: " + e.getMessage());
        }
    }

    @Test(priority = 3, dependsOnMethods = "retrieveUser")
    public void updateUser() {
        log.info("=== Updating user job to Senior QA Engineer ===");

        try {
            user.setJob("Senior QA Engineer");

            Response response = given()
                    .spec(RequestBuilder.getRequest())
                    .pathParam("id", userId)
                    .body(user)
                    .when()
                    .put("/api/users/{id}")
                    .then()
                    .extract().response();

            log.info("Update User Response: " + response.getBody().asString());
            Assert.assertEquals(response.statusCode(), 200, "User update status code mismatch");

            String updatedJob = response.path("job");
            Assert.assertEquals(updatedJob, "Senior QA Engineer", "Job update mismatch");

        } catch (Exception e) {
            log.error("Update user failed", e);
            Assert.fail("Update user failed: " + e.getMessage());
        }
    }

    // ===================== Negative Scenarios =====================

    @Test(priority = 4)
    public void createUserWithInvalidBody() {
        log.info("=== Creating user with invalid body ===");

        try {
            User invalidUser = new User("","QA Engineer",30); // empty object, missing fields
            Response response = given()
                    .spec(RequestBuilder.getRequest())
                    .body(invalidUser)
                    .when()
                    .post("/api/users")
                    .then()
                    .extract().response();

            log.info("Create User Invalid Response: " + response.getBody().asString());
            Assert.assertTrue(response.statusCode() >= 400, "Expected failure status code for invalid body");

        } catch (Exception e) {
            log.error("Create user with invalid body failed", e);
            Assert.fail("Create user with invalid body failed: " + e.getMessage());
        }
    }

    @Test(priority = 5)
    public void retrieveNonExistentUser() {
        log.info("=== Retrieving user with non-existent ID ===");

        try {
            String nonExistId = "999999";
            Response response = given()
                    .spec(RequestBuilder.getRequest())
                    .pathParam("id", nonExistId)
                    .when()
                    .get("/api/users/{id}")
                    .then()
                    .extract().response();

            log.info("Retrieve Non-Existent User Response: " + response.getBody().asString());
            Assert.assertEquals(response.statusCode(), 404, "Expected 404 for non-existent user");

        } catch (Exception e) {
            log.error("Retrieve non-existent user failed", e);
            Assert.fail("Retrieve non-existent user failed: " + e.getMessage());
        }
    }

    @Test(priority = 6)
    public void updateNonExistentUser() {
        log.info("=== Updating user with non-existent ID ===");

        try {
            User tempUser = new User("Temp", "Tester", 25);
            String nonExistId = "999999";

            Response response = given()
                    .spec(RequestBuilder.getRequest())
                    .pathParam("id", nonExistId)
                    .body(tempUser)
                    .when()
                    .put("/api/users/{id}")
                    .then()
                    .extract().response();

            log.info("Update Non-Existent User Response: " + response.getBody().asString());
            Assert.assertEquals(response.statusCode(), 404, "Expected 404 for updating non-existent user");

        } catch (Exception e) {
            log.error("Update non-existent user failed", e);
            Assert.fail("Update non-existent user failed: " + e.getMessage());
        }
    }
}
