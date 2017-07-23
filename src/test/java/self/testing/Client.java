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

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {
    //private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private static WebDriver driver = Driver.getInstance();
    private WebDriverWait wait = new WebDriverWait(driver, 60);
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

        Properties props= Driver.loadProperties();
        ExcelUtils.setExcelFile(path,sheetName);

        String str = props.getProperty("Project");

        //0 : TFL
        //1 : AF
        int iType = 0;
        if(str.equals("AF"))
            iType++;

        switch (iType){
            case 0:
                // sTestCaseName = excelObject.getTestCaseName(this.toString());
                iTestCaseRow = ExcelUtils.getRowContains("Client TFL",1);
            case 1:
                // sTestCaseName = excelObject.getTestCaseName(this.toString());
                iTestCaseRow = ExcelUtils.getRowContains("Client AF",1);
        }

        Object[][] testDataArray = ExcelUtils.getTableArray(path,sheetName,iTestCaseRow);
        String URL = ExcelUtils.getCellData(iTestCaseRow,"URL");

        LoginPage page = new LoginPage();
        Menus menu = new Menus();
        //menu.navigateTomenu("client,new person");
        String Navigate = ExcelUtils.getCellData(iTestCaseRow,"Navigate");

        menu.navigateTomenu(ExcelUtils.getCellData(iTestCaseRow,"Navigate"));
        page.waitForPageLoad();
        // Object iw =wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("v-loading-indicator")));

        Thread.sleep(5000);

        Random rn = new Random();
        int rand = rn.nextInt(99)+1;
        String rnd = Integer.toString(rand);
        ManageWebElements.setElementValue("ClientDetails/FirstName",ExcelUtils.getCellData(iTestCaseRow,"First Name")+rnd);
        ManageWebElements.setElementValue("ClientDetails/MiddleName",ExcelUtils.getCellData(iTestCaseRow,"MiddleName"));
        ManageWebElements.setElementValue("ClientDetails/Surname",ExcelUtils.getCellData(iTestCaseRow,"Surname"));
        ManageWebElements.setComboValue("ClientDetails/Gender",ExcelUtils.getCellData(iTestCaseRow,"Gender"));
        ManageWebElements.setElementValue("ClientDetails/DateOfBirth",ExcelUtils.getCellData(iTestCaseRow,"Date Of Birth"));
        ManageWebElements.clickElement("gwt-uid-1"); // no need for AF

        // Address Table
        ManageWebElements.clickElement("address/addAddress"); //address/global.res.addRow
        ManageWebElements.setComboValue("address/addressTypeValue",ExcelUtils.getCellData(iTestCaseRow,"Address Type")); //address/addressType/0/0
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
        boolean iWait = wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("v-loading-indicator"))));

        // close popup
        WebElement elemnt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirm.ok")));

        // WebElement notificatrion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("v-label v-label-error-alert-message error-alert-message")));
        // WebElement notificatrion = driver.findElement(By.className("v-label v-label-error-alert-message error-alert-message"));

        //WebElement notificatrion = driver.findElement(By.id("confirm.dialog"));
        WebElement notificatrion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirm.dialog")));
        String Str =notificatrion.getText();

        Pattern p = Pattern.compile("[0-9]+");
        List<String> numbers = new ArrayList<String>();
        Matcher m = p.matcher(Str);
        while (m.find()) {
            //int n = Integer.parseInt(m.group());
            numbers.add(m.group());
        }

        System.out.println(numbers);

        //  WebElement elemnt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirm.ok")));
        ManageWebElements.clickElement(elemnt);
        // driver.findElement(By.xpath("//div[@id='confirm.ok']/span/span")).click();

        // Close
        ManageWebElements.clickElement("ClientDetails/close");
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
