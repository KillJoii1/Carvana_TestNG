package scripts;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import pages.CarvanaCarsPage;
import pages.CarvanaHomePage;
import utils.Driver;
import waiter.Waiter;

public class Base {
    WebDriver driver;
    Waiter waiter;
    SoftAssert softAssert;
    CarvanaHomePage carvanaHomePage;
    CarvanaCarsPage carvanaCarsPage;

    @BeforeMethod
    public void setup() {
        driver = Driver.getDriver();
        waiter = new Waiter();
        softAssert = new SoftAssert();
        carvanaHomePage = new CarvanaHomePage();
        carvanaCarsPage = new CarvanaCarsPage();
    }

    @AfterMethod
    public void teardown() {
        try {
            softAssert.assertAll();
        } finally {
            Driver.quitDriver();
        }
    }
}
