/*package web.test;

import laptop.database.GiornaleDao;
import laptop.database.LibroDao;
import laptop.database.RivistaDao;
import laptop.database.UsersDao;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import web.bean.ModificaOggettoBean;
import web.bean.SystemBean;
import web.bean.TextAreaBean;
import web.bean.UserBean;
import web.servlet.ReportServlet1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TestWebLogin {

    private final LibroDao lD=new LibroDao();
    private final RivistaDao rD=new RivistaDao();
    private final GiornaleDao gD=new GiornaleDao();
    private final TextAreaBean tAB=new TextAreaBean();
    private final ModificaOggettoBean mOB=new ModificaOggettoBean();

   // private String fileLibro = "ReportFinale/riepilogoLibro.txt";



/*
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
    void testLoginAdmin() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException, IOException {
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

        lD.generaReport();


        /*
        Path curr= Paths.get("riepilogoLibro.txt");
        String s=curr.toAbsolutePath().toString();
        System.out.println("path:" + s);

        //driver.findElement(By.id("buttonL")).click();7

  //  driver.quit();



    }
*/

    //nonva;
    /*
    @Test
    void testLoginAdminRaccoltaLibroInserisci() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException, IOException {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        //schermata index
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/original-LibreriaMaven/index.jsp");
        driver.findElement(By.id("buttonLogin")).click();
        //schermata login
        driver.findElement(By.id("emailL")).sendKeys("admin@admin.com");
        driver.findElement(By.id("passL")).sendKeys("Admin874");
        String email = driver.findElement(By.id("emailL")).getAttribute("value");
        String pass = driver.findElement(By.id("passL")).getAttribute("value");
        PropertyUtils.setProperty(UserBean.getInstance(), "emailB", email);
        PropertyUtils.setProperty(UserBean.getInstance(), "passwordB", pass);
        driver.findElement(By.id("loginB")).click();
        driver.findElement(By.id("raccoltaB")).click();
        //schermata raccolta libro

        driver.findElement(By.id("buttonL")).click();
        driver.findElement(By.id("buttonGenera")).click();
        driver.findElement(By.id("buttonAdd")).click();;
        //schermata aggiungi libro
        driver.findElement(By.id("titoloL")).sendKeys("titolo book via bean");
        driver.findElement(By.id("nrPagL")).sendKeys("180");
        driver.findElement(By.id("codL")).sendKeys("255598541");
        driver.findElement(By.id("autoreL")).sendKeys("autore book via bean");
        driver.findElement(By.id("editoreL")).sendKeys("editore book via bean");
        driver.findElement(By.id("linguaL")).sendKeys("lingua book via bean");
        driver.findElement(By.id("catS")).sendKeys("FAMIGLIA");
        driver.findElement(By.id("dataL")).sendKeys("2024/02/14");
        driver.findElement(By.id("recensioneL")).sendKeys("recencionse book via bean");
        driver.findElement(By.id("descL")).sendKeys("descrizione book via bean");
        driver.findElement(By.id("checkL")).click();
        driver.findElement(By.id("prezzoL")).sendKeys("4.25");
        driver.findElement(By.id("copieL")).sendKeys("100");
        driver.findElement(By.id("confermaB")).click();
        driver.close();

    }



    @Test
    void testLoginAdminRaccoltaLibroModifica() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException, IOException {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        //schermata index
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/original-LibreriaMaven/index.jsp");
        driver.findElement(By.id("buttonLogin")).click();
        //schermata login
        driver.findElement(By.id("emailL")).sendKeys("admin@admin.com");
        driver.findElement(By.id("passL")).sendKeys("Admin874");
        String email = driver.findElement(By.id("emailL")).getAttribute("value");
        String pass = driver.findElement(By.id("passL")).getAttribute("value");
        PropertyUtils.setProperty(UserBean.getInstance(), "emailB", email);
        PropertyUtils.setProperty(UserBean.getInstance(), "passwordB", pass);
        driver.findElement(By.id("loginB")).click();
        driver.findElement(By.id("raccoltaB")).click();
        //schermata raccolta libro

        driver.findElement(By.id("buttonL")).click();
        driver.findElement(By.id("buttonGenera")).click();
        driver.findElement(By.id("idL")).sendKeys("5");
        String id=driver.findElement(By.id("idL")).getAttribute("value");
        PropertyUtils.setProperty(mOB,"idB",Integer.parseInt(id));
        driver.findElement(By.id("buttonMod")).click();;
        //schermata aggiungi libro


    }





}


     */