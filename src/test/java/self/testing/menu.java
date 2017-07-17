package self.testing;

import alis.ui.operations.Menus;
import alis.ui.pages.LoginPage;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;

public class menu extends Login {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Test
    public void testMenu() throws Exception {

         boolean ifoundmenue = false;
        LoginPage page = new LoginPage();
        Menus menu = new Menus();
        menu.navigateTomenu("Tools,Follow Up,Follow Up Query");
        page.waitForPageLoad();
        Thread.sleep(1000);

        menu.navigateTomenu("Policy,New application");
        page.waitForPageLoad();
        Thread.sleep(1000);



        menu.navigateTomenu("client,new person");
        page.waitForPageLoad();
        Thread.sleep(1000);


        if(ifoundmenue){
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
