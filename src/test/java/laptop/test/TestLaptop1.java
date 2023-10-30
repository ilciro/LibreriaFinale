package laptop.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;



import laptop.controller.*;
import laptop.exception.IdException;
import laptop.utilities.ConnToDb;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.itextpdf.text.DocumentException;

import laptop.database.ContrassegnoDao;
import laptop.database.PagamentoDao;
import laptop.model.CartaDiCredito;
import laptop.model.Fattura;
import laptop.model.Pagamento;
import laptop.model.User;
import laptop.model.raccolta.Giornale;
import laptop.model.raccolta.Libro;
import laptop.model.raccolta.Rivista;
import laptop.utilities.CreateDefaultDB;

class TestLaptop1 {


	private  final ResourceBundle rBPagamento=ResourceBundle.getBundle("configurations/pagamento");
	private  final  ResourceBundle rBPagamentoInfo=ResourceBundle.getBundle("configurations/pagamentoInfo");

	private  final ResourceBundle rBFattura=ResourceBundle.getBundle("configurations/fattura");
	private  final ResourceBundle rBPagamentoEsito = ResourceBundle.getBundle("configurations/pagamentoEsito");

	private final ResourceBundle rBLibroDAInserire = ResourceBundle.getBundle("configurations/libroDaInserire");

	private final ResourceBundle rBGiornaleDAInserire=ResourceBundle.getBundle("configurations/giornaleDaInserire");
	private final ResourceBundle rBRivistaDAInserire=ResourceBundle.getBundle("configurations/rivistaDaInserire");

	private final ResourceBundle rBModificaLibro=ResourceBundle.getBundle("configurations/libroDaModificare");

	private final ResourceBundle rBModificaGiornale=ResourceBundle.getBundle("configurations/giornaleDaModificare");

	private final ResourceBundle rBModificaRivista=ResourceBundle.getBundle("configurations/rivistaDaModificare");

	private final ResourceBundle rBSettaOggetto=ResourceBundle.getBundle("configurations/settaOggetto");


	private java.sql.Date dataS= new java.sql.Date(Calendar.getInstance().getTime().getTime());
	private static  CartaDiCredito cc1=new CartaDiCredito();
	private static CartaDiCredito cc2=new CartaDiCredito();
	private ContrassegnoDao cDao=new ContrassegnoDao();
	private Libro l=new Libro();
	private Giornale g=new Giornale();
	private Rivista r=new Rivista();
	private static ControllerSystemState vis = ControllerSystemState.getInstance() ;
	private ControllerAcquista cA=new ControllerAcquista();
	private ControllerAggiungiPage cAP=new ControllerAggiungiPage();
	private String[] info=new String[7];
	private String[] infoGen= new String[6];
	private ControllerCompravendita cC=new ControllerCompravendita();
	private ControllerDownload cD=new ControllerDownload();
	private PagamentoDao pD=new PagamentoDao();
	private ControllerGestionePage cGP=new ControllerGestionePage();
	private static User u = User.getInstance();
	private ControllerLogin cL=new ControllerLogin();
	private ControllerModificaUtente cMU=new ControllerModificaUtente();
	private ControllerModifPage cMP=new ControllerModifPage();
	private String[] cambio=new String[4];
	private String[] cambioR=new String[5];
	private String[] infoCosti=new String[6];
	private ControllerPagamentoCash cPC = new ControllerPagamentoCash();
	private ControllerRicercaPage cRicP=new ControllerRicercaPage();

	private static Fattura f=new Fattura();
	private static Fattura f1=new Fattura();

	private static Pagamento p=new Pagamento();
	private static Pagamento p2=new Pagamento();
	//used for check which test is running



	


	

