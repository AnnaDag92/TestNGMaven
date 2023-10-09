package TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static TestNG.Constants.URL;

public class LoginTest_Example1 {
    WebDriver driver = null;
    WebDriverWait wait;

    @BeforeMethod
    public void beforeMethod() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void loginValid() throws InterruptedException {
        driver.get(URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        username.sendKeys("tomsmith");

        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.sendKeys("SuperSecretPassword!");

        WebElement login = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/button/i"));
        login.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement message = driver.findElement(By.xpath("//*[@id=\"flash\"]"));

        Assert.assertTrue(message.isDisplayed());
        String actualMessage = message.getText().trim();
        String expectedMessage = "You logged into a secure area!";
        Assert.assertTrue(actualMessage.contains(expectedMessage));
        System.out.println(actualMessage);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.quit();
    }

    @Test
    public void loginInvalid() throws InterruptedException {
        driver.get(URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tom");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SecretPassword!");

        WebElement login = driver.findElement(By.cssSelector(".fa"));
        login.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement message = driver.findElement(By.id("flash"));

        Assert.assertTrue(message.isDisplayed());
        String actualMessage = message.getText().trim();
        String expectedMessage = "Your username is invalid!";
        Assert.assertTrue(actualMessage.contains(expectedMessage));
        System.out.println(actualMessage);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.quit();
    }
}

