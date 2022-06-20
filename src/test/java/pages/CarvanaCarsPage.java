package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

import java.util.List;

public class CarvanaCarsPage {
    public CarvanaCarsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "div[data-qa='menu-flex']>button")
    public List<WebElement> carsFilterButtons;

    @FindBy(css = "input[data-qa='search-bar-input']")
    public WebElement carsSearchInput;

    @FindBy(css = "button[data-qa='go-button']")
    public WebElement carsSearchGoButton;

    @FindBy(css = "span[data-qa='filter-display-text']")
    public WebElement carsSearchFilterKeyword;

    @FindBy(css = "#results-section>div") // I'm including the ad tiles, else css = "div[class="result-tile"]"
    public List<WebElement> carsTileBodies;

    @FindBy(css = "svg[class='favorite-icon']")
    public List<WebElement> carsFavoriteIcons;

    @FindBy(css = "picture[class='vehicle-image']")
    public List<WebElement> carsPictures;

    @FindBy(css = "div[data-test='InventoryTypeExperiment']")
    public List<WebElement> carsInventoryTypes;

    @FindBy(css = "div[data-qa='make-model']")
    public List<WebElement> carsYearMakeModels;

    @FindBy(css = "div[data-qa='trim-mileage']")
    public List<WebElement> carsTrimMiles;

    @FindBy(css = "div[class='price ']")
    public List<WebElement> carsPrices;

    @FindBy(css = "div[data-qa='monthly-payment']")
    public List<WebElement> carsEstMonthlyPay;

    @FindBy(css = "div[class='down-payment']")
    public List<WebElement> carsDownPay;

    @FindBy(css = "div[data-qa='no-shipping-cost-delivery-chip']")
    public List<WebElement> carsDeliveryChip;

}
