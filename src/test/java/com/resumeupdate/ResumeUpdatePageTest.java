package com.resumeupdate;
import com.testngclass.RetryAnalyzer;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ResumeUpdatePageTest {

    @Test(retryAnalyzer= RetryAnalyzer.class)
    public void resume(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("headless");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.naukri.com/");
        By element = By.id("login_Layer");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
        WebElement loginLayer = wait.until(ExpectedConditions.elementToBeClickable(element));
        loginLayer.click();
        driver.findElement(By.xpath("//label[text()='Email ID / Username']/following-sibling::input"))
                .sendKeys("gokul.testengineer@gmail.com");
        driver.findElement(By.xpath("//label[text()='Password']/following-sibling::input")).
                sendKeys("gokultester");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(By.xpath("//a[text()='Complete profile']")).click();
        driver.findElement(By.xpath("(//span[text()='editOneTheme'])[2]")).click();
        List<WebElement> keySkillSugg = driver.findElements(By.xpath("//div[@class='chipsContainer']/child::div/span"));

        for (int i = 0; i < keySkillSugg.size(); i++) {
            if (keySkillSugg.get(i).getText().equalsIgnoreCase("java")) {
                System.out.println("removed skill is :"+keySkillSugg.get(i).getText()+" index is :"+i);
                driver.findElement(By.xpath("(//a[text()='Cross'])["+(i+1)+"]")).click();
                break;
            }
        }

        driver.findElement(By.id("keySkillSugg")).sendKeys("selenium");
        driver.findElement(By.id("keySkillSugg")).sendKeys("Java");
        driver.findElement(By.xpath("//button[text()='Save']")).click();
        List<WebElement> elements = driver.findElements(By.xpath("(//span[text()='CrossLayer'])[7]"));
        if(!elements.isEmpty()){
            elements.get(0).click();
        }

    }
}
