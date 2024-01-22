//package web.test;
//
//import laptop.database.GenerateDaoReportClass;
//import laptop.database.RivistaDao;
//import laptop.model.raccolta.Rivista;
//import org.apache.commons.beanutils.PropertyUtils;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import web.bean.RivistaBean;
//import web.bean.UserBean;
//
//import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
//
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//
//public class TestLoginRaccoltaRivista {
//
//    private int id;
//    private String titolo;
//    WebDriver driver;
//    private final RivistaDao rD=new RivistaDao();
//    private final Rivista r=new Rivista();
//    private final RivistaBean rB=new RivistaBean();
//
//    private final UserBean uB=UserBean.getInstance();
//    private static final String RIVISTA="rivista";
//
//
//    public TestLoginRaccoltaRivista() throws IOException {
//    }
//
//    private int getId() {
//        return id;
//    }
//
//    private void setId(int id) {
//        this.id = id;
//    }
//
//    private String getTitolo() {
//        return titolo;
//    }
//
//    private void setTitolo(String titolo) {
//        this.titolo = titolo;
//    }
//
//    @Test
//    void testLoginAdminRaccoltaRiviste() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
//        String titolo;
//
//        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
//        //schermata index
//        driver = new ChromeDriver();
//        driver.get("http://localhost:8080/original-LibreriaMaven/index.jsp");
//        driver.findElement(By.id("buttonLogin")).click();
//        driver.findElement(By.id("emailL")).sendKeys("admin@admin.com");
//        driver.findElement(By.id("passL")).sendKeys("Admin871");
//        PropertyUtils.setProperty(uB,"emailB",driver.findElement(By.id("emailL")).getAttribute("value"));
//        PropertyUtils.setProperty(uB,"passB",driver.findElement(By.id("passL")).getAttribute("value"));
//        driver.findElement(By.id("loginB")).click();
//        //schermata admin
//        driver.findElement(By.id("raccoltaB")).click();
//        //schermata raccolta
//        driver.findElement(By.id("buttonR")).click();
//        //ho generato la lista
//        driver.findElement(By.id("buttonGenera")).click();
//        // 1 ) inserisco libro
//        driver.findElement(By.id("buttonAdd")).click();
//        driver.findElement(By.id("titoloL")).sendKeys("provo ad inserire un nuovo giornale");
//        driver.findElement(By.id("catS")).sendKeys("INVERNALE");
//        driver.findElement(By.id("autL")).sendKeys("mondadori");
//        driver.findElement(By.id("linguaL")).sendKeys("italiano");
//        driver.findElement(By.id("editoreL")).sendKeys("mondandori");
//        driver.findElement(By.id("descL")).sendKeys("prova");
//        driver.findElement(By.id("dataL")).sendKeys("2024/01/08");
//        driver.findElement(By.id("checkL")).click();
//        driver.findElement(By.id("prezzoL")).sendKeys("2.63f");
//        driver.findElement(By.id("copieL")).sendKeys("100");
//        driver.findElement(By.id("confermaB")).click();
//        driver.findElement(By.id("buttonGenera")).click();
//        GenerateDaoReportClass grCD=new GenerateDaoReportClass(RIVISTA);
//        int idOggetto=grCD.getIdMax(RIVISTA);
//        setId(idOggetto);
//        PropertyUtils.setProperty(rB,"idB",getId());
//        r.setId((Integer)PropertyUtils.getProperty(rB,"idB"));
//        driver.findElement(By.id("idL")).sendKeys(PropertyUtils.getProperty(rB,"idB").toString());
//        driver.findElement(By.id("buttonMod")).click();
//        //modif
//        driver.findElement(By.id("listaB")).click();
//        driver.findElement(By.id("titoloNR")).sendKeys("titolo aggoirnato");
//        driver.findElement(By.id("categoriaNR")).sendKeys("TELEVISIVO");
//        driver.findElement(By.id("autoreNR")).sendKeys("paperino");
//        driver.findElement(By.id("linguaNR")).sendKeys("italiano");
//        driver.findElement(By.id("editoreNR")).sendKeys("pluto");
//        driver.findElement(By.id("descNR")).sendKeys("questa rivista e stata modificata");
//        driver.findElement(By.id("dataNR")).sendKeys("2024/05/10");
//        driver.findElement(By.id("dispNR")).sendKeys("1");
//        driver.findElement(By.id("prezzoNR")).sendKeys("3.25f");
//        driver.findElement(By.id("copieNR")).sendKeys("30");
//        driver.findElement(By.id("buttonI")).click();
//        driver.findElement(By.id("buttonGenera")).click();
//
//        //delete
//        driver.findElement(By.id("idL")).sendKeys(PropertyUtils.getProperty(rB,"idB").toString());
//        driver.findElement(By.id("buttonCanc")).click();
//
//        assertNotEquals(0,PropertyUtils.getProperty(rB,"idB"));
//
//
//    }
//}
