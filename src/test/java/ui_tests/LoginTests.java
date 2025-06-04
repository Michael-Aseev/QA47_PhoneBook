package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends ApplicationManager {

    @Test
    public void loginPositiveTests(){
        User user = new User("studiesaseev@gmail.com", "Ecbdn300396$");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginFrom(user);
    }
}
