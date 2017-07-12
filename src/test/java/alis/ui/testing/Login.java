package alis.ui.testing;




import org.testng.annotations.Test;
import alis.ui.pages.LoginPage;

public class Login extends BaseTest{

    @Test
    public static void loginToAlis() {

        LoginPage page = new LoginPage();
        page.login("Clerk", "a", "tfl_6631_auto");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
