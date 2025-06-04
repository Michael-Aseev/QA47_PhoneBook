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

    public boolean isErrorMessagePresent(String message){
        return isTextElementPresent(ErrorMessageLogin, message);
    }


}
