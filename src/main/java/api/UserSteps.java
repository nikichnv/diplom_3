package api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import lombok.Data;

@Data
public class UserSteps {
    private final UserClient userClient;
    private String accessToken;

    public UserSteps(UserClient userClient) {
        this.userClient = userClient;
    }

    @Step("Создать пользователя")
    public ValidatableResponse create(String email, String password, String name) {
        DtoUserCreate request = new DtoUserCreate(email, password, name);
        request.setEmail(email);
        request.setPassword(password);
        request.setName(name);
        return userClient.create(request).then();
    }

    @Step("Авторизоваться пользователем")
    public ValidatableResponse login(String email, String password) {
        DtoLoginUser request = new DtoLoginUser(email, password);
        request.setEmail(email);
        request.setPassword(password);
        return userClient.login(request).then();
    }

    @Step("Получить токен")
    public String getAccessToken(ValidatableResponse validatableResponse) {
        accessToken = validatableResponse.extract().path("accessToken");
        return accessToken;
    }

    @Step("Удалить пользователей после тестов")
    public void delete() {
        userClient.deletingUsersAfterTests(accessToken);
    }

}
