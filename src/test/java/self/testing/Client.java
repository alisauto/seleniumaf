package self.testing;

import alis.ui.operations.*;
import alis.ui.testing.BaseTest;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import alis.ui.pages.LoginPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;

import java.util.Random;

public class Client {
    //private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private static WebDriver driver = Driver.getInstance();
    private WebDriverWait wait = new WebDriverWait(driver, 10);
    private static String projectDir = System.getProperty("user.dir");

    String path =  projectDir + "\\testData\\clientData.xlsx" ;
    String sheetName = "Client_Details";
    String sTestCaseName;
    int iTestCaseRow;

    @Test
    public void login () throws Exception{
        Login page = new Login();
        page.loginToAlis();
    }

    @Test
    public void testClient() throws Exception {



        ExcelUtils.setExcelFile(path,sheetName);
        // sTestCaseName = excelObject.getTestCaseName(this.toString());
        iTestCaseRow = ExcelUtils.getRowContains("Client TFL",1);

        Object[][] testDataArray = ExcelUtils.getTableArray(path,sheetName,iTestCaseRow);
        String URL = ExcelUtils.getCellData(iTestCaseRow,"URL");


        LoginPage page = new LoginPage();
        Menus menu = new Menus();
        //menu.navigateTomenu("client,new person");
        String Navigate = ExcelUtils.getCellData(iTestCaseRow,"Navigate");

        menu.navigateTomenu(ExcelUtils.getCellData(iTestCaseRow,"Navigate"));
        page.waitForPageLoad();
        Thread.sleep(5000);


        Random rn = new Random();
        int rand = rn.nextInt(9999)+1;
        ManageWebElements.setElementValue("ClientDetails/FirstName",ExcelUtils.getCellData(iTestCaseRow,"First Name"));
        ManageWebElements.setElementValue("ClientDetails/MiddleName",ExcelUtils.getCellData(iTestCaseRow,"MiddleName"));
        ManageWebElements.setElementValue("ClientDetails/Surname",ExcelUtils.getCellData(iTestCaseRow,"Surname"));
        ManageWebElements.setComboValue("ClientDetails/Gender",ExcelUtils.getCellData(iTestCaseRow,"Gender"));
        ManageWebElements.setElementValue("ClientDetails/DateOfBirth",ExcelUtils.getCellData(iTestCaseRow,"Date Of Birth"));
        ManageWebElements.clickElement("gwt-uid-1");

        // Address Table
        ManageWebElements.clickElement("address/addAddress");
        ManageWebElements.setComboValue("address/addressTypeValue",ExcelUtils.getCellData(iTestCaseRow,"Address Type"));
        ManageWebElements.setElementValue("address/AddressLine1",ExcelUtils.getCellData(iTestCaseRow,"Address Line 1"));
        ManageWebElements.setElementValue("address/City",ExcelUtils.getCellData(iTestCaseRow,"City"));
        ManageWebElements.setComboValue("address/states",ExcelUtils.getCellData(iTestCaseRow,"State"));
        ManageWebElements.setElementValue("address/freeText",ExcelUtils.getCellData(iTestCaseRow,"Zip Code"));

        // Address Payment
        ManageWebElements.clickElement("PaymentArrangementGrid/addedArr");
        ManageWebElements.setComboValue("PaymentArrangementGrid/companies",ExcelUtils.getCellData(iTestCaseRow,"Company"));
        ManageWebElements.setComboValue("PaymentArrangementGrid/method",ExcelUtils.getCellData(iTestCaseRow,"Method"));
        ManageWebElements.setComboValue("PaymentArrangementGrid/frequency",ExcelUtils.getCellData(iTestCaseRow,"Frequency"));

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
