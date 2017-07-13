package alis.ui.operations;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Driver {

    //Amid_13072017
    //private static Driver instance = null;
    private static WebDriver driver = null;
    private static String projectDir = System.getProperty("user.dir");
    private static Properties props = loadProperties();

    protected Driver(){}

    private static Properties loadProperties(){
        Properties props = new Properties();
        String propertiesFile = projectDir + "\\src\\main\\config.properties";
        try{
            FileInputStream fs = new FileInputStream(propertiesFile);
            props.load(fs);
        }catch (IOException e){
            System.out.println (e.toString());
            System.out.println("Could not find file " + propertiesFile);
        }

        return props;
    }

    public static WebDriver getInstance(){

        String browser = props.getProperty("browser");

        if(driver == null){
            if(browser.equalsIgnoreCase("chrome")){
                System.setProperty("webdriver.chrome.driver", projectDir + "\\drivers\\chromedriver.exe");
                driver = new ChromeDriver();
            }
            else if(browser.equalsIgnoreCase("firefox")){
                System.setProperty("webdriver.gecko.driver", projectDir + "\\drivers\\geckodriver.exe");
                driver = new FirefoxDriver();
            }
            else if(browser.equalsIgnoreCase("ie")){
                System.setProperty("webdriver.ie.driver", projectDir + "\\drivers\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        }
        return driver;
    }

    public static void getBaseUrl(){
        driver.get(props.getProperty("baseUrl"));
    }

    public static void closeBrowser(){
        driver.quit();
    }
}