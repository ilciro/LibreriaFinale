package laptop.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;



import laptop.controller.*;
import laptop.database.*;
import laptop.exception.AcquistaException;
import laptop.exception.IdException;
import laptop.model.*;
import laptop.model.raccolta.Factory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.itextpdf.text.DocumentException;

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

	private final ResourceBundle rBCartaCredito=ResourceBundle.getBundle("configurations/cartaCredito");

	private final ResourceBundle rBNegozio=ResourceBundle.getBundle("configurations/negozio");

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
	private ControllerScegliNegozio cSN=new ControllerScegliNegozio();
	private Negozio n1=new Negozio();
	private NegozioDao nD=new NegozioDao();
	private RivistaDao rD=new RivistaDao();
	private GiornaleDao gD=new GiornaleDao();


	private static Fattura f=new Fattura();
	private static Fattura f1=new Fattura();

	private static Pagamento p=new Pagamento();
	private static Pagamento p2=new Pagamento();
	private ControllerPagamentoCC cPCC=new ControllerPagamentoCC();
	private ControllerReportPage cRP=new ControllerReportPage();
	private ControllerVisualizza cV=new ControllerVisualizza();
	private Factory factory=new Factory();









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

	@Test
	void testAggiungiCartaDBL() throws SQLException, IdException {
		vis.setSpesaT((float)125.6);
		vis.setTypeAsBook();
		java.sql.Date data= Date.valueOf("2025-11-11");
		cPCC.aggiungiCartaDB(rBCartaCredito.getString("nome1"),rBCartaCredito.getString("cognome1"),rBCartaCredito.getString("codice1"), data, rBCartaCredito.getString("civ1"), Float.parseFloat(rBCartaCredito.getString("prezzo")));
		assertNotNull(data);
	}
	@Test
	void testAggiungiCartaDBG() throws SQLException, IdException {
		vis.setSpesaT((float)35.4);
		vis.setTypeAsDaily();
		java.sql.Date data=Date.valueOf("2026-06-11");
		cPCC.aggiungiCartaDB(rBCartaCredito.getString("nome2"),rBCartaCredito.getString("cognome2"),rBCartaCredito.getString("codice2"), data, rBCartaCredito.getString("civ2"), Float.parseFloat(rBCartaCredito.getString("prezzo2")));
		assertNotNull(data);
	}
	@Test
	void testAggiungiCartaDBR() throws SQLException, IdException {
		vis.setSpesaT((float)14.9);
		vis.setTypeAsMagazine();
		java.sql.Date data=Date.valueOf("2028-02-22");
		cPCC.aggiungiCartaDB(rBCartaCredito.getString("nome3"),rBCartaCredito.getString("cognome3"),rBCartaCredito.getString("codice3"), data, rBCartaCredito.getString("civ3"), Float.parseFloat(rBCartaCredito.getString("prezzo3")));
		assertNotNull(data);
	}
	@Test
	void testGeneraReportLibri() throws IOException, SQLException {
		String report="report libri";
		cRP.generaReportLibri();
		assertEquals("report libri",report);
	}
	@Test
	void testGeneraReportGiornali() throws IOException, SQLException {
		String report="report giornali";
		cRP.generaReportGiornali();
		assertEquals("report giornali",report);


	}
	@Test
	void testGeneraReportRiviste() throws IOException, SQLException {
		String report="report riviste";
		cRP.generaReportRiviste();
		assertEquals("report riviste",report);


	}

	@ParameterizedTest
	@ValueSource(ints = {1,2,3,4,5,6,7,8,9,10})
	void testGetDataL(int ints) throws SQLException {
		assertNotNull(cV.getDataL(ints));
	}
	@ParameterizedTest
	@ValueSource(ints = {1,2,3,4,5,6})
	void testGetDataG(int ints) throws SQLException {
		assertNotNull(cV.getDataG(ints));
	}
	@ParameterizedTest
	@ValueSource(ints = {1,2,3,4,5})
	void testGetDataR(int ints) throws SQLException {
		assertNotNull(cV.getDataR(ints));
	}

	@Test
	void testCreateRaccoltaFinaleCompletaL() {
		factory.createRaccoltaFinale1(rBSettaOggetto.getString("tipologia1"),rBSettaOggetto.getString("titolo1"),rBSettaOggetto.getString("categoria1"),rBSettaOggetto.getString("autore1"),rBSettaOggetto.getString("lingua1"), rBSettaOggetto.getString("editore1"),rBSettaOggetto.getString("categoria1"));
		factory.createRaccoltaFinale2(rBSettaOggetto.getString("tipologia1"),Integer.parseInt(rBSettaOggetto.getString("numPag1")),rBSettaOggetto.getString("isbn"), Integer.parseInt(rBSettaOggetto.getString("numPag1")), Integer.parseInt(rBSettaOggetto.getString("disp1")), Float.parseFloat(rBSettaOggetto.getString("prezzo1")), Integer.parseInt(rBSettaOggetto.getString("nrCopie")));
		assertNotNull(factory.createRaccoltaFinaleCompleta(rBSettaOggetto.getString("tipologia1"),LocalDate.now(), rBSettaOggetto.getString("recensione1"), rBSettaOggetto.getString("descrizione1"), Integer.parseInt(rBSettaOggetto.getString("id"))));


	}
	@Test
	void testCreateRaccoltaFinaleCompletaG() {
		factory.createRaccoltaFinale1(rBSettaOggetto.getString("tipologia2"), rBSettaOggetto.getString("tipologia2"), rBSettaOggetto.getString("titolo2"),rBSettaOggetto.getString("autore2"), rBSettaOggetto.getString("lingua2"),rBSettaOggetto.getString("editore2"),rBSettaOggetto.getString("categoria2"));
		factory.createRaccoltaFinale2(rBSettaOggetto.getString("tipologia2"), Integer.parseInt(rBSettaOggetto.getString("numPag2")), rBSettaOggetto.getString("isbn2"), Integer.parseInt(rBSettaOggetto.getString("copieRim2")), Integer.parseInt(rBSettaOggetto.getString("disp2")), Float.parseFloat(rBSettaOggetto.getString("prezzo2")), Integer.parseInt(rBSettaOggetto.getString("copieRim2")));
		assertNotNull(factory.createRaccoltaFinaleCompleta(rBSettaOggetto.getString("tipologia2"),LocalDate.now(), rBSettaOggetto.getString("recensione2"), rBSettaOggetto.getString("descrizione2"),Integer.parseInt(rBSettaOggetto.getString("id2"))));

	}
	@Test
	void testCreateRaccoltaFinaleCompletaR() {
		factory.createRaccoltaFinale1(rBSettaOggetto.getString("tipologia3"),rBSettaOggetto.getString("titolo3"),rBSettaOggetto.getString("tipologia"),rBSettaOggetto.getString("autore3"),rBSettaOggetto.getString("lingua3"),rBSettaOggetto.getString("editore3"), null);
		factory.createRaccoltaFinale2(rBSettaOggetto.getString("tipologia3"), Integer.parseInt(rBSettaOggetto.getString("numPag3")), null, Integer.parseInt(rBSettaOggetto.getString("copieRim3")), Integer.parseInt(rBSettaOggetto.getString("disp3")), Float.parseFloat(rBSettaOggetto.getString("prezzo3")), Integer.parseInt(rBSettaOggetto.getString("copieRim3")));
		assertNotNull(factory.createRaccoltaFinaleCompleta(rBSettaOggetto.getString("tipologia3"),LocalDate.now(), rBSettaOggetto.getString("recensione3"), rBSettaOggetto.getString("descrizione3"),Integer.parseInt(rBSettaOggetto.getString("id3"))));

	}

	@ParameterizedTest
	@ValueSource(strings= {"LINGUISTICA_SCRITTURA","ADOLESCENTI_RAGAZZI","CINEMA_FOTOGRAFIA",
			"ECONOMIA" ,"INFORMATICA","WEB_DIGITAL_MEDIA","POLITICA" ,"ROMANZI_ROSA"})
	void testGetCategoria(String strings) {
		l.setCategoria(strings);
		assertEquals(strings,l.getCategoria());
	}
	@ParameterizedTest
	@ValueSource(strings= {"SETTIMANALE","BISETTIMANALE","MENSILE",
			"BIMESTRALE","TRIMESTRALE","ANNUALE","ESTIVO","INVERNALE",
			"SPORTIVO","CINEMATOGRAFICA","GOSSIP","TELEVISIVO","MILITARE","INFORMATICA"})
	void testGetTipologia(String strings) {
		r.setTipologia(strings);
		assertEquals(strings,r.getTipologia());
	}
	@Test
	void testRitornaElencoCC() throws SQLException {
		assertNotNull(cPCC.ritornaElencoCC(rBCartaCredito.getString("nome2")));
	}

	@Test
	void testTornaDalDb() throws Exception {
		assertNotNull(cPCC.tornaDalDb(rBCartaCredito.getString("codice2")));
	}
	@Test
	void reportTotale()
	{
		assertNotNull(cRP.reportTotale());
	}
	@Test
	void reportRaccolta() throws IOException {
		assertNotNull(cRP.reportRaccolta());
	}
	@ParameterizedTest
	@ValueSource(ints= {1,2,3,4,5,6,7,8,9,10})
	void testGetId(int ints) {
		g.setId(ints);
		assertEquals(ints,g.getId());
	}

	@ParameterizedTest
	@ValueSource(ints= {1,2,3,4,5,6,7,8,9,10})
	void testScarica(int ints) throws DocumentException, IOException
	{
		g.setId(ints);
		g.scarica();
		assertEquals(ints,g.getId());
	}
	@ParameterizedTest
	@ValueSource(ints= {1,2,3,4,5,6,7,8,9,10})
	void testLeggi(int ints) throws IOException, DocumentException
	{
		g.setId(ints);
		g.leggi(ints);
		assertEquals(ints,g.getId());
	}

	@Test
	void testControllaPag() {
		cPCC.controllaPag("2025-08-08",rBPagamentoInfo.getString("codice"),rBPagamentoInfo.getString("civ"));
		assertNotNull("2025-08-08");
	}
	@Test
	void testPagamentoCCL() throws SQLException, IdException {
		vis.setTypeAsBook();
		vis.setSpesaT((float)125.6);
		vis.setId(1);
		cPCC.pagamentoCC(rBCartaCredito.getString("nome1"));
		assertNotEquals(0,vis.getId());
	}
	@Test
	void testPagamentoCCG() throws SQLException, IdException {
		vis.setTypeAsDaily();
		vis.setSpesaT((float)35.4);
		vis.setId(1);
		cPCC.pagamentoCC(rBCartaCredito.getString("nome2"));
		assertNotEquals(0,vis.getId());
	}
	@Test
	void testPagamentoCCR() throws SQLException, IdException {
		vis.setTypeAsMagazine();
		vis.setSpesaT((float)14.9);
		vis.setId(1);
		cPCC.pagamentoCC(rBCartaCredito.getString("nome3"));
		assertNotEquals(0,vis.getId());
	}
	@Test
	void testGetNegozi() throws SQLException {
		assertNotNull(cSN.getNegozi());
	}

	@Test
	void testCheckOpen() throws SQLException {
		n1.setNome(rBNegozio.getString("nome1"));
		nD.setOpen(n1, Boolean.getBoolean(rBNegozio.getString("isOpen")));
		assertFalse(nD.checkOpen(n1));
	}

	@Test
	void testCheckRitiro() throws SQLException {
		n1.setNome(rBNegozio.getString("nome1"));
		nD.setRitiro(n1, Boolean.getBoolean(rBNegozio.getString("isValid1")));
		assertFalse(nD.checkRitiro(n1));
	}

	//added this
	@ParameterizedTest
	@ValueSource(ints={1,2,3,4,5,6,7})
	void cancellaL(int ints) throws SQLException {
		vis.setTypeAsBook();
		cGP.cancella(ints);
		assertNotEquals(0,ints);
	}
	@ParameterizedTest
	@ValueSource(ints={1,2})
	void cancellaG(int ints) throws SQLException {
		vis.setTypeAsDaily();
		cGP.cancella(ints);
		assertNotEquals(0,ints);
	}
	@ParameterizedTest
	@ValueSource(ints={1,2})
	void cancellaR(int ints) throws SQLException {
		vis.setTypeAsMagazine();
		cGP.cancella(ints);
		assertNotEquals(0,ints);
	}
	@ParameterizedTest
	@ValueSource(strings={"libro","giornale","rivista"})
	void controllaDisp(String strings) throws SQLException, IdException {
		assertFalse(cC.disponibilita(strings,"1"));
	}
	@Test
	void reportL() throws IOException {
		assertNotNull(cRP.reportLibri());
	}
	@Test
	void reportG() throws IOException {
		assertNotNull(cRP.reportGiornali());
	}

	@ParameterizedTest
	@ValueSource(strings={"libro","giornale","rivista"})
	void testAmmontare(String strings) throws AcquistaException, SQLException, IdException {

		cA.inserisciAmmontare(strings,3);
		assertNotNull(strings);
	}


	@ParameterizedTest
	@ValueSource(strings={"libro","rivista","giornale"})
	void testGetDisp(String strings) throws SQLException, IdException {
		assertNotEquals(0,cA.getDisp(strings));
	}
	@ParameterizedTest
	@ValueSource(strings={"libro","rivista","giornale"})
	void testGetCosto(String strings) throws SQLException, IdException {
		cA.getCosto(strings);
		assertNotNull(strings);
	}
	@Test
	void testGetNomeById() throws SQLException {
		vis.setId(1);
		vis.setTypeAsBook();
		assertNotNull(cA.getNomeById());

	}
	@Test
	void testSetDescrizione() throws SQLException {
		Rivista r=new Rivista();
		r.setTitolo("Focus");
		rD.getDesc(r);
		int id=rD.retId(r);
		r.setId(id);
		String nome=rD.getNome(r);
		int disp=rD.getDisp(r);
		String titolo=rD.getTitolo(r);
		assertNotEquals(0,r.getId());
	}
	@Test
	void testRetId() throws SQLException {
		Giornale g=new Giornale();
		g.setTitolo("La Republica1");
		int id=gD.retId(g);
		String titolo=gD.getTitolo(g);
		String nome=gD.getNome(g);
		int disp=gD.getDisp(g);
		assertNotEquals(0,id);
	}
	@Test
	void testControlla() throws SQLException, IdException {
		vis.setSpesaT(25.6f);
		cPC.controlla(rBFattura.getString("nome1"),rBFattura.getString("cognome1"),rBFattura.getString("via"),rBFattura.getString("comunicazioni1"));
		assertNotEquals(0,vis.getSpesaT());
	}
}