	@BeforeAll
	static void creaDB() throws FileNotFoundException, SQLException
	{
		CreateDefaultDB.createDefaultDB();


	}
	@Test
	void testInizializzazioneOggetti()  {
		String titoloTest="inizializzazione oggetti";
		cc1.setTipo(Integer.parseInt(rBPagamento.getString("tipo")));
		cc1.setNumeroCC(rBPagamento.getString("numero"));
		cc1.setLimite(Double.parseDouble(rBPagamento.getString("limite")));
		cc1.setAmmontare(Double.parseDouble(rBPagamento.getString("ammontare")));
		cc1.setScadenza(dataS);
		cc1.setNomeUser(rBPagamento.getString("nomeUser"));
		cc1.setPrezzoTransazine(Float.parseFloat(rBPagamento.getString("prezzoTransazione")));

		cc2.setNomeUser(rBPagamentoInfo.getString("nome"));
		cc2.setCognomeUser(rBPagamentoInfo.getString("cognome"));
		cc2.setNumeroCC(rBPagamentoInfo.getString("codice"));
		cc2.setCiv(rBPagamentoInfo.getString("civ"));
		cc2.setScadenza(dataS);
		cc2.setAmmontare(Float.parseFloat(rBPagamentoInfo.getString("ammontare")));




		assertEquals("inizializzazione oggetti",titoloTest);

	}
	@Test
	void testCheckInsertLibro() throws SQLException {
		info[0]=rBLibroDAInserire.getString("info[0]");
		info[1]=rBLibroDAInserire.getString("info[1]");
		info[2]=rBLibroDAInserire.getString("info[2]");
		info[3]=rBLibroDAInserire.getString("info[3]");
		info[4]=rBLibroDAInserire.getString("info[4]");
		info[5]=rBLibroDAInserire.getString("info[5]");
		info[6]=rBLibroDAInserire.getString("info[6]");
		infoGen[0]=String.valueOf(100);
		infoGen[1]=rBLibroDAInserire.getString("infoGen[1]");
		infoGen[2]=rBLibroDAInserire.getString("infoGen[2]");
		infoGen[3]=rBLibroDAInserire.getString("infoGen[3]");
		infoGen[4]=rBLibroDAInserire.getString("infoGen[4]");
		infoGen[5]=rBLibroDAInserire.getString("infoGen[5]");

		l.setCategoria(rBLibroDAInserire.getString("info[1]"));
		l.setDataPubb(dataS.toLocalDate());
		l.setRecensione(rBLibroDAInserire.getString("recensione"));
		l.setDesc(rBLibroDAInserire.getString("descrizione"));


		assertTrue(cAP.checkData(info, l.getRecensione(), l.getDesc(), l.getDataPubb(), infoGen));

	}
	@Test
	void testCheckInsertGiornale() throws SQLException {
		g.setTitolo(rBGiornaleDAInserire.getString("titolo"));
		g.setTipologia(rBGiornaleDAInserire.getString("tipologia"));
		g.setLingua(rBGiornaleDAInserire.getString("lingua"));
		g.setEditore(rBGiornaleDAInserire.getString("editore"));
		g.setDataPubb(LocalDate.of(2022, 12,12));
		g.setCopieRimanenti(Integer.parseInt(rBGiornaleDAInserire.getString("copieRimanenti")));
		g.setDisponibilita(Integer.parseInt(rBGiornaleDAInserire.getString("disponibilita")));
		g.setPrezzo(Float.parseFloat(rBGiornaleDAInserire.getString("prezzo")));
		assertTrue(cAP.checkDataG(g));
	}
	@Test
	void testCheckInsertRivista() throws SQLException {
		info[0]=rBRivistaDAInserire.getString("info[0]");
		info[1]=rBRivistaDAInserire.getString("info[1]");
		info[2]=rBRivistaDAInserire.getString("info[2]");
		info[3]=rBRivistaDAInserire.getString("info[3]");
		info[4]=rBRivistaDAInserire.getString("info[4]");
		r.setDescrizione(rBRivistaDAInserire.getString("descrizione"));
		r.setDataPubb(LocalDate.of(2022, 11,12));
		r.setDisp(Integer.parseInt(rBRivistaDAInserire.getString("disponibilita")));
		r.setPrezzo(Float.parseFloat(rBRivistaDAInserire.getString("prezzo")));
		r.setCopieRim(Integer.parseInt(rBRivistaDAInserire.getString("copieRimanenti")));
		assertTrue(cAP.checkDataR(info, r.getDataPubb(), r.getDisp(),r.getPrezzo() , r.getCopieRim(), r.getDescrizione()));

	}
	@Test
	void testModificaL() throws NullPointerException, SQLException {
		ControllerSystemState.getInstance().setId(18);
		info[0]=rBModificaLibro.getString("info[0]");
		info[2]=rBModificaLibro.getString("info[2]");
		info[3]=rBModificaLibro.getString("info[3]");
		info[4]=rBModificaLibro.getString("info[4]");
		info[5]=rBModificaLibro.getString("info[5]");

		infoCosti[0]="100";
		infoCosti[1]=rBModificaLibro.getString("infoCosti[1]");
		infoCosti[3]="1";
		infoCosti[4]=String.valueOf((float)2.45);
		infoCosti[5]="50";

		//vedere qui
		cMP.checkDataL(info, "aggiorno libro","provo ad aggiornare", LocalDate.of(2023,1,5), infoCosti);
	}

