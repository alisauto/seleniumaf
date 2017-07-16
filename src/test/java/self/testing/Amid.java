package self.testing;

import alis.ui.operations.ExcelUtils;

/**
 * Created by amid.k on 7/16/2017.
 */
public class Amid {

    private static String projectDir = System.getProperty("user.dir");
    private static String sTestCaseName;
    private static int iTestCaseRow;


    public static void main(String args[]) throws Exception {

       String path =  projectDir + "\\testData\\testData.xlsx" ;
       String sheetName = "LogIn";

        ExcelUtils.setExcelFile(path,sheetName);
       // sTestCaseName = ExcelUtils.getTestCaseName(this.toString());
        /**int col = ExcelUtils.getRowByHeader("UserName");
        int col1 = ExcelUtils.getRowByHeader("usrName");*/


        iTestCaseRow = ExcelUtils.getRowContains("LogIn Alis 1",1);
        Object[][] testDataArray = ExcelUtils.getTableArray(path,sheetName,iTestCaseRow);
        int i = testDataArray.length;

        System.out.println("");

    }

}
