package web.test;


import laptop.database.*;
import laptop.model.Negozio;
import laptop.model.TempUser;
import laptop.model.User;
import laptop.model.raccolta.Giornale;
import laptop.model.raccolta.Libro;
import laptop.model.raccolta.Rivista;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import web.bean.*;

;import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;


class TestIndexPage {
    WebDriver driver;
    private final LibroBean lB=new LibroBean();
    private final LibroDao lD=new LibroDao();

    private final Libro l=new Libro();

    private final Giornale g=new Giornale();
    private final GiornaleDao gD=new GiornaleDao();
    private final GiornaleBean gB=new GiornaleBean();


    private final Rivista r=new Rivista();
    private final RivistaBean rB=new RivistaBean();
    private final RivistaDao rD=new RivistaDao();
    private final UserBean uB=UserBean.getInstance();

    private final User u= User.getInstance();
    private final TempUser tUser=TempUser.getInstance();
    private final TextAreaBean tAB=new TextAreaBean();

         /*
            Test for Admin all functionalities
          */

  @Test
    void testLoginAdminRaccoltaLibro() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException {
        //usato per prendere id
        String isbn;
        int idLibro;
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        //schermata index
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/original-LibreriaMaven/index.jsp");
        driver.findElement(By.id("buttonLogin")).click();
        driver.findElement(By.id("emailL")).sendKeys("admin@admin.com");
        driver.findElement(By.id("passL")).sendKeys("Admin871");
        PropertyUtils.setProperty(uB,"emailB",driver.findElement(By.id("emailL")).getAttribute("value"));
        PropertyUtils.setProperty(uB,"passB",driver.findElement(By.id("passL")).getAttribute("value"));
        driver.findElement(By.id("loginB")).click();
        //schermata admin
        driver.findElement(By.id("raccoltaB")).click();
        //schermata raccolta
        driver.findElement(By.id("buttonL")).click();
        //ho generato la lista
        driver.findElement(By.id("buttonGenera")).click();
        // 1 ) inserisco libro
        driver.findElement(By.id("buttonAdd")).click();
        //insierimento nuovo libro
        driver.findElement(By.id("titoloL")).sendKeys("provo ad inserire un nuovo libro");
        driver.findElement(By.id("nrPagL")).sendKeys("150");
        WebElement id=driver.findElement(By.id("codL"));
        id.sendKeys("18552963");
        isbn=id.getAttribute("value");
        driver.findElement(By.id("autoreL")).sendKeys("autore prova");
        driver.findElement(By.id("editoreL")).sendKeys("editore prova");
        driver.findElement(By.id("linguaL")).sendKeys("italiano");
        driver.findElement(By.id("catS")).sendKeys("ARTE");
        driver.findElement(By.id("dataL")).sendKeys("2024/11/03");
        driver.findElement(By.id("recensioneL")).sendKeys(" questo e un libro inserito");
        driver.findElement(By.id("descL")).sendKeys("libro inserito per test");
        //non disponibile -> non clicco su checkbox
        driver.findElement(By.id("prezzoL")).sendKeys("5.00f");
        driver.findElement(By.id("copieL")).sendKeys("16");
        driver.findElement(By.id("confermaB")).click();
        //previous page
        driver.findElement(By.id("buttonGenera")).click();
        PropertyUtils.setProperty(lB,"codIsbnB",isbn);

        l.setCodIsbn(lB.getCodIsbnB());

        idLibro=lD.retId(l);
        PropertyUtils.setProperty(lB,"idB",idLibro);

        driver.findElement(By.id("idL")).sendKeys(PropertyUtils.getProperty(lB,"idB").toString());
        driver.findElement(By.id("buttonMod")).click();
        //schermata modifica
        driver.findElement(By.id("listaB")).click();
        //update
        driver.findElement(By.id("titoloNL")).sendKeys("un bel libro aggironato");
        driver.findElement(By.id("pagineNL")).sendKeys("180");
        driver.findElement(By.id("codiceNL")).sendKeys("16632510");
        driver.findElement(By.id("editoreNL")).sendKeys("hoepli");
        driver.findElement(By.id("autoreNL")).sendKeys("hoepli");
        driver.findElement(By.id("linguaNL")).sendKeys("italiano");
        driver.findElement(By.id("categoriaNL")).sendKeys("SCIENZE");
        driver.findElement(By.id("dataNL")).sendKeys("02/05/2024");
        driver.findElement(By.id("recNL")).sendKeys("libro aggiornato");
        driver.findElement(By.id("copieNL")).sendKeys("75");
        driver.findElement(By.id("descNL")).sendKeys("un bel libro aggironato");
        driver.findElement(By.id("dispNL")).sendKeys("0");
        driver.findElement(By.id("prezzoNL")).sendKeys("7.52f");
        driver.findElement(By.id("buttonI")).click();
        //updated book
        driver.findElement(By.id("idL")).sendKeys(PropertyUtils.getProperty(lB,"idB").toString());
        //delete
        driver.findElement(By.id("buttonCanc")).click();



        assertNotEquals(0,PropertyUtils.getProperty(lB,"idB"));

    }


