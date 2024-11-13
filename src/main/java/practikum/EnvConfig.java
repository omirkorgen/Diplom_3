package practikum;

public class EnvConfig {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    public static final int IMPLICIT_WAIT = 5;
    public static final int EXPLICIT_WAIT = 15;
    public static void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);  // Преобразуем секунды в миллисекунды
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
