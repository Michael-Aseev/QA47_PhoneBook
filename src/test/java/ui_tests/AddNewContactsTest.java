package ui_tests;

import dto.Contact;
import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddPage;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.HeaderMenuItem;

import static pages.BasePage.*;
import static utils.RandomUtils.*;

public class AddNewContactsTest extends ApplicationManager {
    HomePage homePage;
    LoginPage loginPage;
    ContactsPage contactsPage;
    AddPage addPage;
    int sizeBeforeAdd;


@BeforeMethod
public void login(){
    User user = new User("mamon300396@gmail.com", "Ercbdn300396$");
    homePage = new HomePage(getDriver());
    loginPage = clickButtonHeader(HeaderMenuItem.LOGIN);
    loginPage.typeLoginForm(user);
    contactsPage = new ContactsPage(getDriver());
    sizeBeforeAdd = contactsPage.getContactsListSize();
    addPage = clickButtonHeader(HeaderMenuItem.ADD);
}

@Test(invocationCount = 1)
public void addNewContactPositiveTest(){
    Contact contact = Contact.builder()
            .name(generateString(5))
            .lastName(generateString(10))
            .phone("0123456789")
            .email(generateEmail(10))
            .address("Haifa " + generateString(10))
            .description("desc " + generateString(15))
            .build();
    addPage.typeAddNewContactFrom(contact);
    int sizeAfterAdd = contactsPage.getContactsListSize();
    System.out.println(sizeBeforeAdd + "X" + sizeAfterAdd);
    Assert.assertEquals(sizeBeforeAdd +1, sizeAfterAdd);
}

    @Test(invocationCount = 1)
    public void addNewContactPositiveTest_useFindElements() {
        Contact contact = Contact.builder()
                .name(generateString(5))
                .lastName(generateString(10))
                .phone("0123456789")
                .email(generateEmail(10))
                .address("Haifa " + generateString(10))
                .description("desc " + generateString(15))
                .build();
        addPage.typeAddNewContactFrom(contact);
        int sizeAfterAdd = contactsPage.getContactsListSizeUseFindElement();
        System.out.println(sizeBeforeAdd + "X" + sizeAfterAdd);
        Assert.assertEquals(sizeBeforeAdd +1, sizeAfterAdd);
    }
    @Test
    public void addNewContactPositiveTest_validateContactNamePhone() {
        Contact contact = Contact.builder()
                .name("Name-"+generateString(8))
                .lastName(generateString(10))
                .phone(generatePhone(10))
                .email(generateEmail(10))
                .address("Haifa " + generateString(10))
                .description("desc " + generateString(15))
                .build();
        addPage.typeAddNewContactFrom(contact);
        Assert.assertTrue(contactsPage.validateContactNamePhone(contact.getName(),
                contact.getPhone()));
    }

}

