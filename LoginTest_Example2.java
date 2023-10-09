package TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import static TestNG.Constants.URL;

public class LoginTest_Example2 {
    WebDriver driver = null;
    WebDriverWait wait;

    @BeforeMethod
    public void beforeMethod() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @DataProvider(name = "data")
    public Object[][] data(){
        return new Object[][]{ {"tomsmith", "SuperSecretPassword!", "valid"}, {"tom", "SecretPassword!", "invalid"}};
    }

    @Test(dataProvider  = "data")
    public void login(String user, String pass, String valid) throws InterruptedException {
        driver.get(URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys(user);

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(pass);

        WebElement login = driver.findElement(By.cssSelector(".fa"));
        login.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement message = driver.findElement(By.id("flash"));

        Assert.assertTrue(message.isDisplayed());
        String actualMessage = message.getText().trim();
        String validMessage = "You logged into a secure area!";
        String invalidMessage = "Your username is invalid!";

        if (valid.equals("invalid")) {
            Assert.assertTrue(actualMessage.contains(invalidMessage));
            System.out.println(actualMessage);
        } else if(valid.equals("valid")) {
            Assert.assertTrue(actualMessage.contains(validMessage));
            System.out.println(actualMessage);
        }
        else{
            Assert.fail();
        }

    }

    @AfterMethod
    public void quit(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.quit();
    }
}
