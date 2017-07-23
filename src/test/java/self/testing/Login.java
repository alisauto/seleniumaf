package self.testing;

import alis.ui.operations.Driver;
import alis.ui.operations.ExcelUtils;
import alis.ui.testing.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import alis.ui.pages.LoginPage;

import java.util.Properties;

public class Login extends BaseTest {

    private static String projectDir = System.getProperty("user.dir");
    private WebDriverWait wait = new WebDriverWait(Driver.getInstance(), 60);
    @Test
    public void loginToAlis() throws Exception {
        //test
        String path =  projectDir + "\\testData\\LoginData.xlsx" ;
        String sheetName = "LogIn";
        String sTestCaseName;
        int iTestCaseRow=-1;


        ExcelUtils.setExcelFile(path,sheetName);
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
                iTestCaseRow = ExcelUtils.getRowContains("LogIn Alis TFL",1);
            case 1:
                // sTestCaseName = ExcelUtils.getTestCaseName(this.toString());
                iTestCaseRow = ExcelUtils.getRowContains("LogIn Alis AF",1);
        }

        // sTestCaseName = excelObject.getTestCaseName(this.toString());
        //iTestCaseRow = ExcelUtils.getRowContains("LogIn Alis TFL",1);
        //iTestCaseRow = excelObject.getRowContains("LogIn Alis AF",1);

        Assert.assertNotEquals(iTestCaseRow,-1);
        Object[][] testDataArray = ExcelUtils.getTableArray(path,sheetName,iTestCaseRow);

        String URL = ExcelUtils.getCellData(iTestCaseRow,"URL");
        String userName = ExcelUtils.getCellData(iTestCaseRow,"UserName");
        String password= ExcelUtils.getCellData(iTestCaseRow,"Password");
        String dataBase= ExcelUtils.getCellData(iTestCaseRow,"DataBase");

        /**String URL = excelObject.getCellData(iTestCaseRow,2);
         String userName = excelObject.getCellData(iTestCaseRow,3);
         String password= excelObject.getCellData(iTestCaseRow,4);
         String dataBase= excelObject.getCellData(iTestCaseRow,5);*/

        LoginPage page = new LoginPage();
        page.login(URL, userName, password,dataBase);
        page.waitForPageLoad();
        // boolean iWait = wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("v-loading-indicator"))));

        Object iWait =wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("v-loading-indicator")));

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