    //funziona

    @Test
    void testLoginAdminRaccoltaGiorale() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException {
        String titolo;

        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        //schermata index
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/original-LibreriaMaven/index.jsp");
        driver.findElement(By.id("buttonLogin")).click();
        driver.findElement(By.id("emailL")).sendKeys("admin@admin.com");
        driver.findElement(By.id("passL")).sendKeys("Admin871");
        PropertyUtils.setProperty(uB,"emailB",driver.findElement(By.id("emailL")).getAttribute("value"));
        PropertyUtils.setProperty(uB,"passB",driver.findElement(By.id("passL")).getAttribute("value"));
        driver.findElement(By.id("loginB")).click();
        //schermata admin
        driver.findElement(By.id("raccoltaB")).click();
        //schermata raccolta
        driver.findElement(By.id("buttonG")).click();
        //ho generato la lista
        driver.findElement(By.id("buttonGenera")).click();
        // 1 ) inserisco libro
        driver.findElement(By.id("buttonAdd")).click();
        //insierimento nuovo giornale
        WebElement t=driver.findElement(By.id("titoloG"));
        t.sendKeys("provo ad inserire un nuovo giornale");
        titolo=t.getAttribute("value");
        //set id
        PropertyUtils.setProperty(gB,"titoloB",titolo);
        g.setTitolo(PropertyUtils.getProperty(gB,"titoloB").toString());
        PropertyUtils.setProperty(gB,"idB",gD.retId(g));


        driver.findElement(By.id("linguaG")).sendKeys("italiano");
        driver.findElement(By.id("editoreG")).sendKeys("editore prova");
        driver.findElement(By.id("dataG")).sendKeys("2024/01/08");
        driver.findElement(By.id("copieG")).sendKeys("52");
        driver.findElement(By.id("dispG")).sendKeys("1");
        driver.findElement(By.id("prezzoG")).sendKeys("1.65f");
        driver.findElement(By.id("confermaB")).click();
        //
        driver.findElement(By.id("idL")).sendKeys(PropertyUtils.getProperty(gB,"idB").toString());

        driver.findElement(By.id("buttonMod")).click();
        driver.findElement(By.id("listaB")).click();



        //modif
        driver.findElement(By.id("titoloNG")).sendKeys("La gazzetta del pirla");
        driver.findElement(By.id("linguaNG")).sendKeys("italiano");
        driver.findElement(By.id("editoreNG")).sendKeys("mondadori");
        driver.findElement(By.id("dataNG")).sendKeys("2024/02/18");
        driver.findElement(By.id("copieNG")).sendKeys("100");
        driver.findElement(By.id("dispNG")).sendKeys("1");
        driver.findElement(By.id("prezzoNG")).sendKeys("4.56f");
        driver.findElement(By.id("buttonI")).click();
        //delete
        driver.findElement(By.id("idL")).sendKeys(PropertyUtils.getProperty(gB,"idB").toString());
        driver.findElement(By.id("buttonCanc")).click();
        assertNotEquals(0,PropertyUtils.getProperty(gB,"idB"));


    }


