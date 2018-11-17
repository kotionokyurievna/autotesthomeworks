package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {
    private WebDriver driver;
    private WebDriverWait wait;

    public GeneralActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    /**
     * Logs in to Admin Panel.
     *
     * @param login
     * @param password
     */
    public void login(String login, String password) {
        //logging in to Admin Panel
        WebElement loginField = driver.findElement(By.id("email"));
        loginField.sendKeys(login);

        WebElement passwordField = driver.findElement(By.id("passwd"));
        passwordField.sendKeys(password);

        WebElement submitLoginButton = driver.findElement(By.name("submitLogin"));
       // submitLoginButton.click();

    }

    /**
     * Adds new category in Admin Panel.
     *
     * @param categoryName
     */
    public void createCategory(String categoryName) {
        //logic for new category creation
        WebDriverWait wait = new WebDriverWait(driver,10);

        WebElement catalogMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("subtab-AdminCatalog")));

        WebElement categoriesMenu = driver.findElement(By.id("subtab-AdminCategories"));

        Actions actions = new Actions(driver);
        actions.moveToElement(catalogMenu).click(categoriesMenu).build().perform();

        WebElement newCategoryIcon = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("process-icon-new")));
        newCategoryIcon.click();

        WebElement categoryNameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name_1")));
        categoryNameField.sendKeys(categoryName);

        WebElement saveButton = driver.findElement(By.id("category_form_submit_btn"));
        saveButton.click();

        wait.until(ExpectedConditions
                .presenceOfElementLocated(By.cssSelector("div.alert")));

    }

    /**
     * Filtering for the category in Admin Panel.
     *
     * @param categoryName
     */
    public boolean filterCategory(String categoryName) {
        boolean result = true;
        WebDriverWait wait = new WebDriverWait(driver,10);

        WebElement categoryFilterField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("categoryFilter_name")));
        categoryFilterField.sendKeys(categoryName);

        WebElement submitBtn = driver.findElement(By.id("submitFilterButtoncategory"));
        submitBtn.click();

        try {
            WebElement categoryInList =
                    wait.until (ExpectedConditions.presenceOfElementLocated(By.xpath("//*[.='"+categoryName+"']")));
        }
        catch( NoSuchElementException exception ){
            result = false;
        }
        return result;
    }

    /**
     * Waits until page loader disappears from the page
     */
    public void waitForContentLoad() {

    }
}