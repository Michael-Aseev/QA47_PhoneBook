package pages;

import dto.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(name = "email")
    WebElement inputEmail;

    @FindBy(name = "password")
    WebElement inputPassword;

    @FindBy(xpath = "//button[text()= 'Login']")
    WebElement btnLoginFrom;

    @FindBy(xpath = "//div[@class='login_login__3EHKB']/div")
    WebElement ErrorMessageLogin;

    @FindBy(xpath = "//button[text()= 'Registration']")
    WebElement btnRegistrationFrom;

    @FindBy(className = "contact-page_message__2qafk")
    WebElement messageNoContacts;

    @FindBy(xpath = "//button[text()='Sign Out']")
    WebElement btnSignOutHeader;

    public void logOut(){
        btnSignOutHeader.click();
    }

    //    public void typeLoginFrom(String email, String password){
//        inputEmail.sendKeys(email);
//        inputPassword.sendKeys(password);
//       btnLogin.click();
//    }
    public void typeLoginFrom(User user) {
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
        btnLoginFrom.click();

    }

    public void CloseAlert() {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.alertIsPresent());
        System.out.println(alert.getText());
        alert.accept();
    }

    public String CloseAlertReturnText() {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();
        return text;
    }

    public boolean isErrorMessagePresent(String message){
        return isTextElementPresent(ErrorMessageLogin, message);
    }

    public boolean isNoContactsMessagePresent(String message){
        return isTextElementPresent(messageNoContacts, message);
    }


    public void typeRegistrationForm(User user) {
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
        btnRegistrationFrom.click();
    }
}
