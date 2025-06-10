package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactsPage;
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
        ContactsPage contactsPage = new ContactsPage(getDriver());
        Assert.assertTrue(contactsPage.isContactsPresent());
        //System.out.println("after assert");
    }

    @Test
    public void loginNegativeTests_wrongPassword(){
        User user = new User("studiesaseev@gmail.com", "Ecbdn300396kkll");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginFrom(user);
        loginPage.CloseAlert();
        Assert.assertTrue(loginPage.isErrorMessagePresent("Login Failed with code 401"));



    }

    @Test
    public void loginNegativeTests_wrongEmail(){
        User user = new User("studiesaseevgmail.com", "Ecbdn300396$");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginFrom(user);
        loginPage.CloseAlert();
        Assert.assertTrue(loginPage.isErrorMessagePresent("Login Failed with code 401"));



    }

}

