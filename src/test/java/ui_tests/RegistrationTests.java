package ui_tests;


import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import static utils.RandomUtils.*;

public class RegistrationTests extends ApplicationManager {
    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod
    public void goToRegistrationPage() {
        homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        loginPage = new LoginPage(getDriver());

    }

    //
    @Test
    public void registrationPositiveTest() {
        User user = new User(generateEmail(10), "Password123!");
        loginPage.typeRegistrationForm(user);
        Assert.assertTrue(loginPage.isNoContactsMessagePresent("Add new by clicking on Add in NavBar!"));


    }

    @Test
    public void registrationNegativeTest() {
        User user = new User(generateEmail(10), "Password123");
        loginPage.typeRegistrationForm(user);
        Assert.assertTrue(loginPage.CloseAlertReturnText()
                .contains("Password must contain at least one special symbol from [‘$’,’~’,’-‘,’_’]!"));


    }

    @Test
    public void registrationNegativeTest_duplicateUser() {
        User user = new User(generateEmail(10), "Password123!");
        loginPage.typeRegistrationForm(user);
        if (loginPage.isNoContactsMessagePresent("Add new by clicking on Add in NavBar!")) {
            loginPage.logOut();
            loginPage.typeRegistrationForm(user);
            Assert.assertTrue(loginPage.CloseAlertReturnText()
                    .contains("User already exist"));
        } else {
            Assert.fail("wrong registration with user " + user.toString());
        }

    }
}