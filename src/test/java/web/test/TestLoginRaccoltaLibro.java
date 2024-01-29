package web.test;


import laptop.database.*;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import web.bean.*;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


import static org.junit.jupiter.api.Assertions.*;


class TestLoginRaccoltaLibro {
    WebDriver driver;
    private final LibroBean lB=new LibroBean();

    private final UserBean uB=UserBean.getInstance();
    private final LibroDao lD=new LibroDao();


    TestLoginRaccoltaLibro() throws IOException {
    }


    // Test for Admin all functionalities



  @Test
    void testLoginAdminRaccoltaLibro() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException{
        //usato per prendere id

        int id;
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
        driver.findElement(By.id("codL")).sendKeys("18552963");
        driver.findElement(By.id("autoreL")).sendKeys("autore prova");
        driver.findElement(By.id("editoreL")).sendKeys("editore prova");
        driver.findElement(By.id("linguaL")).sendKeys("italiano");
        driver.findElement(By.id("catS")).sendKeys("ARTE");
        driver.findElement(By.id("dataL")).sendKeys("2024/11/03");
        driver.findElement(By.id("recensioneL")).sendKeys(" questo e un libro inserito");
        driver.findElement(By.id("descL")).sendKeys("libro inserito per test");
        //non disponibile -> non clicco su checkbox
        driver.findElement(By.id("prezzoL")).sendKeys("5f");
        driver.findElement(By.id("copieL")).sendKeys("16");
        driver.findElement(By.id("confermaB")).click();


        //previous page

        driver.findElement(By.id("buttonGenera")).click();
        //get last id

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
        driver.findElement(By.id("buttonGenera")).click();

        PropertyUtils.setProperty(lB,"idB",lD.getIdMax());
        driver.findElement(By.id("idL")).sendKeys(String.valueOf(PropertyUtils.getProperty(lB,"idB")));
        //delete
        driver.findElement(By.id("buttonCanc")).click();
        driver.findElement(By.id("buttonGenera")).click();


        assertNotEquals(0,PropertyUtils.getProperty(lB,"idB"));

    }


/*
    //funziona



/*
     //funziona





    //    test for user



    @Test
    void testLoginUserGiornale() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, ParseException, SQLException {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        //schermata index
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/original-LibreriaMaven/index.jsp");
        driver.findElement(By.id("buttonLogin")).click();
        driver.findElement(By.id("emailL")).sendKeys("giuliaConforto@gmail.eu");
        driver.findElement(By.id("passL")).sendKeys("12345678Gc");
        PropertyUtils.setProperty(uB,"emailB",driver.findElement(By.id("emailL")).getAttribute("value"));
        PropertyUtils.setProperty(uB,"passB",driver.findElement(By.id("passL")).getAttribute("value"));
        driver.findElement(By.id("loginB")).click();
        driver.findElement(By.id("buttonG")).click();
        //schermata giornali
        PropertyUtils.setProperty(gB,"listaGiornaliB", gD.getGiornali());
        PropertyUtils.setProperty(sB,"typeB", sB.getTypeB());
        driver.findElement(By.id("idOgg")).sendKeys("1");
        int id=Integer.parseInt(driver.findElement(By.id("idOgg")).getAttribute("value"));
        PropertyUtils.setProperty(sB,"categoriaB","QUOTIDIANO");
        PropertyUtils.setProperty(sB,"idB", id);
        PropertyUtils.setProperty(gB,"idB", id);
        g.setId(id);
        PropertyUtils.setProperty(sB, "titoloB",gD.getData(g).getTitolo());
        PropertyUtils.setProperty(aB, "titoloB",sB.getTitoloB());
        PropertyUtils.setProperty(aB,"prezzoB",gD.getData(g).getPrezzo());
        driver.findElement(By.id("procedi")).click();
        //schermata acquista
        driver.findElement(By.id("quantita")).clear();
        driver.findElement(By.id("quantita")).sendKeys("3");
        int quantita=Integer.parseInt(driver.findElement(By.id("quantita")).getAttribute("value"));
        PropertyUtils.setProperty(sB,"quantitaB",quantita);
        driver.findElement(By.id("totaleB")).click();
        float prezzo=Float.parseFloat(driver.findElement(By.id("totale")).getAttribute("value"));
        PropertyUtils.setProperty(sB,"spesaTB",prezzo);
        PropertyUtils.setProperty(aB,"prezzoB",PropertyUtils.getProperty(sB,"spesaTB"));
        //metodo cCredito
        WebElement input =driver.findElement(By.xpath("//input[@list='metodi']"));
        WebElement option =driver.findElement(By.xpath("//*[@id='metodi']/option[2]"));
        String value = option.getAttribute("value");
        input.sendKeys(value);
        PropertyUtils.setProperty(sB,"metodoPB",value);
        driver.findElement(By.id("negozioB")).click();
        //schermata cartacredito

        driver.findElement(By.id("nomeL")).sendKeys("luigiB");
        driver.findElement(By.id("cognomeL")).sendKeys("neriB");
        driver.findElement(By.id("cartaL")).sendKeys("1995-8412-6632-2500");
        driver.findElement(By.id("scadL")).sendKeys("2028/08/01");
        driver.findElement(By.id("passL")).sendKeys("185");

        String nome=driver.findElement(By.id("nomeL")).getAttribute("value");
        String cognome=driver.findElement(By.id("cognomeL")).getAttribute("value");
        String numeroCarta=driver.findElement(By.id("cartaL")).getAttribute("value");
        String scadenza=driver.findElement(By.id("scadL")).getAttribute("value");
        String civ=driver.findElement(By.id("passL")).getAttribute("value");

        PropertyUtils.setProperty(cCB,"nomeB",nome);
        PropertyUtils.setProperty(cCB,"cognomeB",cognome);
        PropertyUtils.setProperty(cCB,"numeroCCB",numeroCarta);


        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date utilDate = format.parse(scadenza);

        PropertyUtils.setProperty(cCB,"dataScadB",utilDate);
        PropertyUtils.setProperty(cCB,"prezzoTransazioneB",PropertyUtils.getProperty(sB,"spesaTB"));
        PropertyUtils.setProperty(cCB,"civB",civ);

        PropertyUtils.setProperty(pB,"idB",0);
        PropertyUtils.setProperty(pB,"metodoB",value);
        PropertyUtils.setProperty(pB,"nomeUtenteB",PropertyUtils.getProperty(cCB,"nomeB"));
        PropertyUtils.setProperty(pB,"ammontareB",PropertyUtils.getProperty(sB,"spesaTB"));
        PropertyUtils.setProperty(pB,"tipoB",PropertyUtils.getProperty(sB,"categoriaB"));
        PropertyUtils.setProperty(pB,"idOggettoB",PropertyUtils.getProperty(sB,"idB"));

        driver.findElement(By.id("buttonI")).click();
        //schermata negozio

        //Negozio A
        PropertyUtils.setProperty(nB,"nomeB",nD.getNegozi().get(0).getNome());
        PropertyUtils.setProperty(nB,"openB",nD.getNegozi().get(0).getIsOpen());
        PropertyUtils.setProperty(nB,"validB",nD.getNegozi().get(0).getIsValid());
        PropertyUtils.setProperty(nB,"mexB","negozio non disponibile e/o chiuso");
        driver.findElement(By.id("buttonNeg1")).click();
        //Negozio C
        PropertyUtils.setProperty(nB,"nomeB",nD.getNegozi().get(2).getNome());
        PropertyUtils.setProperty(nB,"openB",nD.getNegozi().get(2).getIsOpen());
        PropertyUtils.setProperty(nB,"validB",nD.getNegozi().get(2).getIsValid());
        PropertyUtils.setProperty(nB,"mexB","negozio non disponibile e/o chiuso");
        driver.findElement(By.id("buttonNeg3")).click();
        //Negozio D
        PropertyUtils.setProperty(nB,"nomeB",nD.getNegozi().get(3).getNome());
        PropertyUtils.setProperty(nB,"openB",nD.getNegozi().get(3).getIsOpen());
        PropertyUtils.setProperty(nB,"validB",nD.getNegozi().get(3).getIsValid());
        PropertyUtils.setProperty(nB,"mexB","negozio non disponibile e/o chiuso");
        driver.findElement(By.id("buttonNeg4")).click();
        //Negozio B
        PropertyUtils.setProperty(nB,"nomeB",nD.getNegozi().get(1).getNome());
        PropertyUtils.setProperty(nB,"openB",nD.getNegozi().get(1).getIsOpen());
        PropertyUtils.setProperty(nB,"validB",nD.getNegozi().get(1).getIsValid());
        driver.findElement(By.id("buttonNeg2")).click();



        assertEquals(1,PropertyUtils.getProperty(sB,"idB"));
    }


    @Test
    void testLoginUtenteAnnullaRivista() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        //schermata index
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/original-LibreriaMaven/index.jsp");
        driver.findElement(By.id("buttonLogin")).click();
        driver.findElement(By.id("emailL")).sendKeys("giuliaConforto@gmail.eu");
        driver.findElement(By.id("passL")).sendKeys("12345678Gc");
        PropertyUtils.setProperty(uB,"emailB",driver.findElement(By.id("emailL")).getAttribute("value"));
        PropertyUtils.setProperty(uB,"passB",driver.findElement(By.id("passL")).getAttribute("value"));
        driver.findElement(By.id("loginB")).click();
        driver.findElement(By.id("buttonR")).click();
        //schermata riviste
        //setto il tipo cosi a rivista

        PropertyUtils.setProperty(rB,"listaRivisteB", rD.getRiviste());
        PropertyUtils.setProperty(sB,"typeB", sB.getTypeB());
        driver.findElement(By.id("idOgg")).sendKeys("2");
        int id=Integer.parseInt(driver.findElement(By.id("idOgg")).getAttribute("value"));
        //aggiunto per passare titiolo
        PropertyUtils.setProperty(rB,"idB",id);
        r.setId((Integer) PropertyUtils.getProperty(rB,"idB"));
        String titolo=rD.getData(r).getTitolo();
        PropertyUtils.setProperty(sB,"idB", id);
        PropertyUtils.setProperty(rB,"idB", id);
        r.setId(id);
        PropertyUtils.setProperty(sB, "titoloB",rD.getData(r).getTitolo());
        PropertyUtils.setProperty(aB, "titoloB",sB.getTitoloB());
        PropertyUtils.setProperty(aB,"prezzoB",rD.getData(r).getPrezzo());
        driver.findElement(By.id("procedi")).click();
        //schermata acquista
        driver.findElement(By.id("quantita")).clear();
        driver.findElement(By.id("quantita")).sendKeys("3");
        int quantita=Integer.parseInt(driver.findElement(By.id("quantita")).getAttribute("value"));
        PropertyUtils.setProperty(sB,"quantitaB",quantita);
        driver.findElement(By.id("totaleB")).click();
        float prezzo=Float.parseFloat(driver.findElement(By.id("totale")).getAttribute("value"));
        PropertyUtils.setProperty(sB,"spesaTB",prezzo);
        PropertyUtils.setProperty(aB,"prezzoB",PropertyUtils.getProperty(sB,"spesaTB"));

        //metodo cash
        WebElement input =driver.findElement(By.xpath("//input[@list='metodi']"));
        WebElement option =driver.findElement(By.xpath("//*[@id='metodi']/option[1]"));
        String value = option.getAttribute("value");
        input.sendKeys(value);
        PropertyUtils.setProperty(sB,"metodoPB",value);
        driver.findElement(By.id("pdfB")).click();
        //schermata fattura
        driver.findElement(By.id("nomeT")).sendKeys("marcoB");
        driver.findElement(By.id("cognomeT")).sendKeys("arancioniB");
        driver.findElement(By.id("indirizzoT")).sendKeys("via ciclamini 12");
        driver.findElement(By.id("com")).sendKeys("il cap è 025235 . Chiamare prima al numero 118563");

        String nome=driver.findElement(By.name("nomeT")).getAttribute("value");
        String cognome=driver.findElement(By.name("cognomeT")).getAttribute("value");
        String indirizzo=driver.findElement(By.name("indirizzoT")).getAttribute("value");
        String com=driver.findElement(By.name("com")).getAttribute("value");
        //setto fattura
        PropertyUtils.setProperty(fB,"nomeB",nome);
        PropertyUtils.setProperty(fB,"cognomeB",cognome);
        PropertyUtils.setProperty(fB,"indirizzoB",indirizzo);
        PropertyUtils.setProperty(fB,"comunicazioniB",com);
        driver.findElement(By.id("buttonC")).click();
        //schermata download

        driver.findElement(By.id("titoloL")).sendKeys(titolo);
        driver.findElement(By.id("annullaB")).click();



        assertNotEquals(0,PropertyUtils.getProperty(sB,"idB"));



    }

        // test for Writer




*/








}

