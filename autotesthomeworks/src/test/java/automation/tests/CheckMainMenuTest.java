package automation.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by kotionokyurievna on 09.11.2018.
 */
public class CheckMainMenuTest {

    public static void main (String[] args){
        WebDriver driver = getDriver();
        driver.manage().window().maximize();
        driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");

        //Login to admin panel
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("webinar.test@gmail.com");

        WebElement pesswordField = driver.findElement(By.id("passwd"));
        pesswordField.sendKeys("Xcg7299bnSmMuRLp9ITw");

        WebElement submitLoginButton = driver.findElement(By.name("submitLogin"));
        submitLoginButton.click();


        WebElement currentPageBeforeReload;
        WebElement currentPageAfterReload;
        String currentPageBeforeReloadTitle;
        String currentPageAfterReloadTitle;

        //Checking menu
        List<WebElement> menuTabs = (new WebDriverWait(driver,10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("maintab")));


        for (int i=menuTabs.size()-1; i >= 0; i--){
            menuTabs.get(i).click();
            currentPageBeforeReloadTitle = driver.findElement(By.className("breadcrumb")).getText();
            System.out.println(currentPageBeforeReloadTitle);
            driver.navigate().refresh();
            currentPageAfterReloadTitle = (new WebDriverWait(driver,10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.className("breadcrumb"))).getText();
            System.out.println("In the same page after reloading " + currentPageAfterReloadTitle.equals(currentPageBeforeReloadTitle));

           menuTabs = (new WebDriverWait(driver,90))
                  .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("maintab")));
        }


        driver.quit();

    }

    private static WebDriver getDriver(){
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "//resources//chromedriver.exe");
        return new ChromeDriver();
    }
}
