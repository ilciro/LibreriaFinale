/*package laptop.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;

import laptop.utilities.ConnToDb;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import laptop.database.CartaCreditoDao;
import laptop.database.GiornaleDao;
import laptop.database.LibroDao;
import laptop.database.PagamentoDao;
import laptop.database.RivistaDao;
import laptop.database.UsersDao;
import laptop.exception.LogoutException;
import laptop.model.CartaDiCredito;
import laptop.model.Negozio;
import laptop.model.Pagamento;

import laptop.model.raccolta.Rivista;
import web.bean.AcquistaBean;
import web.bean.CartaCreditoBean;
import web.bean.DownloadBean;
import web.bean.FatturaBean;
import web.bean.GiornaleBean;
import web.bean.LibroBean;
import web.bean.ModificaOggettoBean;
import web.bean.NegozioBean;
import web.bean.PagamentoBean;
import web.bean.RicercaBean;
import web.bean.RivistaBean;
import web.bean.SystemBean;
import web.bean.TextAreaBean;
import web.bean.UserBean;



class TestLaptop4 {
	private AcquistaBean aB=new AcquistaBean();
	private CartaCreditoBean ccB=new CartaCreditoBean();
	private java.sql.Date dataS= new java.sql.Date(Calendar.getInstance().getTime().getTime());
	private CartaDiCredito cc1=new CartaDiCredito(3,"8541-8596-5552-9858",3000.0,200,dataS,"pippo",(float)156.3);
	private CartaDiCredito cc2=new CartaDiCredito("pippo","pluto","1452-9958-7485-2222",new Date(),"852",(float)125.36);
	private CartaCreditoDao cDao=new CartaCreditoDao();
	private DownloadBean dB=new DownloadBean();
	private FatturaBean fB=new FatturaBean();
	private GiornaleBean gB=new GiornaleBean();
	private GiornaleDao gD=new GiornaleDao();
	private LibroBean lB=new LibroBean();
	private LibroDao lD=new LibroDao();
	private String[] info=new String[7];
	private ModificaOggettoBean mOB=new ModificaOggettoBean();
	private NegozioBean nB=new NegozioBean();
	private Negozio n=new Negozio();
	private PagamentoBean pB=new PagamentoBean();
	private PagamentoDao pD=new PagamentoDao();
	private RicercaBean rB=new RicercaBean();
	private Rivista r=new Rivista();
	private RivistaDao rD=new RivistaDao();
	private RivistaBean rivB=new RivistaBean();
	private SystemBean visB=SystemBean.getInstance();
	private TextAreaBean tAB=new TextAreaBean();
	private UserBean uB=UserBean.getInstance();
	
	
	
	

	@Test
	void testTitolo() {
		aB.setTitoloB("Titolo prova");
		assertNotNull(aB.getTitoloB());
	}
	@ParameterizedTest
	@ValueSource(strings= {"libro","giornale","rivista"})
	void testTipo(String strings)
	{
		aB.setTipoB(strings);
		assertEquals(aB.getTipoB(),strings);
	}
	@ParameterizedTest
	@ValueSource(floats= {(float) 1.25,(float) 6.35,(float) 8.25})
	void testPrezzo(Float floats)
	{
		aB.setPrezzoB(floats);
		assertEquals(aB.getPrezzoB(),floats);
	}
	@Test
	void testNomeCarta()
	{
		ccB.setNomeB(cc1.getNomeUser());
		assertNotNull(ccB.getNomeB());
	}
	@Test
	void testCognomeCarta()
	{
		ccB.setCognomeB(cc2.getCognomeUser());
		assertNotNull(ccB.getCognomeB());
	}
	@Test
	void testCodiceCarta()
	{
		ccB.setNumeroCCB(cc2.getNumeroCC());
		assertNotNull(ccB.getNumeroCCB());
	}
	@Test
	void testScadenza()
	{
		ccB.setDataScadB(cc2.getScadenza());
		assertNotNull(ccB.getDataScadB());
	}
	@Test
	void testCiv()
	{
		ccB.setCivB(cc2.getCiv());
		assertNotNull(ccB.getCivB());
	}
	@Test
	void testListaCarte() throws SQLException
	{
		cDao.insCC(cc1);
		ccB.setListaCarteCreditoB(cDao.getCarteCredito("pippo"));
		assertEquals(1,ccB.getListaCarteCreditoB().size());
	}
	@Test
	void testPrezzoTransazione()
	{
		ccB.setPrezzoTransazioneB(cc2.getPrezzoTransazine());
		assertEquals((float)125.36,ccB.getPrezzoTransazioneB());
	}
	@Test
	void testID()
	{
		dB.setIdB(5);
		assertEquals(5,dB.getIdB());
	}
	@ParameterizedTest
	@ValueSource(strings= {"titolo1","titolo2"})
	void testTitolo(String strings)
	{
		dB.setTitoloB(strings);
		assertNotNull(dB.getTitoloB());
	}
	@Test
	void testFattura()
	{
		fB.setNomeB("pippo");
		fB.setCognomeB("pluto");
		fB.setIndirizzoB("via paperopoli 23");
		fB.setComunicazioniB("");
		assertNotNull(fB.getNomeB());
	}
	@Test
	void testCreaGiornale() throws SQLException
	{
		gB.setTitoloB("giornale da inserire");
		gB.setTipologiaB("quotidiano");
		gB.setLinguaB("ita");
		gB.setEditoreB("editore ins prova");
		gB.setDataPubbB(LocalDate.of(2022, 12,12));
		gB.setCopieRimanentiB(100);
		gB.setDisponibilitaB(0);
		gB.setPrezzoB((float)2.45);
		
		
		assertNotNull(gB);
	}
	@Test
	void testListaGiornali() throws SQLException
	{
		gB.setListaGiornaliB(gD.getGiornali());
		assertNotNull(gB.getListaGiornaliB());
	}
	
	@Test
	void testListaLibri() throws SQLException
	{
		lB.setListaLibriB(lD.getLibri());
		assertNotNull(lB.getListaLibriB());
	}
	@Test
	void testModificaOggettoList() throws SQLException
	{
		mOB.setMiaListaB(lD.getLibri());
		assertNotNull(mOB.getMiaListaB());
	}
	@Test
	void testCreaNegozio()  {
		n.setNome("Negozio E");
		n.setIsOpen(true);
		n.setIsValid(true);
		n.setVia("via rosata 15");
		nB.setNomeB(n.getNome());
		nB.setIndirizzoB(n.getVia());
		nB.setAperturaB(n.getIsOpen());
		nB.setDisponibileB(n.getIsValid());
		assertNotNull(n);
	}
	@Test
	void testPagamento() throws SQLException
	{

		Pagamento p=new Pagamento(1, "cash", 0, "pippo", (float)16.3, "cash");
		Pagamento p1=new Pagamento(2, "cash", 0, "pippo", (float)18.5, "cash");

		pB.setIdB(p.getId());
		pB.setEsitoB(p.getEsito());
		pB.setNomeUtenteB(p.getNomeUtente());
		pB.setAmmontareB(p.getAmmontare());
		pB.setTipoB(p.getTipo());
		pB.setIdOggettoB(p.getIdOggetto());
		pD.inserisciPagamento(p1);
		pD.inserisciPagamento(p);
		
		pB.setListaPagamentiB(pD.getPagamenti());
		assertNotNull(pB.getListaPagamentiB());

	}
	@Test
	void testRicercaBean() throws SQLException
	{
		rB.setListaB(lD.getLibri());
		assertNotNull(rB.getListaB());
	}
	@Test
	void testInsertRivistaBean() throws SQLException
	{
		info[0]="ins rivista prova";
		info[1]="SETTIMANALE";
		info[2]="autore ins rivi";
		info[3]="ita";
		info[4]="editore ins rivi";
		r.setDescrizione("aaa");
		r.setDataPubb(LocalDate.of(2022, 11,12));
		r.setDisp(0);
		r.setPrezzo((float)1.25);
		r.setCopieRim(5);
		
		rivB.setTitoloB(r.getTitolo());
		rivB.setTipologiaB(r.getTipologia());
		rivB.setEditoreB(r.getEditore());
		rivB.setAutoreB(r.getAutore());
		rivB.setLinguaB(r.getLingua());
		rivB.setDescrizioneB(r.getDescrizione());
		rivB.setDataB(dataS);
		rivB.setDispB(r.getDisp());
		rivB.setPrezzoB(r.getPrezzo());
		rivB.setCopieRimB(r.getCopieRim());
		
		rD.creaRivista(r);
		rivB.setListaRivisteB(rD.getRiviste());
		assertNotNull(rivB.getListaRivisteB());
	}
	@Test
	void testParametriSystemBean()
	{
		visB.setIdB(5);
		visB.setTypeAsBook();
		visB.setLoggedB(false);
		visB.setPickupB(false);
		visB.setSpesaTB((float)115.6);
		visB.setQuantitaB(8);
		visB.setMetodoPB("cc");
		visB.setNegozioSelezionatoB(true);
		visB.setTitoloB("prova");
		assertEquals(5,visB.getIdB());
	}
	@Test
	void testReportUtenti() throws IOException, SQLException
	{
		assertNotNull(TextAreaBean.getListaUtenti());
	}
	@Test
	void testReportG() throws SQLException, IOException
	{
		assertNotNull(tAB.generaReportG());
	}
	@Test
	void testReportL() throws SQLException, IOException
	{
		assertNotNull(tAB.generaReportL());
	}
	@Test
	void testReportR() throws SQLException, IOException
	{
		assertNotNull(tAB.generaReportR());
	}
	@Test
	void testUser()
	{
		uB.setNomeB("nomeU");
		uB.setCognomeB("cognomeU");
		uB.setEmailB("nomeCognomeU@gmail.com");
		uB.setPasswordB("nome52");
		uB.setDescrizioneB("prova");
		uB.setDataDiNascitaB(LocalDate.of(1936, 8,11));
		uB.setIdRuolo("EDITORE");
		assertNotNull(uB.getEmailB());
	}
	@Test
	void testCreaLibroBean()
	{
		lB.setIdB(5);
		lB.setTitoloB("libro prova");
		lB.setCodIsbnB("1526369845");
		lB.setEditoreB("editore prova");
		lB.setAutoreB("autore prova");
		lB.setLinguaB("de");
		lB.setDateB(dataS);
		lB.setRecensioneB(" autore cimentato in nuova avventura ");
		lB.setNrCopieB(150);
		lB.setRecensioneB("primo manuale in tedesco");
		lB.setDisponibilitaB(1);
		lB.setDataPubbB(LocalDate.now());
		lB.setNrCopieB(47);
		lB.setTipologiaB("INFORMATICA");
		lB.setcategoriaB("FAMIGLIA");
		lB.setPrezzoB((float)15.36);
		lB.setNumeroPagineB(250);
		assertNotNull(lB.getCodIsbnB());
	}
	@Test
	void testLibriBean() throws SQLException
	{
		lB.setListaLibriB(lD.getLibri());
		assertNotNull(lB.getListaLibriB());
	}
	@Test
	void testCategorieLibriBean()
	{
		assertNotNull(lB.setCategorie());
	}
	@ParameterizedTest
	@ValueSource(strings = {"BIOGRAFIE","WEB_DIGITAL_MEDIA","DIZINARI_OPERE","FANTASCIENZA_FANTASY","POLITICA" ,"GIALLI_THRILLER","LIBRI_SCOLASTICI","LIBRI_UNIVERSITARI","HUMOR"})
	void testCategorie(String strings)
	{
		lB.setcategoriaB(strings);
		assertEquals(lB.getcategoriaB(),strings);
	}
	@Test
	void testCategorieRivista()
	{
		assertNotNull(rivB.elencoCategorie());
	}
	@ParameterizedTest
	@ValueSource(strings= {"ADMIN","SCRITTORE","UTENTE","EDITORE"})
	void testUserBeanType(String strings)
	{
		uB.setIdRuolo(strings);
		assertEquals(uB.getrB(),strings);
	}
	@Test 
	void testLogout() throws LogoutException
	{
		UserBean.getInstance().setNomeB("franco");
		assertTrue(UsersDao.logout());
	}
	@AfterAll
	static void ripristinaDB() throws FileNotFoundException {

		 Connection conn;
		 ScriptRunner sr;

		java.util.logging.Logger.getLogger("Test ripristina db").log(Level.INFO,"---------Chiamo stored truncate---------\n\n");

		conn= ConnToDb.generalConnection();
		sr = new ScriptRunner(conn);
		sr.setSendFullScript(true);
		Reader reader = new BufferedReader(new FileReader("FileSql/dropSchema.sql"));
		//Running the script
		sr.runScript(reader);

	}

	
}
*/