package practikum.user;

import io.restassured.response.ValidatableResponse;

public class UserService {
    private UserClient client; // Ваш клиент API
    private UserChecks check; // Проверки пользователя
    private User user; // Пользователь, который будет удален

    // Конструктор для инициализации клиента, проверок и пользователя
    public UserService(UserClient client, UserChecks check, User user) {
        this.client = client;
        this.check = check;
        this.user = user;
    }

    public void deleteUser() {
        var creds = UserCredentials.fromUser(user);
        ValidatableResponse response = client.login(creds);
        int statusCode = response.extract().statusCode();
        if (statusCode == 200) {
            String accessToken = check.checkLogin(response);
            if (accessToken != null) {
                ValidatableResponse responseDelete = client.deleteUser(accessToken);
                String message = check.deletedSuccessfully(responseDelete);
                assert message.contains("User successfully removed");
            }
        }

    }
}
