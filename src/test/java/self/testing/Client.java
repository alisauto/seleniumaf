package self.testing;

import alis.ui.operations.Driver;
import alis.ui.operations.FindPageElements;
import alis.ui.operations.ManageWebElements;
import alis.ui.testing.BaseTest;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import alis.ui.operations.Menus;
import alis.ui.pages.LoginPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;

import java.util.Random;

public class Client{
    //private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private static WebDriver driver = Driver.getInstance();
    private WebDriverWait wait = new WebDriverWait(driver, 10);

    @Test
    public void login () throws Exception{
        Login page = new Login();
        page.loginToAlis();
    }

    @Test
    public void testClient() throws Exception {

        LoginPage page = new LoginPage();
        Menus menu = new Menus();
        menu.navigateTomenu("client,new person");
        page.waitForPageLoad();
        Thread.sleep(5000);

        Random rn = new Random();
        int rand = rn.nextInt(9999)+1;
        ManageWebElements.setElementValue("ClientDetails/FirstName","John"+Integer.toString(rand));
        ManageWebElements.setElementValue("ClientDetails/MiddleName","Firstinsured");
        ManageWebElements.setElementValue("ClientDetails/Surname","Fifty");
        ManageWebElements.setComboValue("ClientDetails/Gender","male");
        ManageWebElements.setElementValue("ClientDetails/DateOfBirth","01011980");
        ManageWebElements.clickElement("gwt-uid-1");

        // Address Table
        ManageWebElements.clickElement("address/addAddress");
        ManageWebElements.setComboValue("address/addressTypeValue","Physical");
        ManageWebElements.setElementValue("address/AddressLine1","Main Street");
        ManageWebElements.setElementValue("address/City","Nashville");
        ManageWebElements.setComboValue("address/states","TN Tennessee");
        ManageWebElements.setElementValue("address/freeText","12345");

        // Address Table
        ManageWebElements.clickElement("PaymentArrangementGrid/addedArr");

        //Save
        ManageWebElements.clickElement("ClientDetails/buttonsave");

        // close popup
       // driver.findElement(By.xpath("//div[@id='confirm.ok']/span/span")).click();

       // Close
       // driver.findElement(By.xpath("//div[@id='ClientDetails/close']/span/span")).click();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        //driver.quit();
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