     //funziona
    @Test
    void testLoginAdminRaccoltaRiviste() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException {
        String titolo;

        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        //schermata index
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/original-LibreriaMaven/index.jsp");
        driver.findElement(By.id("buttonLogin")).click();
        driver.findElement(By.id("emailL")).sendKeys("admin@admin.com");
        driver.findElement(By.id("passL")).sendKeys("Admin871");
        PropertyUtils.setProperty(uB,"emailB",driver.findElement(By.id("emailL")).getAttribute("value"));
        PropertyUtils.setProperty(uB,"passB",driver.findElement(By.id("passL")).getAttribute("value"));
        driver.findElement(By.id("loginB")).click();
        //schermata admin
        driver.findElement(By.id("raccoltaB")).click();
        //schermata raccolta
        driver.findElement(By.id("buttonR")).click();
        //ho generato la lista
        driver.findElement(By.id("buttonGenera")).click();
        // 1 ) inserisco libro
        driver.findElement(By.id("buttonAdd")).click();
        WebElement t=driver.findElement(By.id("titoloL"));
        t.sendKeys("provo ad inserire un nuovo giornale");
        titolo=t.getAttribute("value");
        PropertyUtils.setProperty(rB,"titoloB",titolo);
        r.setTitolo(PropertyUtils.getProperty(rB,"titoloB").toString());
        PropertyUtils.setProperty(rB,"idB",rD.retId(r));
        driver.findElement(By.id("catS")).sendKeys("INVERNALE");
        driver.findElement(By.id("autL")).sendKeys("mondadori");
        driver.findElement(By.id("linguaL")).sendKeys("italiano");
        driver.findElement(By.id("editoreL")).sendKeys("mondandori");
        driver.findElement(By.id("descL")).sendKeys("prova");
        driver.findElement(By.id("dataL")).sendKeys("2024/01/08");
        driver.findElement(By.id("checkL")).click();
        driver.findElement(By.id("prezzoL")).sendKeys("2.63f");
        driver.findElement(By.id("copieL")).sendKeys("100");
        driver.findElement(By.id("confermaB")).click();
        driver.findElement(By.id("idL")).sendKeys(PropertyUtils.getProperty(rB,"idB").toString());
        driver.findElement(By.id("buttonMod")).click();
        driver.findElement(By.id("listaB")).click();
        //modif
        driver.findElement(By.id("titoloNR")).sendKeys("titolo aggoirnato");
        driver.findElement(By.id("categoriaNR")).sendKeys("TELEVISIVO");
        driver.findElement(By.id("autoreNR")).sendKeys("paperino");
        driver.findElement(By.id("linguaNR")).sendKeys("italiano");
        driver.findElement(By.id("editoreNR")).sendKeys("pluto");
        driver.findElement(By.id("descNR")).sendKeys("questa rivista e stata modificata");
        driver.findElement(By.id("dataNR")).sendKeys("2024/05/10");
        driver.findElement(By.id("dispNR")).sendKeys("1");
        driver.findElement(By.id("prezzoNR")).sendKeys("3.25f");
        driver.findElement(By.id("copieNR")).sendKeys("30");
        driver.findElement(By.id("buttonI")).click();
        //delete
        driver.findElement(By.id("idL")).sendKeys(PropertyUtils.getProperty(rB,"idB").toString());
        driver.findElement(By.id("buttonCanc")).click();

        assertNotEquals(0,PropertyUtils.getProperty(rB,"idB"));


    }


