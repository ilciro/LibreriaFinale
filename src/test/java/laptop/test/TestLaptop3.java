package laptop.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;
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



class TestLaptop3 {

    private final ResourceBundle rBPagamento= ResourceBundle.getBundle("configurations/pagamento");
    private final ResourceBundle rBPagamentoInfo= ResourceBundle.getBundle("configurations/pagamentoInfo");
    private final ResourceBundle rBFattura=ResourceBundle.getBundle("configurations/fattura");

    private final ResourceBundle rBGiornale=ResourceBundle.getBundle("configurations/settaOggetto");
    private final ResourceBundle rBNegozio=ResourceBundle.getBundle("configurations/negozio");
    private final ResourceBundle rBPagamentoEsito=ResourceBundle.getBundle("configurations/pagamentoEsito");
    private final ResourceBundle rBInsertRivista=ResourceBundle.getBundle("configurations/rivistaDAInserire");
    private final ResourceBundle rBUtente=ResourceBundle.getBundle("configurations/utente");
	private final ResourceBundle rBInsetLibro = ResourceBundle.getBundle("configurations/libroDaInserire");
	private final ResourceBundle rBCartaCredito = ResourceBundle.getBundle("configurations/cartaCredito");

	private AcquistaBean aB=new AcquistaBean();
	private CartaCreditoBean ccB=new CartaCreditoBean();
	private java.sql.Date dataS= new java.sql.Date(Calendar.getInstance().getTime().getTime());
	private CartaDiCredito cc1=new CartaDiCredito(rBPagamentoInfo.getString("nome"),rBPagamentoInfo.getString("cognome"),rBPagamentoInfo.getString("codice"),dataS,rBPagamentoInfo.getString("civ"),Float.parseFloat(rBPagamentoInfo.getString("ammontare")));

	private CartaDiCredito cc2=new CartaDiCredito(rBPagamentoInfo.getString("nome"),rBPagamentoInfo.getString("cognome"),rBPagamentoInfo.getString("codice"),dataS,rBPagamentoInfo.getString("civ"),Float.parseFloat(rBPagamentoInfo.getString("ammontare")));
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
	
	
	
	

