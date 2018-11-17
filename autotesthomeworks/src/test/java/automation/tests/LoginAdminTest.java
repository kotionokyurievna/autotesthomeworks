package automation.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by kotionokyurievna on 09.11.2018.
 */
public class LoginAdminTest {

    public static void main(String[] args) {
        WebDriver driver = getDriver();
        driver.manage().window().maximize();
        driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("webinar.test@gmail.com");

        WebElement pesswordField = driver.findElement(By.id("passwd"));
        pesswordField.sendKeys("Xcg7299bnSmMuRLp9ITw");

        WebElement submitLoginButton = driver.findElement(By.name("submitLogin"));
        submitLoginButton.click();

        WebElement employeeLogo = driver.findElement(By.cssSelector("#employee_infos > a"));
        employeeLogo.click();

        WebElement logoutOption = driver.findElement(By.id("header_logout"));
        logoutOption.click();

        driver.quit();

    }
    private static WebDriver getDriver(){
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "//resources//chromedriver.exe");
        return new ChromeDriver();
    }
}
