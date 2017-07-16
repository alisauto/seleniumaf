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


    /**     //String sMenuPath = "Client,New Person";
        String sMenuPath = "Tools,Follow Up,Follow Up Query";
        String []arrMenu =  sMenuPath.split(",");

        WebDriver driver = Driver.getInstance();
        WebElement menuCol = driver.findElement(By.xpath(".//*[@id='alis-menu']"));
        List <WebElement> menuItem = menuCol.findElements(By.className("v-menubar-menuitem-caption"));

        //int aa = menuItem.size();
        ///WebElement elm = menuItem.get(6);

        for ( WebElement item: menuItem) {
            String val=item.getAttribute("innerText");
            System.out.println(val);
            if(val.equalsIgnoreCase(arrMenu[0])) {
                System.out.println("found "+ val);
                item.click();

                driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
                ifoundmenue = true;
            }
        }

        if(ifoundmenue) {
            for (int iItem = 1; iItem <= arrMenu.length - 1; iItem++) {
                ifoundmenue = false;
                List<WebElement> submenus = driver.findElements(By.className("v-menubar-popup"));
                WebElement menu = submenus.get(submenus.size()-1 );
                List<WebElement> items = menu.findElements(By.className("v-menubar-menuitem-caption"));

                for (WebElement item : items) {
                    String val = item.getAttribute("innerText");
                    System.out.println(val);

                    if(val.equalsIgnoreCase(arrMenu[iItem])) {
                        System.out.println("found " + val);
                        item.click();
                        driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
                        ifoundmenue = true;
                    }

                }
                if(!ifoundmenue) break;
            }

        }*/

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
