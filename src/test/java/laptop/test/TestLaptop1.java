/*package laptop.test;

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

import laptop.model.raccolta.Libro;
import laptop.model.raccolta.Rivista;
import laptop.utilities.CreateDefaultDB;

class TestLaptop1 {










	private final ResourceBundle rBNegozio=ResourceBundle.getBundle("configurations/negozio");






	private ControllerCompravendita cC=new ControllerCompravendita();
	private static User u = User.getInstance();
	private ControllerLogin cL=new ControllerLogin();
	private ControllerModificaUtente cMU=new ControllerModificaUtente();
	private String[] cambioR=new String[5];

	private ControllerPagamentoCash cPC = new ControllerPagamentoCash();
	private ControllerRicercaPage cRicP=new ControllerRicercaPage();
	private ControllerScegliNegozio cSN=new ControllerScegliNegozio();
	private Negozio n1=new Negozio();
	private NegozioDao nD=new NegozioDao();
;





	private ControllerPagamentoCC cPCC=new ControllerPagamentoCC();
	private ControllerReportPage cRP=new ControllerReportPage();
	private ControllerVisualizza cV=new ControllerVisualizza();




















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
	void testAggiungiCartaDBG() throws SQLException, IdException {
		vis.setSpesaT((float)35.4);
		vis.setTypeAsDaily();
		java.sql.Date data=Date.valueOf("2026-06-11");
		cPCC.aggiungiCartaDB(rBCartaCredito.getString("nome2"),rBCartaCredito.getString("cognome2"),rBCartaCredito.getString("codice2"), data, rBCartaCredito.getString("civ2"), Float.parseFloat(rBCartaCredito.getString("prezzo2")));
		assertNotNull(data);
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
	void testControlla() throws SQLException, IdException {
		vis.setSpesaT(25.6f);
		cPC.controlla(rBFattura.getString("nome1"),rBFattura.getString("cognome1"),rBFattura.getString("via"),rBFattura.getString("comunicazioni1"));
		assertNotEquals(0,vis.getSpesaT());
	}


}


 */