    /*
	@Test
	void testTitolo() {
		aB.setTitoloB("Titolo prova");
		assertNotNull(aB.getTitoloB());
	}*/
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
	void testCartaCreditoBeanSetter()
	{
		ccB.setNomeB(cDao.getCarteCredito(rBCartaCredito.getString("nome2")).get(0).getNomeUser());
		ccB.setCognomeB(cDao.getCarteCredito(rBCartaCredito.getString("nome2")).get(0).getCognomeUser());
		ccB.setNumeroCCB(cDao.getCarteCredito(rBCartaCredito.getString("nome2")).get(0).getNumeroCC());
		ccB.setDataScadB(cDao.getCarteCredito(rBCartaCredito.getString("nome2")).get(0).getScadenza());
		ccB.setCivB(cDao.getCarteCredito(rBCartaCredito.getString("nome2")).get(0).getCiv());
		assertNotNull(ccB.getNomeB());

	}
    /*
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
	}*/
	@Test
	void testListaCarte() throws SQLException
	{
		cDao.insCC(cc1);
		ccB.setListaCarteCreditoB(cDao.getCarteCredito(rBPagamentoInfo.getString("nome")));
		assertNotNull(ccB.getListaCarteCreditoB());
	}
    /*
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
	}*/
	@Test
	void testFattura()
	{
		fB.setNomeB(rBFattura.getString("nome1"));
		fB.setCognomeB(rBFattura.getString("cognome1"));
		fB.setIndirizzoB(rBFattura.getString("via"));
		fB.setComunicazioniB(rBFattura.getString("comunicazioni2"));
		assertNotNull(fB.getNomeB());
	}
	@Test
	void testCreaGiornale() throws SQLException
	{
		gB.setTitoloB(rBGiornale.getString("titolo2"));
		gB.setTipologiaB(rBGiornale.getString("categoria2"));
		gB.setLinguaB(rBGiornale.getString("lingua2"));
		gB.setEditoreB(rBGiornale.getString("editore2"));
		gB.setDataPubbB(LocalDate.of(2022, 12,12));
		gB.setCopieRimanentiB(Integer.parseInt(rBGiornale.getString("copieRim2")));
		gB.setDisponibilitaB(0);
		gB.setPrezzoB(Float.parseFloat(rBGiornale.getString("prezzo2")));
		
		
		assertNotNull(gB.getDataPubbB());
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
		n.setNome(rBNegozio.getString("nome2"));
		n.setIsOpen(Boolean.parseBoolean(rBNegozio.getString("isOpen2")));
		n.setIsValid(Boolean.parseBoolean(rBNegozio.getString("isValid2")));
		n.setVia(rBNegozio.getString("via2"));
		nB.setNomeB(n.getNome());
		nB.setIndirizzoB(n.getVia());
		nB.setAperturaB(n.getIsOpen());
		nB.setDisponibileB(n.getIsValid());
		assertNotNull(nB.getNomeB());
	}
	@Test
	void testPagamento() throws SQLException
	{

		Pagamento p=new Pagamento(Integer.parseInt(rBPagamentoEsito.getString("id1")), rBPagamentoEsito.getString("metodo"), Integer.parseInt(rBPagamentoEsito.getString("esito")), rBPagamentoEsito.getString("nomeUtente"), Float.parseFloat(rBPagamentoEsito.getString("spesa1")), rBPagamentoEsito.getString("tipo"));
		Pagamento p1=new Pagamento(Integer.parseInt(rBPagamentoEsito.getString("id2")), rBPagamentoEsito.getString("metodo"), Integer.parseInt(rBPagamentoEsito.getString("esito")), rBPagamentoEsito.getString("nomeUtente"), Float.parseFloat(rBPagamentoEsito.getString("spesa2")), rBPagamentoEsito.getString("tipo"));


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
		info[0]=rBInsertRivista.getString("info[0]");
		info[1]=rBInsertRivista.getString("info[1]");
		info[2]=rBInsertRivista.getString("info[2]");
		info[3]=rBInsertRivista.getString("info[3]");
		info[4]=rBInsertRivista.getString("info[4]");
		r.setDescrizione(rBInsertRivista.getString("descrizione"));
		r.setDataPubb(LocalDate.of(2022, 11,12));
		r.setDisp(Integer.parseInt(rBInsertRivista.getString("disponibilita")));
		r.setPrezzo(Float.parseFloat(rBInsertRivista.getString("prezzo")));
		r.setCopieRim(Integer.parseInt(rBInsertRivista.getString("copieRimanenti")));
		
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
	/*@Test
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
	*/

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
		uB.setNomeB(rBUtente.getString("nomeU"));
		uB.setCognomeB(rBUtente.getString("cognomeU"));
		uB.setEmailB(rBUtente.getString("emailU"));
		uB.setPasswordB(rBUtente.getString("passU"));
		uB.setDescrizioneB(rBUtente.getString("descU"));
		uB.setDataDiNascitaB(LocalDate.of(1963, 8,11));
		uB.setIdRuolo(rBUtente.getString("ruoloU"));
		assertNotNull(uB.getEmailB());
	}
	@Test
	void testCreaLibroBean()
	{
		lB.setIdB(Integer.parseInt(rBInsetLibro.getString("IdB")));
		lB.setTitoloB(rBInsetLibro.getString("TitoloB"));
		lB.setCodIsbnB(rBInsetLibro.getString("CodIsbnB"));
		lB.setEditoreB(rBInsetLibro.getString("EditoreB"));
		lB.setAutoreB(rBInsetLibro.getString("AutoreB"));
		lB.setLinguaB(rBInsetLibro.getString("LinguaB"));
		lB.setDateB(dataS);
		lB.setRecensioneB(rBInsetLibro.getString("RecensioneB"));
		lB.setNrCopieB(Integer.parseInt(rBInsetLibro.getString("NrCopieB")));
		lB.setDisponibilitaB(Integer.parseInt(rBInsetLibro.getString("DisponibilitaB")));
		lB.setDataPubbB(LocalDate.now());
		lB.setTipologiaB(rBInsetLibro.getString("TipologiaB"));
		lB.setcategoriaB(rBInsetLibro.getString("categoriaB"));
		lB.setPrezzoB(Float.parseFloat(rBInsetLibro.getString("PrezzoB")));
		lB.setDescB(rBInsetLibro.getString("descrizioneB"));
		lB.setNumeroPagineB(Integer.parseInt(rBInsetLibro.getString("NumeroPagineB")));
		assertNotNull(lB.getCodIsbnB());
	}
	@Test
	void testLibriBean() throws SQLException
	{
		lB.setListaLibriB(lD.getLibri());
		assertNotNull(lB.getListaLibriB());
	}
	/*
	@Test
	void testCategorieLibriBean()
	{
		assertNotNull(lB.setCategorie());
	}*/
	@ParameterizedTest
	@ValueSource(strings = {"ARTE","BIOGRAFIE","DIARI_MEMORIE","CALENDARI_AGENDE","DIZINARI_OPERE","GIALLI_THRILLER","COMPUTER_GIOCHI","LIBRI_UNIVERSITARI"})
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

	/*
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



	 */


}