	@Test
	void testModificaGiornale() throws SQLException {
		vis.setId(1);
		cambio[0]=rBModificaGiornale.getString("cambio[0]");
		cambio[1]=rBModificaGiornale.getString("cambio[1]");
		cambio[2]=rBModificaGiornale.getString("cambio[2]");
		cambio[3]=rBModificaGiornale.getString("cambio[3]");
		cMP.checkDataG(cambio,LocalDate.of(2022, 11,20), 0, (float)1.25, 10);

	}

	@Test
	void testModificaRivista() throws SQLException {
		cambioR[0]=rBModificaRivista.getString("cambioR[0]");
		cambioR[1]=rBModificaRivista.getString("cambioR[1]");
		cambioR[2]=rBModificaRivista.getString("cambioR[2]");
		cambioR[3]=rBModificaRivista.getString("cambioR[3]");
		cambioR[4]=rBModificaRivista.getString("cambioR[4]");
		cMP.checkDataR(cambioR, LocalDate.of(2023, 5,11), 0, (float)2.36, 100, 1, "rivista cambiata2");
	}

	@Test
	void testTotale() throws SQLException, IdException {
		l.setTitolo(rBSettaOggetto.getString("titoloL"));
		l.setNrCopie(Integer.parseInt(rBSettaOggetto.getString("copieL")));
		vis.setQuantita(3);
		vis.setId(Integer.parseInt(rBSettaOggetto.getString("idL")));
		vis.setTypeAsBook();
		l.setDisponibilita(Integer.parseInt(rBSettaOggetto.getString("disponibilita")));
		assertEquals(0,cA.totale1(vis.getType(),l.getTitolo(), l.getDisponibilita(), vis.getQuantita()));
	}

	@Test
	void testTotaleG() throws SQLException ,IdException {
		g.setTitolo(rBSettaOggetto.getString("titoloG"));
		vis.setId(3);
		g.setCopieRimanenti(Integer.parseInt(rBSettaOggetto.getString("copieRimanenti")));
		vis.setQuantita(5);
		vis.setTypeAsDaily();
		g.setDisponibilita(Integer.parseInt(rBSettaOggetto.getString("disponibilita")));
		assertNotEquals(0,cA.totale1(vis.getType(),g.getTitolo(),g.getDisponibilita(), vis.getQuantita()));
	}

	@Test
	void testTotaleR() throws SQLException, IdException {
		r.setTitolo(rBSettaOggetto.getString("titoloR"));
		vis.setId(Integer.parseInt(rBSettaOggetto.getString("idR")));
		r.setCopieRim(Integer.parseInt(rBSettaOggetto.getString("copieRimanenti")));
		vis.setQuantita(Integer.parseInt(rBSettaOggetto.getString("quantitaR")));
		vis.setTypeAsMagazine();
		r.setDisp(Integer.parseInt(rBSettaOggetto.getString("disponibilita")));
		assertNotEquals(0,cA.totale1(vis.getType(),r.getTitolo(),r.getDisp(),vis.getQuantita()));
	}

	@ParameterizedTest
	@ValueSource(ints= {1,2,3,4,5,6})
	void testScaricaLibro(int ints) throws DocumentException, IOException, URISyntaxException {
		vis.setId(ints);
		vis.setTypeAsBook();
		cD.scarica(vis.getType());
		assertEquals(ints,vis.getId());
	}

