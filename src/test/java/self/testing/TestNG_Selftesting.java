package self.testing;

import org.testng.annotations.Test;
import alis.ui.operations.Driver;


/**
 * Created by emaiz on 7/7/2017.
 */
public class TestNG_Selftesting {

    @Test
    public static void startBrowserTest(){

        Driver.getInstance();
       // Driver.getBaseUrl();
        Driver.closeBrowser();

    }
}