    @Test
    void testAdminUtenti() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException {
        String email;
        String pass;
        int idUser;
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        //schermata index
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/original-LibreriaMaven/index.jsp");
        driver.findElement(By.id("buttonLogin")).click();
        driver.findElement(By.id("emailL")).sendKeys("admin@admin.com");
        driver.findElement(By.id("passL")).sendKeys("Admin871");
        PropertyUtils.setProperty(uB,"emailB",driver.findElement(By.id("emailL")).getAttribute("value"));
        PropertyUtils.setProperty(uB,"passB",driver.findElement(By.id("passL")).getAttribute("value"));
        driver.findElement(By.id("loginB")).click();
        driver.findElement(By.id("utentiB")).click();
        //schermata profilo
        driver.findElement(By.id("buttonProfilo")).click();
        //schermata utenti
        driver.findElement(By.id("genera lista")).click();
        //insert new user
        driver.findElement(By.id("inserisci")).click();
        driver.findElement(By.id("nomeU")).sendKeys("franco");
        driver.findElement(By.id("cognomeU")).sendKeys("rossi");
        WebElement e=driver.findElement(By.id("emailU"));
        e.sendKeys("frRossi185@libero.it");
        email=e.getAttribute("value");
        WebElement p=driver.findElement(By.id("passU"));
        p.sendKeys("fra185ros");
        pass=p.getAttribute("value");
        driver.findElement(By.id("descU")).sendKeys("user added");
        driver.findElement(By.id("dataU")).sendKeys("1970/02/03");

        PropertyUtils.setProperty(uB,"emailB",email);
        PropertyUtils.setProperty(uB,"passB",pass);
        System.out.println("email + pass :"+ PropertyUtils.getProperty(uB,"emailB")+ PropertyUtils.getProperty(uB,"passB"));

        tUser.setEmailT(PropertyUtils.getProperty(uB,"emailB").toString());

        tUser.setPasswordT(PropertyUtils.getProperty(uB,"passB").toString());



        driver.findElement(By.id("buttonI")).click();

        PropertyUtils.setProperty(uB,"idB",UsersDao.checkTempUser(tUser));


        //generate list
        driver.findElement(By.id("genera lista")).click();


        driver.findElement(By.id("idOgg")).sendKeys(PropertyUtils.getProperty(uB,"idB").toString());
        driver.findElement(By.id("modifica")).click();
        //modif user
        driver.findElement(By.id("generaB")).click();

        //change parameters
        driver.findElement(By.id("ruoloNL")).sendKeys("W");
        driver.findElement(By.id("nomeNL")).sendKeys("Franco");
        driver.findElement(By.id("cognomeNL")).sendKeys("Rossi");
        driver.findElement(By.id("emailNL")).sendKeys("FRossi195@libero.it");
        driver.findElement(By.id("passNL")).sendKeys("Fra185Ros");
        driver.findElement(By.id("descNL")).sendKeys("scrittore semplice");
        driver.findElement(By.id("buttonI")).click();
        //delete user

        driver.findElement(By.id("idOgg")).sendKeys(PropertyUtils.getProperty(uB,"idB").toString());
        driver.findElement(By.id("elimina")).click();
        assertNotEquals(0,PropertyUtils.getProperty(uB,"idB"));



    }
    @Test
    void testLoginAdminReport() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, IOException {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        //schermata index
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/original-LibreriaMaven/index.jsp");
        driver.findElement(By.id("buttonLogin")).click();
        driver.findElement(By.id("emailL")).sendKeys("admin@admin.com");
        driver.findElement(By.id("passL")).sendKeys("Admin871");
        PropertyUtils.setProperty(uB,"emailB",driver.findElement(By.id("emailL")).getAttribute("value"));
        PropertyUtils.setProperty(uB,"passB",driver.findElement(By.id("passL")).getAttribute("value"));
        driver.findElement(By.id("loginB")).click();
        //schermata admin
        driver.findElement(By.id("reportB")).click();
        //schermata report
        driver.findElement(By.id("buttonT")).click();
        assertNotEquals("",PropertyUtils.getProperty(tAB,"scriviB"));

    }










    @AfterEach
    void chiudiTest()
    {
        driver.close();

    }










}
