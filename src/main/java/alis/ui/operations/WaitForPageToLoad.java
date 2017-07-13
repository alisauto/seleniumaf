package alis.ui.operations;

/**
 * Created by amid.k on 7/13/2017.
 */

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class WaitForPageToLoad {
    private int timeToWait = 100;
/**
    /**
     * Overrides the default time to wait (in milliseconds) after a page has finished loading.
     * @param timeToWait the time to wait, in milliseconds, after the page has finished loading

    public void setTimeToWait(int timeToWait) {
        this.timeToWait = timeToWait;
    }

     public Void handleSeleneseCommand(final WebDriver driver, String timeout, String ignored) {
        // Wait until things look like they've been stable for "timeToWait"
        if (!(driver instanceof JavascriptExecutor)) {
            // Assume that we Do The Right Thing
            return null;
        }
            long started = System.currentTimeMillis();

            public boolean until(){
                try {
                    Object result = ((JavascriptExecutor) driver).executeScript(
                            "return document['readyState'] ? 'complete' == document.readyState : true");
                    if (result != null && result instanceof Boolean && (Boolean) result) {
                        long now = System.currentTimeMillis();
                        if (now - started > timeToWait) {
                            return true;
                        }
                    } else {
                        started = System.currentTimeMillis();
                    }
                } catch (Exception e) {
                    // Possible page reload. Fine
                }
                return false;
            }
        return null;
    }*/
}
