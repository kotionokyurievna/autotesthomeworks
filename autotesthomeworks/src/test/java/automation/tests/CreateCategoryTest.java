package automation.tests;

import automation.BaseScript;
import automation.GeneralActions;
import automation.utils.Properties;
import org.openqa.selenium.WebDriver;

/**
 * Created by kotionokyurievna on 16.11.2018.
 */

public class CreateCategoryTest extends BaseScript {
    public static void main(String[] args) throws InterruptedException {
        //preparing driver object
        WebDriver driver = getConfiguredDriver();
        driver.get(Properties.getBaseAdminUrl());
        GeneralActions generalActions = new GeneralActions(driver);

        String categoryName = "Judes";

        // login
        generalActions.login("webinar.test@gmail.com","Xcg7299bnSmMuRLp9ITw\n");
        // create category
        generalActions.createCategory(categoryName);
        // check that new category appears in Categories table
        boolean isCreated = generalActions.filterCategory(categoryName);
        if (isCreated){
            System.out.println("Category successfully created");
        }
        // finish script
        driver.quit();
    }
}

