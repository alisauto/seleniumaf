package self.testing;

import alis.ui.operations.ExcelUtils;
import alis.ui.testing.BaseTest;
import org.testng.annotations.Test;
import alis.ui.pages.LoginPage;

public class Login extends BaseTest {

    private static String projectDir = System.getProperty("user.dir");
    @Test
    public void loginToAlis() throws Exception {
        //test
        String path =  projectDir + "\\testData\\LoginData.xlsx" ;
        String sheetName = "LogIn";
        String sTestCaseName;
        int iTestCaseRow;

        ExcelUtils excelObject = new ExcelUtils();
        excelObject.setExcelFile(path,sheetName);

       // sTestCaseName = excelObject.getTestCaseName(this.toString());

        iTestCaseRow = excelObject.getRowContains("LogIn Alis TFL",1);
        //iTestCaseRow = excelObject.getRowContains("LogIn Alis AF",1);

        Object[][] testDataArray = excelObject.getTableArray(path,sheetName,iTestCaseRow);

        String URL = excelObject.getCellData(iTestCaseRow,"URL");
        String userName = excelObject.getCellData(iTestCaseRow,"UserName");
        String password= excelObject.getCellData(iTestCaseRow,"Password");
        String dataBase= excelObject.getCellData(iTestCaseRow,"DataBase");

        /**String URL = excelObject.getCellData(iTestCaseRow,2);
         String userName = excelObject.getCellData(iTestCaseRow,3);
         String password= excelObject.getCellData(iTestCaseRow,4);
         String dataBase= excelObject.getCellData(iTestCaseRow,5);*/

        LoginPage page = new LoginPage();
        page.login(URL, userName, password,dataBase);
        page.waitForPageLoad();
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
