package api;

import io.restassured.response.Response;

import static config.RestConfig.*;
import static io.restassured.RestAssured.given;

public class UserClient extends RestClient {

    public Response create(DtoUserCreate dtoUserCreate) {
        return defaultRestSpecification()
                .body(dtoUserCreate)
                .when()
                .post(USER_CREATE_HANDLE);
    }

    public Response login(DtoLoginUser dtoLoginUser) {
        return defaultRestSpecification()
                .body(dtoLoginUser)
                .when()
                .post(LOGIN_USER_HANDLE);
    }

    public void deleteUser(String accessToken) {
        given()
                .header("authorization", accessToken)
                .spec(defaultRestSpecification())
                .when()
                .delete(AUTH_USER_HANDLE);
    }


    public void deletingUsersAfterTests(String accessToken) {
        if (accessToken != null) {
            deleteUser(accessToken);
        } else {
            given().spec(defaultRestSpecification())
                    .when()
                    .delete(AUTH_USER_HANDLE);
        }
    }
}
