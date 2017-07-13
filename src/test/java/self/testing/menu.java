package self.testing;

import java.util.List;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import alis.ui.operations.Driver;
import alis.ui.testing.Login;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class menu extends Login {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

   /** @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        //driver = new FirefoxDriver();

        /**System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "http://alis-fnb-app02:8082/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Login login = new Login();
        Login.loginToAlis();

    }*/

    @Test
    public void testMenu() throws Exception {

         boolean iFoundMenue = false;

         //String sMenuPath = "Client,New Person";

          String sMenuPath = "Tools,Follow Up,Follow Up Query";


        String []arrMenu =  sMenuPath.split(",");

        WebDriver driver = Driver.getInstance();

        WebElement menuCol = driver.findElement(By.xpath(".//*[@id='alis-menu']"));
        List <WebElement> menuItem = menuCol.findElements(By.className("v-menubar-menuitem-caption"));

        int aa = menuItem.size();
        ///WebElement elm = menuItem.get(6);

        for ( WebElement item: menuItem) {
            String val=item.getAttribute("innerText");
            System.out.println(val);
            if(val.equalsIgnoreCase(arrMenu[0])) {
                System.out.println("found "+ val);
                item.click();

                driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
                iFoundMenue = true;
            }
        }


        if(iFoundMenue) {

            for (int iItem = 1; iItem <= arrMenu.length - 1; iItem++) {
                iFoundMenue = false;
                List<WebElement> submenus = driver.findElements(By.className("v-menubar-popup"));
                WebElement menu = submenus.get(submenus.size()-1 );

                //v-menubar-submenu v-menubar-submenu-spml-menubar
                List<WebElement> iTems = menu.findElements(By.className("v-menubar-menuitem-caption"));

                for (WebElement item : iTems) {
                    String val = item.getAttribute("innerText");
                    System.out.println(val);

                    if(val.equalsIgnoreCase(arrMenu[iItem])) {
                        System.out.println("found " + val);
                        item.click();
                        driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
                        iFoundMenue = true;
                    }

                }
                //.

                if(!iFoundMenue) break;
            }

        }






        if(iFoundMenue){


            //.v-menubar-submenu.v-menubar-submenu-spml-menubar

        }




        //driver.findElement(By.cssSelector("span.v-menubar-menuitem-caption")).click();
       // driver.findElement(By.cssSelector("div.v-menubar-submenu.v-menubar-submenu-spml-menubar > span.v-menubar-menuitem > span.v-menubar-menuitem-caption")).click();
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
