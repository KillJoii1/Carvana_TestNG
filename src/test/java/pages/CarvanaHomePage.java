package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

import java.util.List;

public class CarvanaHomePage {
    public CarvanaHomePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "div[data-qa='logo-wrapper']")
    public WebElement carvanaLogo;

    @FindBy(css = "div[data-qa='header-menu-wrapper']>div>a>svg>g>path")
    public List<WebElement> carvanaLogoPieces;

    @FindAll({
            @FindBy(css = "div[data-qa='menu-wrapper']:nth-child(1)>a"),
            @FindBy(css = "div[class*='eNcYVl']>a"),
            @FindBy(css = "div[data-qa='menu-wrapper']:nth-child(5)>a")
    })
    public List<WebElement> carvanaMainMenuNavItems;

    @FindBy(css = "div[data-qa='sign-in-wrapper']")
    public WebElement carvanaSignInButton;

    @FindBy(css = "div[data-cv-test='Header.Modal']")
    public WebElement signInModal;

    @FindBy(id = "usernameField")
    public WebElement signInModalUsernameInput;

    @FindBy(css = "label[for='usernameField']")
    public WebElement signInModalUsernameLabel;

    @FindBy(id = "passwordField")
    public WebElement signInModalPasswordInput;

    @FindBy(css = "label[for='passwordField']")
    public WebElement signInModalPasswordLabel;

    @FindBy(css = "button[data-cv]")
    public WebElement signInModalButton;

    @FindBy(css = "div[data-qa='error-message-container']")
    public WebElement signInModalErrorMessage;

    @FindBy(css = "a[data-cv-test='headerSearchLink']")
    public WebElement carvanaSearchCarsButton;
}