	@ParameterizedTest
	@ValueSource(ints= {1,2,3,4,5,6})
	void testScaricaGiornale(int ints) throws IOException, DocumentException, URISyntaxException {
		vis.setTypeAsDaily();
		vis.setId(ints);
		cD.scarica(vis.getType());
		assertEquals(ints,vis.getId());
	}
	@ParameterizedTest
	@ValueSource(ints= {1,2,3,4,5,6})
	void testScaricaRivista(int ints) throws DocumentException, IOException, URISyntaxException {
		vis.setTypeAsMagazine();
		vis.setId(ints);
		cD.scarica(vis.getType());
		assertEquals(ints,vis.getId());
	}

	@Test
	void testAnnullaOrdineL() throws SQLException, IdException {
		vis.setTypeAsBook();
		vis.setMetodoP("cash");

		f.setNome(rBFattura.getString("nome1"));
		f.setCognome(rBFattura.getString("cognome1"));
		f.setVia(rBFattura.getString("via"));
		f.setCom(rBFattura.getString("comunicazioni1"));
		f.setNumero(rBFattura.getString("numeroF1"));
		f.setAmmontare(Float.parseFloat(rBFattura.getString("ammontare1")));

		f1.setNome(rBFattura.getString("nome1"));
		f1.setCognome(rBFattura.getString("cognome1"));
		f1.setVia(rBFattura.getString("via"));
		f1.setCom(rBFattura.getString("comunicazioni2"));
		f1.setNumero(rBFattura.getString("numeroF2"));
		f1.setAmmontare(Float.parseFloat(rBFattura.getString("ammontare2")));
		cDao.inserisciFattura(f);
		cDao.inserisciFattura(f1);

		p.setId(Integer.parseInt(rBPagamentoEsito.getString("id1")));
		p.setMetodo(rBPagamentoEsito.getString("metodo"));
		p.setEsito(Integer.parseInt(rBPagamentoEsito.getString("esito")));
		p.setNomeUtente(rBPagamentoEsito.getString("nomeUtente"));
		p.setAmmontare(Float.parseFloat(rBPagamentoEsito.getString("spesa1")));
		p.setTipo(rBPagamentoEsito.getString("tipo"));

		p2.setId(Integer.parseInt(rBPagamentoEsito.getString("id2")));
		p2.setMetodo(rBPagamentoEsito.getString("metodo"));
		p2.setEsito(Integer.parseInt(rBPagamentoEsito.getString("esito")));
		p2.setNomeUtente(rBPagamentoEsito.getString("nomeUtente"));
		p2.setAmmontare(Float.parseFloat(rBPagamentoEsito.getString("spesa2")));
		p2.setTipo(rBPagamentoEsito.getString("tipo"));

		pD.inserisciPagamento(p);
		pD.inserisciPagamento(p2);

		cD.annullaOrdine();
		assertEquals("cash",vis.getMetodoP());
	}

	@ParameterizedTest
	@ValueSource(strings ={"libro","rivista","giornale"})
	void testGetRivista(String strings) throws SQLException {
		assertNotNull(cGP.getLista(strings));
	}

	@ParameterizedTest
	@ValueSource(strings= {"Zerocalcare","In cucina con ciccio"})
	void testCercaPerTipoL(String strings) throws SQLException {
		vis.setTypeAsBook();
		assertNotNull(cRicP.cercaPerTipo(strings));
	}
	@ParameterizedTest
	@ValueSource(strings= {"Il Fatto Quotidiano","La Republica"})
	void testCercaPerTipoG(String strings) throws SQLException {
		vis.setTypeAsDaily();
		assertNotNull(cRicP.cercaPerTipo(strings));
	}

	@ParameterizedTest
	@ValueSource(strings= {"Focus","Rivista A"})
	void testCercaPerTipoR(String strings) throws SQLException {
		vis.setTypeAsMagazine();
		assertNotNull(cRicP.cercaPerTipo(strings));
	}


	@ParameterizedTest
	@ValueSource(strings = {"libro","giornale","rivista"})
	void testGetLista(String strings) throws SQLException {
		assertNotNull(cC.getLista(strings));
	}
	@Test
	void testGetOggettById() throws SQLException {
		assertNotNull(cMP.getLibriById(1));
	}

	@Test
	void testGetGiornaliById() throws SQLException {
		assertNotNull(cMP.getGiornaliById(2));
	}



	@Test
	void testGetRivistaById() throws SQLException {
		assertNotNull(cMP.getRivistaById(5));
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
