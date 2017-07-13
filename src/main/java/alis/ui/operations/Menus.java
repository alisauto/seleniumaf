package alis.ui.operations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by amid.k on 7/13/2017.
 */
public class Menus extends Driver{
    private static WebDriver driver = Driver.getInstance();


    public void navigateTomenu(String sMenuPath)throws Exception{

        boolean ifoundmenue = false;
        //String sMenuPath = "Client,New Person";
        //String sMenuPath = "Tools,Follow Up,Follow Up Query";

        String []arrMenu =  sMenuPath.split(",");

        WebDriver driver = Driver.getInstance();
        WebElement menuCol = driver.findElement(By.xpath(".//*[@id='alis-menu']"));
        List<WebElement> menuItem = menuCol.findElements(By.className("v-menubar-menuitem-caption"));

        //int aa = menuItem.size();
        ///WebElement elm = menuItem.get(6);

        for ( WebElement item: menuItem) {
            String val=item.getAttribute("innerText");
            System.out.println(val);
            if(val.equalsIgnoreCase(arrMenu[0])) {
                System.out.println("found "+ val);
                item.click();

                driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                ifoundmenue = true;
                break;
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
                        break;
                    }

                }
                if(!ifoundmenue) break;
            }

        }

    }

}
