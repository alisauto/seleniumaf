package alis.ui.testing;



import org.testng.annotations.Test;
import alis.ui.pages.LoginPage;

public class Login extends BaseTest{

    @Test
    public static void loginToAlis() throws InterruptedException {
        //test
        LoginPage page = new LoginPage();
        page.login("Clerk", "a", "tfl_6631_auto");
        page.waitForPageLoad();
        try {
          Thread.sleep(100);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
