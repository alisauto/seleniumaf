WebDriverWait wait = new WebDriverWait(driver, 10);
WebElement element = wait.until(
        ExpectedConditions.visibilityOfElementLocated(By.id("someid")));


WebElement myDynamicElement = (new WebDriverWait(driver, 10))
.until(ExpectedConditions.presenceOfElementLocated(By.id("myDynamicElement")));


WebDriverWait wait = new WebDriverWait(driver, 10);
WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("someid")));


private boolean isElementPresent(By by) {
        try {
        driver.findElement(by);
        return true;
        } catch (NoSuchElementException e) {
        return false;
        }
        }
        for (int second = 0;; second++) {
        if (second >= 60){
        fail("timeout");
        }
        try {
        if (isElementPresent(By.id("someid"))){
        break;
        }
        }
        catch (Exception e) {

        }
        Thread.sleep(1000);
        }