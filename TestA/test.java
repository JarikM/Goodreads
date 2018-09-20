package tests;

import Managers.WebDriverManager;
import POM.Pages.HomePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import utils.Randomizer;

import java.text.ParseException;

public class test {

    private String name = Randomizer.randomString(10);
    private String email = name + "@email.com";

    @AfterTest
    public void close() {
        WebDriverManager.close();
    }


    @Test
    public void test() throws ParseException {

        new HomePage()
                .open()
        //register new user
                .singUp(name, email, "123456") // Here test may fall if security page to enter the captcha was opened
                .clickSkipThisStep()
                .clickSkipThisStep()
                .checkArtBox()
                .clickContinue()
                .clickFinishRating()
                .signOut()
        //login with invalid credentials
                .clickSignInAgain()
                .setEmail("noname@email.com")
                .setPassword("qwerty")
                .clickSignIn()
        //login with valid credentials
                .signIn(email, "123456")
        //search
                .searchText("Best crime and mystery books")
        //mark and put feedback for 1 of top 3 book
                .markWantToRead(1)
                .markRead(1)
                .makeFeedback(1, "What a great book", "2001", "January", "1", "2002", "January", "1")
        //mark and put feedback for 2 of top 3 book
                .markWantToRead(2)
                .markRead(2)
                .makeFeedback(2, "I didn't like it", "2005", "February", "10", "2006", "February", "10")
        //mark and put feedback for 3 of top 3 book
                .markWantToRead(3)
                .markRead(3)
                .makeFeedback(3, "It's OK", "2007", "March", "1", "2008", "September", "1")
        //log out
                .signOut();

    }
}
