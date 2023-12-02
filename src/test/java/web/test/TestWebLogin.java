package web.test;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import web.bean.SystemBean;
import web.bean.UserBean;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class TestWebLogin {


    @Test
        void testLoginAdminError() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        //schermata index
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/original-LibreriaMaven/index.jsp");
        driver.findElement(By.id("buttonLogin")).click();
        //schermata login
        driver.findElement(By.id("emailL")).sendKeys("admin@admin.com");
        driver.findElement(By.id("emailL")).sendKeys("Admin874");
        String email= driver.findElement(By.id("emailL")).getAttribute("value");
        String pass= driver.findElement(By.id("passL")).getAttribute("value");
        PropertyUtils.setProperty(UserBean.getInstance(),"emailB",email);
        PropertyUtils.setProperty(UserBean.getInstance(),"passwordB",pass);
        driver.findElement(By.id("loginB")).click();
        assertEquals("",pass);
        driver.close();
    }
    @Test
    void testLoginAdmin() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        //schermata index
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/original-LibreriaMaven/index.jsp");
        driver.findElement(By.id("buttonLogin")).click();
        //schermata login
        driver.findElement(By.id("emailL")).sendKeys("admin@admin.com");
        driver.findElement(By.id("passL")).sendKeys("Admin874");
        String email= driver.findElement(By.id("emailL")).getAttribute("value");
        String pass= driver.findElement(By.id("passL")).getAttribute("value");
        PropertyUtils.setProperty(UserBean.getInstance(),"emailB",email);
        PropertyUtils.setProperty(UserBean.getInstance(),"passwordB",pass);
        driver.findElement(By.id("loginB")).click();
        driver.findElement(By.id("reportB")).click();
        //scermata report
        driver.findElement(By.id("totaleRacc")).click();
        driver.findElement(By.id("scelta")).click();


    }

    /*
    /admin report




     */

}
