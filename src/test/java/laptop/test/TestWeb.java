package laptop.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class TestWeb {
     @Test
    void test1()
     {
         System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");


         WebDriver driver = new ChromeDriver();

         driver.get("https://google.com");

         driver.findElement(By.id("L2AGLb")).click();
     }

}
