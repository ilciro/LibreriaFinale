package web.test;

import laptop.database.GiornaleDao;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import web.bean.AcquistaBean;
import web.bean.CartaCreditoBean;
import web.bean.GiornaleBean;
import web.bean.SystemBean;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class TestWebGiornale {
    private final GiornaleBean gB=new GiornaleBean();
    private final GiornaleDao gD=new GiornaleDao();
    private final AcquistaBean aB=new AcquistaBean();
    private final CartaCreditoBean ccB=new CartaCreditoBean();
    @Test
    void testPagamentoCashEDownloadLibro() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException, ParseException {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        //schermata index
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/original-LibreriaMaven/index.jsp");
        driver.findElement(By.id("buttonL")).click();
        SystemBean.getInstance().setTypeAsDaily();
        //schermata libri
        driver.findElement(By.id("genera lista")).click();
        PropertyUtils.setProperty(gB, "listaGiornaliB", gD.getGiornali());
        WebElement ricerca = driver.findElement(By.id("idOgg"));
        ricerca.clear();
        ricerca.sendKeys("10");
        String id = ricerca.getAttribute("value");
        PropertyUtils.setProperty(SystemBean.getInstance(), "idB", Integer.parseInt(id));
        driver.findElement(By.id("procedi")).click();
        //schermata acquista
        WebElement titolo = driver.findElement(By.id("nome"));
        String t = titolo.getAttribute("value");
        PropertyUtils.setProperty(aB, "titoloB", t);
        PropertyUtils.setProperty(SystemBean.getInstance(), "titoloB", t);
        WebElement quantita = driver.findElement(By.id("quantita"));
        quantita.clear();
        quantita.sendKeys("2");
        int q = Integer.parseInt(quantita.getAttribute("value"));
        PropertyUtils.setProperty(SystemBean.getInstance(), "quantitaB", q);
        driver.findElement(By.id("totaleB")).click();
        WebElement prezzo = driver.findElement(By.id("totale"));
        float tot = Float.parseFloat(prezzo.getAttribute("value"));
        PropertyUtils.setProperty(aB, "prezzoB", tot);
        PropertyUtils.setProperty(SystemBean.getInstance(), "spesaTB", tot);
        //scelgo il metodo di pagamento
        WebElement input = driver.findElement(By.xpath("//input[@list='metodi']"));
        WebElement option = driver.findElement(By.xpath("//*[@id='metodi']/option[2]"));
        String value = option.getAttribute("value");
        input.sendKeys(value);
        PropertyUtils.setProperty(SystemBean.getInstance(), "metodoPB", value);
        driver.findElement(By.xpath("//input[@id='negozioB']")).click();
        //schermata carta credito
        driver.findElement(By.id("nomeL")).sendKeys("luigiB");
        driver.findElement(By.id("cognomeL")).sendKeys("neriB");
        driver.findElement(By.id("cartaL")).sendKeys("1526-8549-9662-1100");
        driver.findElement(By.id("scadL")).sendKeys("2028/08/11");
        driver.findElement(By.id("passL")).sendKeys("145");
        String nome=driver.findElement(By.id("nomeL")).getAttribute("value");
        String cognome=driver.findElement(By.id("cognomeL")).getAttribute("value");
        String codice=driver.findElement(By.id("cartaL")).getAttribute("value");

        //added this
        String scadenza=driver.findElement(By.id("scadL")).getAttribute("value");


        java.util.Date utilDate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");


        utilDate = format.parse(scadenza);


        String password=driver.findElement(By.id("passL")).getAttribute("value");
        PropertyUtils.setProperty(ccB,"nomeB",nome);
        PropertyUtils.setProperty(ccB,"cognomeB",cognome);
        PropertyUtils.setProperty(ccB,"numeroCCB",codice);
        PropertyUtils.setProperty(ccB,"dataScadB",utilDate);
        PropertyUtils.setProperty(ccB,"civB",password);
        driver.findElement(By.id("buttonI")).click();
        //schermata negozio
        driver.findElement(By.id("buttonNeg2")).click();









/*

        //schermata fattura
        driver.findElement(By.id("nomeL")).sendKeys("francoB");
        driver.findElement(By.id("cognomeL")).sendKeys("rossiB");
        driver.findElement(By.id("indirizzoL")).sendKeys("via papaveri 12");
        driver.findElement(By.id("com")).sendKeys("il cap è 00005 . Chiamare prima al numero");
        String nome=driver.findElement(By.id("nomeL")).getAttribute("value");
        String cognome=driver.findElement(By.id("cognomeL")).getAttribute("value");
        String indirizzo=driver.findElement(By.id("indirizzoL")).getAttribute("value");
        String com=driver.findElement(By.id("com")).getAttribute("value");
        //setto fattura
        PropertyUtils.setProperty(fB,"nomeB",nome);
        PropertyUtils.setProperty(fB,"cognomeB",cognome);
        PropertyUtils.setProperty(fB,"indirizzoB",indirizzo);
        PropertyUtils.setProperty(fB,"comunicazioniB",com);
        //setto pagamento
        PropertyUtils.setProperty(pB,"idB",0);
        PropertyUtils.setProperty(pB,"metodoB", PropertyUtils.getProperty(SystemBean.getInstance(),"metodoPB"));
        PropertyUtils.setProperty(pB,"ammontareB",PropertyUtils.getProperty(SystemBean.getInstance(),"spesaTB"));
        PropertyUtils.setProperty(pB,"esitoB",0);
        PropertyUtils.setProperty(pB,"nomeUtenteB","");
        driver.findElement(By.id("buttonC")).click();
        //schermata download
        String titoloL=driver.findElement(By.id("titoloL")).getAttribute("value");
        PropertyUtils.setProperty(dB,"idB",SystemBean.getInstance().getIdB());
        PropertyUtils.setProperty(dB,"titoloB",titoloL);
        driver.findElement(By.id("downloadB")).click();
        driver.quit();


    }

 */
    }
}
