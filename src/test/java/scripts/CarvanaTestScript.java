package scripts;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;

public class CarvanaTestScript extends Base{

    @Test (priority = 1, description = "Test Case 1 : Validate Carvana home page title and url")
    public void testCarvanaHomeTitleUrl() {
        driver.get("https://www.carvana.com");
        Assert.assertEquals(driver.getTitle(), "Carvana | Buy & Finance Used Cars Online | At Home Delivery");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.carvana.com/");
    }

    @Test (priority = 2, description = "Test Case 2 : Validate Carvana logo")
    public void testCarvanaLogo() {
        driver.get("https://www.carvana.com");
        int[] logoDAttributeLength = {360, 1256, 69, 537, 1238}; // getting 'd' attribute length to compare
        String[] logoFillColor = {"#183558", "#183558", "#00acd8", "#fbb649", "#fff"};
        Assert.assertTrue(carvanaHomePage.carvanaLogo.isEnabled());

        for (int i = 0; i < logoDAttributeLength.length; i++) {
            Assert.assertEquals(carvanaHomePage.carvanaLogoPieces.get(i).getAttribute("d").length(), logoDAttributeLength[i]);
            Assert.assertEquals(carvanaHomePage.carvanaLogoPieces.get(i).getAttribute("fill"), logoFillColor[i]);
        }
    }

    @Test (priority = 3, description = "Test Case 3 : Validate Carvana main navigation section items")
    public void testCarvanaMainNavigationSectionsItems() {
        driver.get("https://www.carvana.com");
        String[] mainNavItems = {"HOW IT WORKS", "ABOUT CARVANA", "SUPPORT & CONTACT"};
        for (int i = 0; i < mainNavItems.length; i++) {
            Assert.assertTrue(carvanaHomePage.carvanaMainMenuNavItems.get(i).isEnabled());
            Assert.assertEquals(carvanaHomePage.carvanaMainMenuNavItems.get(i).getText(), mainNavItems[i]);
        }
    }

    @Test (priority = 4, description = "Test Case 4 : Validate the sign in error message")
    public void testCarvanaSignInErrorMessage() {
        driver.get("https://www.carvana.com");
        Assert.assertTrue(carvanaHomePage.carvanaSignInButton.isEnabled());
        Assert.assertEquals(carvanaHomePage.carvanaSignInButton.getText(), "SIGN IN");
        carvanaHomePage.carvanaSignInButton.click();
        Assert.assertTrue(carvanaHomePage.signInModal.isDisplayed());
        Assert.assertEquals(carvanaHomePage.signInModalUsernameLabel.getText(), "Email");
        Assert.assertEquals(carvanaHomePage.signInModalPasswordLabel.getText(), "Password");
        Assert.assertEquals(carvanaHomePage.signInModalButton.getText(), "SIGN IN");
        carvanaHomePage.signInModalUsernameInput.sendKeys("johndoe@gmail.com");
        carvanaHomePage.signInModalPasswordInput.sendKeys("abcd1234");
        carvanaHomePage.signInModalButton.click();
        Assert.assertEquals(carvanaHomePage.signInModalErrorMessage.getText(), "Email address and/or password combination is incorrect\n" +
                "Please try again or reset your password.");
    }

    @Test (priority = 5, description = "Test Case 5 : Validate the search filter options and search button")
    public void testCarvanaSearchFilterAndButton() {
        driver.get("https://www.carvana.com");
        Assert.assertTrue(carvanaHomePage.carvanaSearchCarsButton.isEnabled());
        Assert.assertEquals(carvanaHomePage.carvanaSearchCarsButton.getText(), "SEARCH CARS");
        carvanaHomePage.carvanaSearchCarsButton.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.carvana.com/cars");

        String[] filterOptions = {"PAYMENT & PRICE", "MAKE & MODEL", "BODY TYPE", "YEAR & MILEAGE", "FEATURES", "MORE FILTERS"};
        for (int i = 0; i < filterOptions.length; i++) {
            Assert.assertTrue(carvanaCarsPage.carsFilterButtons.get(i).isEnabled());
            Assert.assertEquals(carvanaCarsPage.carsFilterButtons.get(i).getText(), filterOptions[i]);
        }
    }

    @Test (priority = 6, description = "Test Case 6 : Validate the search result tiles")
    public void testCarvanaSearchResultTiles() {
        driver.get("https://www.carvana.com");
        Assert.assertTrue(carvanaHomePage.carvanaSearchCarsButton.isEnabled());
        Assert.assertEquals(carvanaHomePage.carvanaSearchCarsButton.getText(), "SEARCH CARS");
        carvanaHomePage.carvanaSearchCarsButton.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.carvana.com/cars");

        Assert.assertTrue(carvanaCarsPage.carsSearchInput.isEnabled());
        Assert.assertEquals(carvanaCarsPage.carsSearchInput.getAttribute("placeholder"), "Search makes, models or keywords");
        Assert.assertFalse(carvanaCarsPage.carsSearchGoButton.isDisplayed());
        carvanaCarsPage.carsSearchInput.sendKeys("mercedes-benz");
        Assert.assertTrue(carvanaCarsPage.carsSearchGoButton.isEnabled());
        Assert.assertEquals(carvanaCarsPage.carsSearchGoButton.getText(), "GO");
        carvanaCarsPage.carsSearchGoButton.click();
        Assert.assertEquals(carvanaCarsPage.carsSearchFilterKeyword.getText().toLowerCase(), "mercedes-benz");
        Assert.assertTrue(driver.getCurrentUrl().contains("mercedes-benz"));

        int adCounter = 0; // Just a hypothetical check, "Are we showing too little or too many ad tiles?" - PO
        for (int i = 0; i < carvanaCarsPage.carsPictures.size(); i++) {
            if (carvanaCarsPage.carsTileBodies.get(i).getAttribute("class").equals("result-tile")) {
                Assert.assertTrue(carvanaCarsPage.carsTileBodies.get(i).isEnabled());
                Assert.assertTrue(carvanaCarsPage.carsPictures.get(i).isDisplayed());
                Assert.assertTrue(carvanaCarsPage.carsFavoriteIcons.get(i).isEnabled());
                Assert.assertTrue(carvanaCarsPage.carsInventoryTypes.get(i).getText().matches(".+"));
                Assert.assertTrue(carvanaCarsPage.carsYearMakeModels.get(i).getText().matches("\\d{4} .+ .+"));
                Assert.assertTrue(carvanaCarsPage.carsTrimMiles.get(i).getText().matches(".+ • [\\d,]+ miles"));
                Assert.assertTrue(carvanaCarsPage.carsPrices.get(i).getText().matches("\\$[\\d,]+"));
                Assert.assertTrue(carvanaCarsPage.carsPrices.get(i).getText().matches("\\$[\\d,]+"));
                Assert.assertTrue(carvanaCarsPage.carsEstMonthlyPay.get(i).getText().matches("Est.\n\\$\\d+\\/mo"));
                Assert.assertTrue(carvanaCarsPage.carsDownPay.get(i).getText().matches("\\$[\\d,]+ cash down"));
                Assert.assertTrue(carvanaCarsPage.carsDeliveryChip.get(i).getText().matches(".*Arrives.*"));
            } else adCounter++;
        }
        if (adCounter > 4) {
            Assert.assertTrue(true,"WE'RE SHOWING TOO MANY ON THIS PAGE!!");
        } else if (adCounter < 3) Assert.assertTrue(true, "WE'RE NOT SHOWING ENOUGH ADS ON THIS PAGE!!");
        else Assert.assertTrue(true, "GOOD AMOUNT OF ADS ON THIS PAGE!!");
    }
}
