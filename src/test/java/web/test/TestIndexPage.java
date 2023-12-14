package web.test;


import laptop.database.GiornaleDao;
import laptop.database.LibroDao;
import laptop.database.NegozioDao;
import laptop.database.RivistaDao;
import laptop.model.Negozio;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class TestIndexPage {
    WebDriver driver;
    private final LibroBean lB=new LibroBean();
    private final LibroDao lD=new LibroDao();
    private final SystemBean sB=SystemBean.getInstance();
    private final AcquistaBean aB=new AcquistaBean();
    private final Libro l=new Libro();
    private final FatturaBean fB=new FatturaBean();

    private final Giornale g=new Giornale();
    private final GiornaleDao gD=new GiornaleDao();
    private final GiornaleBean gB=new GiornaleBean();

    private final CartaCreditoBean cCB=new CartaCreditoBean();
    private final PagamentoBean pB=new PagamentoBean();

    private final NegozioBean nB=new NegozioBean();
    private final NegozioDao nD=new NegozioDao();
    private final RicercaBean ricB=new RicercaBean();

    private final Rivista r=new Rivista();
    private final RivistaBean rB=new RivistaBean();
    private final RivistaDao rD=new RivistaDao();
    private final UserBean uB=UserBean.getInstance();

            /*
        TODO
            fare test
            poi fare commit
         */


     @Test
     void testAcquistaLibro() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException {
         System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
         //schermata index
         driver = new ChromeDriver();
         driver.get("http://localhost:8080/original-LibreriaMaven/index.jsp");
         driver.findElement(By.id("buttonL")).click();
         //schermata libri
         //setto il tipo cosi a libro
         sB.setTypeAsBook();
         PropertyUtils.setProperty(lB,"elencoLibriB", lD.getLibri());

         PropertyUtils.setProperty(sB,"typeB", sB.getTypeB());
         driver.findElement(By.id("idOgg")).sendKeys("1");
         int id=Integer.parseInt(driver.findElement(By.id("idOgg")).getAttribute("value"));
         PropertyUtils.setProperty(sB,"idB", id);
         PropertyUtils.setProperty(lB,"idB", id);
         l.setId(id);
         PropertyUtils.setProperty(sB, "titoloB",lD.getTitolo(l));
         PropertyUtils.setProperty(aB, "titoloB",sB.getTitoloB());
         PropertyUtils.setProperty(aB,"prezzoB",lD.getCosto(l));
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
         driver.findElement(By.id("nomeT")).sendKeys("francoB");
         driver.findElement(By.id("cognomeT")).sendKeys("rossiB");
         driver.findElement(By.id("indirizzoT")).sendKeys("via papaveri 12");
         driver.findElement(By.id("com")).sendKeys("il cap è 00005 . Chiamare prima al numero 9411526");

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
         String titolo=PropertyUtils.getProperty(sB,"titoloB").toString();
         driver.findElement(By.id("titoloL")).sendKeys(titolo);
         driver.findElement(By.id("downloadB")).click();

         assertEquals(1,PropertyUtils.getProperty(sB,"idB"));

     }



     @Test
    void testAcquistaGiornale() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException, ParseException {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        //schermata index
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/original-LibreriaMaven/index.jsp");
        driver.findElement(By.id("buttonG")).click();
        //schermata giornali
        //setto il tipo cosi a giornale
        sB.setTypeAsDaily();
        PropertyUtils.setProperty(gB,"listaGiornaliB", gD.getGiornali());
        PropertyUtils.setProperty(sB,"typeB", sB.getTypeB());
        driver.findElement(By.id("idOgg")).sendKeys("1");
        int id=Integer.parseInt(driver.findElement(By.id("idOgg")).getAttribute("value"));
        PropertyUtils.setProperty(sB,"categoriaB","QUOTIDIANO");
        PropertyUtils.setProperty(sB,"idB", id);
        PropertyUtils.setProperty(gB,"idB", id);
        g.setId(id);
        PropertyUtils.setProperty(sB, "titoloB",gD.getTitolo(g));
        PropertyUtils.setProperty(aB, "titoloB",sB.getTitoloB());
        PropertyUtils.setProperty(aB,"prezzoB",gD.getCosto(g));
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
    void testAcquistaRivistaAnnullato() throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        //schermata index
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/original-LibreriaMaven/index.jsp");
        driver.findElement(By.id("buttonR")).click();
        //schermata riviste
        //setto il tipo cosi a rivista
        sB.setTypeAsMagazine();
        PropertyUtils.setProperty(rB,"listaRivisteB", rD.getRiviste());
        PropertyUtils.setProperty(sB,"typeB", sB.getTypeB());
        driver.findElement(By.id("idOgg")).sendKeys("2");
        int id=Integer.parseInt(driver.findElement(By.id("idOgg")).getAttribute("value"));
        PropertyUtils.setProperty(sB,"idB", id);
        PropertyUtils.setProperty(rB,"idB", id);
        r.setId(id);
        PropertyUtils.setProperty(sB, "titoloB",rD.getTitolo(r));
        PropertyUtils.setProperty(aB, "titoloB",sB.getTitoloB());
        PropertyUtils.setProperty(aB,"prezzoB",rD.getCosto(r));
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
        String titolo=PropertyUtils.getProperty(sB,"titoloB").toString();
        driver.findElement(By.id("titoloL")).sendKeys(titolo);
        driver.findElement(By.id("annullaB")).click();

        assertEquals(2,PropertyUtils.getProperty(sB,"idB"));

    }


    @Test
    void testLogin() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, IOException {
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

        //lD.generaReport();
        driver.findElement(By.id("raccoltaB")).click();
        assertNotNull(PropertyUtils.getProperty(uB,"emailB"));

    }


    @Test
    void testRaccoltaAdminLibro() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {


        /*
           1) genero lista
           2) aggiungo
           3) modifico
           4) cancello

         */

        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        //schermata index
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/original-LibreriaMaven/index.jsp");
        driver.findElement(By.id("buttonLogin")).click();
        driver.findElement(By.id("emailL")).sendKeys("admin@admin.com");
        driver.findElement(By.id("passL")).sendKeys("Admin871");
        PropertyUtils.setProperty(uB, "emailB", driver.findElement(By.id("emailL")).getAttribute("value"));
        PropertyUtils.setProperty(uB, "passB", driver.findElement(By.id("passL")).getAttribute("value"));
        driver.findElement(By.id("loginB")).click();
        driver.findElement(By.id("raccoltaB")).click();
        //schermata raccolta
        // (1)
        driver.findElement(By.id("buttonL")).click();
        //schermata getione oggetto -> libri
        driver.findElement(By.id("buttonGenera")).click();
        driver.findElement(By.id("buttonAdd")).click();
        //schermata aggiungi oggetto
        // (2)
        driver.findElement(By.id("titoloL")).sendKeys("provo ad inserire un libro dalla web");
        driver.findElement(By.id("nrPagL")).sendKeys("150");
        driver.findElement(By.id("codL")).sendKeys("152996325");
        driver.findElement(By.id("autoreL")).sendKeys("autore web");
        driver.findElement(By.id("editoreL")).sendKeys("editore dalla web");
        driver.findElement(By.id("linguaL")).sendKeys("italiano");
        driver.findElement(By.id("catS")).sendKeys("FAMIGLIA");
        driver.findElement(By.id("dataL")).sendKeys("2024/11/11");
        driver.findElement(By.id("recensioneL")).sendKeys("provoad inserire un nuovo libro dalla web");
        driver.findElement(By.id("descL")).sendKeys("descrizione di prova");
        driver.findElement(By.id("checkL")).click();
        driver.findElement(By.id("prezzoL")).sendKeys("4.50");
        driver.findElement(By.id("copieL")).sendKeys("300");
        driver.findElement(By.id("confermaB")).click();
        // scheramta libro
        //carico schermata modifica
        driver.findElement(By.id("idL")).sendKeys("7");
        PropertyUtils.setProperty(sB,"idB",Integer.parseInt(driver.findElement(By.id("idL")).getAttribute("value")));
        //schermata modifica
        // (3)
        //setto id a 5
        driver.findElement(By.id("buttonMod")).click();
        driver.findElement(By.id("listaB")).click();
        //modifico
        driver.findElement(By.id("titoloNL")).sendKeys("modifico il libro");
        driver.findElement(By.id("pagineNL")).sendKeys("185");
        driver.findElement(By.id("codiceNL")).sendKeys("152901325");
        driver.findElement(By.id("autoreNL")).sendKeys("autore cambiato");
        driver.findElement(By.id("editoreNL")).sendKeys("editore cambiato");
        driver.findElement(By.id("linguaNL")).sendKeys("italianoo");
        driver.findElement(By.id("categoriaNL")).sendKeys("ARTE");
        driver.findElement(By.id("dataNL")).sendKeys("11/11/2025");
        driver.findElement(By.id("recNL")).sendKeys("modifico libro dalla web");
        driver.findElement(By.id("descNL")).sendKeys("modifica di prova");
        driver.findElement(By.id("dispNL")).click();
        driver.findElement(By.id("prezzoNL")).sendKeys("4.80");
        driver.findElement(By.id("copieNL")).sendKeys("150");
        driver.findElement(By.id("buttonI")).click();
        // schemtata libro
        // (4)
        //elimino

        driver.findElement(By.id("idL")).sendKeys("2");
        PropertyUtils.setProperty(sB,"idB",Integer.parseInt(driver.findElement(By.id("idL")).getAttribute("value")));
        driver.findElement(By.id("buttonCanc")).click();




    }

    @Test
    void testRaccoltaAdminGiornale() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {


        /*
           1) genero lista
           2) aggiungo
           3) modifico
           4) cancello
           */

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
        driver.findElement(By.id("raccoltaB")).click();
        //schermata raccolta
        // (1)
        driver.findElement(By.id("buttonG")).click();
        //schermata getione oggetto -> libri
        driver.findElement(By.id("buttonGenera")).click();
        driver.findElement(By.id("buttonAdd")).click();
        //schermata aggiungi oggetto
        // (2)
        driver.findElement(By.id("titoloG")).sendKeys("provo ad inserire un giornale dalla web");
        driver.findElement(By.id("editoreG")).sendKeys("editore dalla web");
        driver.findElement(By.id("linguaG")).sendKeys("italiano");
        driver.findElement(By.id("dataG")).sendKeys("2024/11/11");
        driver.findElement(By.id("prezzoG")).sendKeys("4.50");
        driver.findElement(By.id("copieG")).sendKeys("300");
        driver.findElement(By.id("dispG")).sendKeys("1");
        driver.findElement(By.id("confermaB")).click();
        // scheramta libro
        //carico schermata modifica
        driver.findElement(By.id("idL")).sendKeys("5");
        PropertyUtils.setProperty(sB,"idB",Integer.parseInt(driver.findElement(By.id("idL")).getAttribute("value")));
        //schermata modifica
        // (3)
        //setto id a 5
        driver.findElement(By.id("buttonMod")).click();
        driver.findElement(By.id("buttonG")).click();
        //modifico
        driver.findElement(By.id("titoloNG")).sendKeys("modifico il giornale");
        driver.findElement(By.id("editoreNG")).sendKeys("editore cambiato");
        driver.findElement(By.id("linguaNG")).sendKeys("italianoo");
        driver.findElement(By.id("dataNG")).sendKeys("11/11/2025");
        driver.findElement(By.id("dispNG")).sendKeys("1");
        driver.findElement(By.id("prezzoNG")).sendKeys("5.00");
        driver.findElement(By.id("copieNG")).sendKeys("200");
        driver.findElement(By.id("confermaB")).click();
        // schemtata libro
        // (4)
        //elimino
        driver.findElement(By.id("idL")).sendKeys("5");
        PropertyUtils.setProperty(sB,"idB",Integer.parseInt(driver.findElement(By.id("idL")).getAttribute("value")));
        driver.findElement(By.id("buttonCanc")).click();


    }



    @Test
    void testRicercaPerLibro() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
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
        //schermata per gestione utenti(in comuni con utente loggato)
        driver.findElement(By.id("utentiB")).click();
        driver.findElement(By.id("buttonRic")).click();
        driver.findElement(By.id("buttonL")).click();
        //schermata ricerca -> vadoper libri
        WebElement autore=driver.findElement(By.id("cercaL"));
        autore.sendKeys("zerocalcare");
        PropertyUtils.setProperty(ricB,"autoreB",autore.getAttribute("value"));
        driver.findElement(By.id("cercaB")).click();
        driver.findElement(By.id("buttonI")).click();
        //ripreto controllo
        driver.findElement(By.id("buttonL")).click();
        WebElement titolo=driver.findElement(By.id("cercaL"));
        titolo.sendKeys("Erasgon Vol 1");
        PropertyUtils.setProperty(ricB,"titoloB",titolo.getAttribute("value"));
        driver.findElement(By.id("cercaB")).click();
        assertNotNull(PropertyUtils.getProperty(ricB,"titoloB"));

    }


    @Test
    void testRicercaPerGiornale() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
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
        //schermata per gestione utenti(in comuni con utente loggato)
        driver.findElement(By.id("utentiB")).click();
        driver.findElement(By.id("buttonRic")).click();
        driver.findElement(By.id("buttonG")).click();
        //schermata ricerca -> vadoper libri
        WebElement editore=driver.findElement(By.id("cercaL"));
        editore.sendKeys("hoepli");
        PropertyUtils.setProperty(ricB,"editoreB",editore.getAttribute("value"));
        driver.findElement(By.id("cercaB")).click();
        driver.findElement(By.id("buttonI")).click();
        //ripreto controllo
        driver.findElement(By.id("buttonG")).click();
        WebElement titolo=driver.findElement(By.id("cercaL"));
        titolo.sendKeys("La Republica1");
        PropertyUtils.setProperty(ricB,"titoloB",titolo.getAttribute("value"));
        driver.findElement(By.id("cercaB")).click();
        assertNotNull(PropertyUtils.getProperty(ricB,"titoloB"));

    }



    @Test
    void testRicercaPerRivista() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
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
        //schermata per gestione utenti(in comuni con utente loggato)
        driver.findElement(By.id("utentiB")).click();
        driver.findElement(By.id("buttonRic")).click();
        driver.findElement(By.id("buttonR")).click();
        //schermata ricerca -> vadoper libri
        WebElement autore=driver.findElement(By.id("cercaL"));
        autore.sendKeys("bao publishing");
        PropertyUtils.setProperty(ricB,"autoreB",autore.getAttribute("value"));
        driver.findElement(By.id("cercaB")).click();
        driver.findElement(By.id("buttonI")).click();
        //ripreto controllo
        driver.findElement(By.id("buttonR")).click();
        WebElement titolo=driver.findElement(By.id("cercaL"));
        titolo.sendKeys("focus");
        PropertyUtils.setProperty(ricB,"titoloB",titolo.getAttribute("value"));
        driver.findElement(By.id("cercaB")).click();
        assertNotNull(PropertyUtils.getProperty(ricB,"titoloB"));

    }








    @AfterEach
    void chiudiTest()
    {
        driver.close();

    }






}
