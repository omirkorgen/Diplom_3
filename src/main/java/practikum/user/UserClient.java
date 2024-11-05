package practikum.user;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import static practikum.EnvConfig.BASE_URL;
import static io.restassured.RestAssured.given;

public class UserClient {

    @Step("Отправка запроса на создания юзера")
    public ValidatableResponse createUser(User user) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(user)
                .when()
                .post("api/auth/register")
                .then();
    }

    @Step("Отпрака запроса на удаления юзера по accessToken")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .contentType(ContentType.JSON)
                .auth().oauth2(accessToken)
                .baseUri(BASE_URL)
                .when()
                .delete("api/auth/user")
                .then();
    }

    @Step("Отпрака запроса на логин юзера")
    public ValidatableResponse login(UserCredentials userCredentials) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(userCredentials)
                .when()
                .post("api/auth/login")
                .then();
    }
}
