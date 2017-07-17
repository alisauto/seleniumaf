package alis.ui.testing;


import alis.ui.operations.Driver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    public static void setup(){
        Driver.getInstance();
        //Driver.getBaseUrl();
    }

    @AfterSuite
    public static void cleanup(){
        //Driver.closeBrowser();
    }

}
