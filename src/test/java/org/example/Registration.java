package org.example;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class Registration{

    private By btnLogin = By.xpath("//div[@class='col-lg-auto ml-auto d-flex align-items-center justify-space-between flex-wrap']//a[@class='btn btn-outline-azure btn-square']");
    private By inputRole = By.xpath("//input[@value='participant']/..");
    private By inputAnyRole = By.xpath("//input[@value='any']/..");
    private By inputLogin = By.xpath("//input[@id='input-login']");
    private By invalidFeedback = By.xpath("//div[@class='invalid-feedback']");
    private By inputEmail = By.xpath("//div[@class='col-lg-5']//input[@id='input-email']");
    private By inputPassword = By.xpath("//div[@class='col-lg-5']//input[@id='input-password']");
    private By inputRepeatPassword = By.xpath("//div[@class='col-lg-5']//input[@id='input-repeat-password']");
    private By inputInn = By.xpath("//div[@class='col-lg-5']//input[@id='input-inn']");
    private By inputSecretWord = By.xpath("//div[@class='col-lg-5']//input[@id='input-secret-word']");
    private By btnReg = By.xpath("//button[@class='btn btn-primary w-100 mt-3']");
    private By registrationStatus = By.xpath("//h5[@class='modal-title']");
    private By success = By.xpath("//div[contains(string(),'Успешно')]");
    private By textDanger = By.xpath("//div[@class='small text-danger mt-1']");
    WebDriver driver;
    WebDriverWait wait;
    Data getDate;
    JavascriptExecutor jse;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        getDate = new Data();
        jse = (JavascriptExecutor) driver;
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void registration() throws InterruptedException {

        driver.get(getDate.getUrl);
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.visibilityOfElementLocated(btnLogin));
        driver.findElement(btnLogin).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputRole));
        driver.findElement(inputRole).click();
        driver.findElement(inputLogin).sendKeys(getDate.login);
        driver.findElement(inputEmail).sendKeys(getDate.email);
        driver.findElement(inputPassword).sendKeys(getDate.password);
        driver.findElement(inputRepeatPassword).sendKeys(getDate.password);
        driver.findElement(inputInn).sendKeys(getDate.inn);
        driver.findElement(inputSecretWord).sendKeys(getDate.secretWord);

        WebElement сheckbox1 = driver.findElement(By.xpath("//input[contains(@class, 'form-check')]"));
        jse.executeScript("arguments[0].scrollIntoView(true);", сheckbox1);
        sleep(1000);
        сheckbox1.click();

        String winHandleBefore = driver.getWindowHandle();
        driver.findElement(btnReg).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(success));

        for (String winHandle : driver.getWindowHandles()) {
            if (!winHandleBefore.contentEquals(winHandle)) {
                driver.switchTo().window(winHandle);
                break;
            }
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(registrationStatus));
        String actualRegistrationStatus = driver.findElement(registrationStatus).getText();
        Assert.assertEquals(getDate.registrationStatusSuccess, actualRegistrationStatus);
    }

    @Test
    public void registrationAnyRole() throws InterruptedException {

        driver.get(getDate.getUrl);
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.visibilityOfElementLocated(btnLogin));
        driver.findElement(btnLogin).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputRole));
        driver.findElement(inputAnyRole).click();
        driver.findElement(inputLogin).sendKeys(getDate.login1);
        driver.findElement(inputEmail).sendKeys(getDate.email1);
        driver.findElement(inputPassword).sendKeys(getDate.password);
        driver.findElement(inputRepeatPassword).sendKeys(getDate.password);
        driver.findElement(inputInn).sendKeys(getDate.inn1);
        driver.findElement(inputSecretWord).sendKeys(getDate.secretWord);

        WebElement сheckbox1 = driver.findElement(By.xpath("//input[contains(@class, 'form-check')]"));
        jse.executeScript("arguments[0].scrollIntoView(true);", сheckbox1);
        sleep(1000);
        сheckbox1.click();

        String winHandleBefore = driver.getWindowHandle();
        driver.findElement(btnReg).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(success));

        for (String winHandle : driver.getWindowHandles()) {
            if (!winHandleBefore.contentEquals(winHandle)) {
                driver.switchTo().window(winHandle);
                break;
            }
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(registrationStatus));
        String actualRegistrationStatus = driver.findElement(registrationStatus).getText();
        Assert.assertEquals(getDate.registrationStatusSuccess, actualRegistrationStatus);
    }

    @Test
    public void registrationLoginDuplication() throws InterruptedException {

        driver.get(getDate.getUrl);
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.visibilityOfElementLocated(btnLogin));
        driver.findElement(btnLogin).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputRole));
        driver.findElement(inputRole).click();
        driver.findElement(inputLogin).sendKeys(getDate.login);
        driver.findElement(inputEmail).sendKeys(getDate.email2);
        driver.findElement(inputPassword).sendKeys(getDate.password);
        driver.findElement(inputRepeatPassword).sendKeys(getDate.password);
        driver.findElement(inputInn).sendKeys(getDate.inn2);
        driver.findElement(inputSecretWord).sendKeys(getDate.secretWord);

        WebElement сheckbox1 = driver.findElement(By.xpath("//input[contains(@class, 'form-check')]"));
        jse.executeScript("arguments[0].scrollIntoView(true);", сheckbox1);
        sleep(1000);
        сheckbox1.click();
        driver.findElement(btnReg).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(invalidFeedback));
        String actualRegistrationStatus = driver.findElement(invalidFeedback).getText();
        Assert.assertEquals(getDate.loginStatusNotSuccess, actualRegistrationStatus);
    }

    @Test
    public void registrationEmptyRole() throws InterruptedException {

        driver.get(getDate.getUrl);
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.visibilityOfElementLocated(btnLogin));
        driver.findElement(btnLogin).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputRole));
        driver.findElement(inputLogin).sendKeys(getDate.login3);
        driver.findElement(inputEmail).sendKeys(getDate.email3);
        driver.findElement(inputPassword).sendKeys(getDate.password);
        driver.findElement(inputRepeatPassword).sendKeys(getDate.password);
        driver.findElement(inputInn).sendKeys(getDate.inn3);
        driver.findElement(inputSecretWord).sendKeys(getDate.secretWord);

        WebElement сheckbox1 = driver.findElement(By.xpath("//input[contains(@class, 'form-check')]"));
        jse.executeScript("arguments[0].scrollIntoView(true);", сheckbox1);
        sleep(1000);
        сheckbox1.click();
        driver.findElement(btnReg).click();

        String actualroleAlertMessage = driver.findElement(textDanger).getText();
        Assert.assertEquals(getDate.roleAlertMessage, actualroleAlertMessage);
    }
}
