/*package web.test;


import laptop.database.*;
import laptop.model.TempUser;
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


import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;


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

    private final TempUser tUser=TempUser.getInstance();
    private final TextAreaBean tAB=new TextAreaBean();
    private final SystemBean sB=SystemBean.getInstance();
    private final AcquistaBean aB=new AcquistaBean();
    private final FatturaBean fB=new FatturaBean();

    private final CartaCreditoBean cCB=new CartaCreditoBean();
    private final PagamentoBean pB=new PagamentoBean();
    private final NegozioBean nB=new NegozioBean();
    private final NegozioDao nD=new NegozioDao();


           // Test for Admin all functionalities



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
    void testLoginAdminRaccoltaGiornale() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException {
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
    void testLoginAdminReport() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
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

    //    test for user


    @Test
    void testLoginUserLibro() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException {
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
        //schermata utente: libro , giornale , rivista , logout,ricerca
        driver.findElement(By.id("buttonL")).click();


        PropertyUtils.setProperty(lB,"elencoLibriB", lD.getLibri());


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
    void testLoginUtenteAnnullaRivista() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException {
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
        driver.findElement(By.id("idOgg")).sendKeys("1");
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



        assertEquals(1,PropertyUtils.getProperty(sB,"idB"));



    }

        // test for Writer

    @Test
    void testLoginRicercaScrittoreByTitoloLibro() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        //schermata index
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/original-LibreriaMaven/index.jsp");
        driver.findElement(By.id("buttonLogin")).click();
        driver.findElement(By.id("emailL")).sendKeys("zerocalcare@gmail.com");
        driver.findElement(By.id("passL")).sendKeys("Zerocalcare21");
        PropertyUtils.setProperty(uB,"emailB",driver.findElement(By.id("emailL")).getAttribute("value"));
        PropertyUtils.setProperty(uB,"passB",driver.findElement(By.id("passL")).getAttribute("value"));
        driver.findElement(By.id("loginB")).click();
        //schermata scelta
        driver.findElement(By.id("buttonRic")).click();
        //schemata ricerca
        driver.findElement(By.id("buttonL")).click();
        //research in books
        //titolo
        WebElement titoloL=driver.findElement(By.id("cercaL"));
        titoloL.sendKeys("Erasgon Vol 1");
        String titoloLibro=titoloL.getAttribute("value");

        driver.findElement(By.id("cercaB")).click();
        PropertyUtils.setProperty(lB,"titoloB",titoloLibro);
        l.setTitolo(PropertyUtils.getProperty(lB,"titoloB").toString());


        assertNotNull(lD.getLibriByName(l));


    }
    @Test
    void testLoginRicercaScrittoreByAutoreLibro() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        //schermata index
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/original-LibreriaMaven/index.jsp");
        driver.findElement(By.id("buttonLogin")).click();
        driver.findElement(By.id("emailL")).sendKeys("zerocalcare@gmail.com");
        driver.findElement(By.id("passL")).sendKeys("Zerocalcare21");
        PropertyUtils.setProperty(uB,"emailB",driver.findElement(By.id("emailL")).getAttribute("value"));
        PropertyUtils.setProperty(uB,"passB",driver.findElement(By.id("passL")).getAttribute("value"));
        driver.findElement(By.id("loginB")).click();
        //schermata scelta
        driver.findElement(By.id("buttonRic")).click();
        //schemata ricerca
        driver.findElement(By.id("buttonL")).click();
        //research in books
        //titolo
        WebElement titoloL=driver.findElement(By.id("cercaL"));
        titoloL.sendKeys("CiccioGamer89");
        String titoloLibro=titoloL.getAttribute("value");

        driver.findElement(By.id("cercaB")).click();
        PropertyUtils.setProperty(lB,"titoloB",titoloLibro);
        l.setTitolo(PropertyUtils.getProperty(lB,"titoloB").toString());


        assertNotNull(lD.getLibriByName(l));


    }

    // test for editor
    @Test
    void testLoginRicercaScrittoreByEditoreGiornale() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        //schermata index
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/original-LibreriaMaven/index.jsp");
        driver.findElement(By.id("buttonLogin")).click();
        driver.findElement(By.id("emailL")).sendKeys("zerocalcare@gmail.com");
        driver.findElement(By.id("passL")).sendKeys("Zerocalcare21");
        PropertyUtils.setProperty(uB,"emailB",driver.findElement(By.id("emailL")).getAttribute("value"));
        PropertyUtils.setProperty(uB,"passB",driver.findElement(By.id("passL")).getAttribute("value"));
        driver.findElement(By.id("loginB")).click();
        //schermata scelta
        driver.findElement(By.id("buttonRic")).click();
        //schemata ricerca
        driver.findElement(By.id("buttonG")).click();
        //research in books
        //titolo
        WebElement editoreG=driver.findElement(By.id("cercaL"));
        editoreG.sendKeys("La Republica");
        String editoreGiornale=editoreG.getAttribute("value");

        driver.findElement(By.id("cercaB")).click();
        PropertyUtils.setProperty(gB,"editoreB",editoreGiornale);
        g.setEditore(PropertyUtils.getProperty(gB,"editoreB").toString());


        assertNotNull(gD.getGiornaliByName(g));


    }
    @Test
    void testLoginRicercaScrittoreByTitoloGiornale() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        //schermata index
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/original-LibreriaMaven/index.jsp");
        driver.findElement(By.id("buttonLogin")).click();
        driver.findElement(By.id("emailL")).sendKeys("zerocalcare@gmail.com");
        driver.findElement(By.id("passL")).sendKeys("Zerocalcare21");
        PropertyUtils.setProperty(uB,"emailB",driver.findElement(By.id("emailL")).getAttribute("value"));
        PropertyUtils.setProperty(uB,"passB",driver.findElement(By.id("passL")).getAttribute("value"));
        driver.findElement(By.id("loginB")).click();
        //schermata scelta
        driver.findElement(By.id("buttonRic")).click();
        //schemata ricerca
        driver.findElement(By.id("buttonG")).click();
        //research in books
        //titolo
        WebElement titoloG=driver.findElement(By.id("cercaL"));
        titoloG.sendKeys("La Republica");
        String titoloGiornale=titoloG.getAttribute("value");

        driver.findElement(By.id("cercaB")).click();
        PropertyUtils.setProperty(gB,"titoloB",titoloGiornale);
        g.setTitolo(PropertyUtils.getProperty(gB,"titoloB").toString());


        assertNotNull(gD.getGiornaliByName(g));


    }

    @Test
    void testLoginRicercaScrittoreByTitoloRivista() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        //schermata index
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/original-LibreriaMaven/index.jsp");
        driver.findElement(By.id("buttonLogin")).click();
        driver.findElement(By.id("emailL")).sendKeys("BaoPublishing@gmail.com");
        driver.findElement(By.id("passL")).sendKeys("BaoPub2021");
        PropertyUtils.setProperty(uB,"emailB",driver.findElement(By.id("emailL")).getAttribute("value"));
        PropertyUtils.setProperty(uB,"passB",driver.findElement(By.id("passL")).getAttribute("value"));
        driver.findElement(By.id("loginB")).click();
        //schermata scelta
        driver.findElement(By.id("buttonRic")).click();
        //schemata ricerca
        driver.findElement(By.id("buttonL")).click();
        //research in books
        //titolo
        WebElement titoloR=driver.findElement(By.id("cercaL"));
        titoloR.sendKeys("Focus");
        String titoloRivista=titoloR.getAttribute("value");

        driver.findElement(By.id("cercaB")).click();
        PropertyUtils.setProperty(rB,"titoloB",titoloRivista);
        r.setTitolo(PropertyUtils.getProperty(rB,"titoloB").toString());


        assertNotNull(rD.getRivisteByName(r));


    }
    @Test
    void testLoginRicercaScrittoreByAutoreRivista() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        //schermata index
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/original-LibreriaMaven/index.jsp");
        driver.findElement(By.id("buttonLogin")).click();
        driver.findElement(By.id("emailL")).sendKeys("BaoPublishing@gmail.com");
        driver.findElement(By.id("passL")).sendKeys("BaoPub2021");
        PropertyUtils.setProperty(uB,"emailB",driver.findElement(By.id("emailL")).getAttribute("value"));
        PropertyUtils.setProperty(uB,"passB",driver.findElement(By.id("passL")).getAttribute("value"));
        driver.findElement(By.id("loginB")).click();
        //schermata scelta
        driver.findElement(By.id("buttonRic")).click();
        //schemata ricerca
        driver.findElement(By.id("buttonL")).click();
        //research in books
        //titolo
        WebElement autoreR=driver.findElement(By.id("cercaL"));
        autoreR.sendKeys("Panorama");
        String autoreRivista=autoreR.getAttribute("value");

        driver.findElement(By.id("cercaB")).click();
        PropertyUtils.setProperty(rB,"autoreB",autoreRivista);
        r.setAutore(PropertyUtils.getProperty(rB,"autoreB").toString());


        assertNotNull(rD.getRivisteByName(r));


    }





    @AfterEach
    void chiudiTest()
    {
        driver.close();

    }



}




 */