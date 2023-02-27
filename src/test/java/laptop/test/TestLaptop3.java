package laptop.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.logging.Level;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.itextpdf.text.DocumentException;

import laptop.controller.ControllerAggiungiUtente;
import laptop.controller.ControllerCancellaUser;
import laptop.controller.ControllerModificaUtente;
import laptop.controller.ControllerUserPage;
import laptop.database.PagamentoDao;
import laptop.database.RivistaDao;
import laptop.model.Pagamento;
import laptop.model.TempUser;
import laptop.model.User;
import laptop.model.raccolta.Rivista;
import laptop.utilities.ConnToDb;

class TestLaptop3 {
	private PagamentoDao pD=new PagamentoDao();
	private Pagamento p;
	private Rivista r=new Rivista();
	private RivistaDao rD=new RivistaDao();
	private TempUser tu=TempUser.getInstance();
	private User u=User.getInstance();
	private ControllerUserPage cUP=new ControllerUserPage();
	private ControllerAggiungiUtente cAU=new ControllerAggiungiUtente();
	private ControllerModificaUtente cMU=new ControllerModificaUtente();
	private ControllerCancellaUser cCU=new ControllerCancellaUser();


	@ParameterizedTest
	@ValueSource(strings={"cash","cCredito"})
	void testInserisciPagamento(String strings) throws SQLException {
		
		p=new Pagamento();
		p.setTipo(strings);
		p.setEsito(0);
		
		p.setNomeUtente("franco");
		p.setAmmontare((float) 1.36);
		
		User.getInstance().setEmail("franco.rossi@gmail.com");
		
		p.setTipo("libro");
		
		p.setId(0);
		
		
		pD.inserisciPagamento(p);
		
		assertNotNull(p);
		
	}

	@Test
	void testGetPagamenti() throws SQLException {
		User.getInstance().setEmail("franco.rossi@gmail.com");
		assertNotNull(pD.getPagamenti());
	}

	@Test
	void testRetUltimoOrdine() throws SQLException {
		assertNotEquals(0,pD.retUltimoOrdinePag());
	}

	@Test
	void testAnnullaOrdine() throws SQLException {
		int id=pD.retUltimoOrdinePag();
		assertTrue(pD.annullaOrdinePag(id));
		
	}
	@ParameterizedTest
	@ValueSource(strings={"Focus","Tv Sorrisi e canzoni","Rivista B"})
	void testGetTitolo(String strings) {
		r.setTitolo(strings);
		assertEquals(strings,r.getTitolo());
	}

	@ParameterizedTest
	@ValueSource(strings= {"SETTIMANALE","BISETTIMANALE","MENSILE",
	"BIMESTRALE","TRIMESTRALE","ANNUALE","ESTIVO","INVERNALE",
	"SPORTIVO","CINEMATOGRAFICA","GOSSIP","TELEVISIVO","MILITARE","INFORMATICA"})
	void testGetTipologia(String strings) {
		r.setTipologia(strings);
		assertEquals(strings,r.getTipologia());
	}
	@ParameterizedTest
	@ValueSource(strings = {"Bao Publishing","Panorama","Focus"})
	void testGetAutore(String strings) {
		r.setAutore(strings);
		assertEquals(strings,r.getAutore());
	}

	@Test
	void testGetLingua() {
		r.setLingua("ita");
		assertEquals("ita",r.getLingua());
	}

	@Test
	void testGetEditore() throws FileNotFoundException, ClassNotFoundException, SQLException {
		r.setEditore("prova");
		assertEquals("prova",r.getEditore());
	}

	@Test
	void testGetDescrizione() {
		r.setDescrizione("prova");
		assertEquals("prova",r.getDescrizione());
	}

	@Test
	void testGetDataPubb() {
		r.setDataPubb(LocalDate.now());
		assertNotNull(r.getDataPubb());
	}

	@Test
	void testGetDisp() {
		r.setDisp(0);
		assertNotEquals(1,r.getDisp());
	}

	@Test
	void testGetPrezzo() {
		r.setPrezzo((float)1.68);
		assertNotEquals(0,r.getPrezzo());
	}

	@Test
	void testGetCopieRim() {
		r.setCopieRim(0);
		assertEquals(0,r.getCopieRim());
	}

	@ParameterizedTest
	@ValueSource(ints = {1,2,3,4,5})
	void testGetId(int ints) {
		r.setId(ints);
		assertEquals(ints,r.getId());
	}
	@ParameterizedTest
	@ValueSource(ints = {1,2,3,4,5})
	void testScarica(int ints) throws DocumentException, IOException
	{
		r.setId(ints);
		r.scarica();
		assertEquals(ints,r.getId());
	}
	@ParameterizedTest
	@ValueSource(ints = {1,2,3,4,5})
	void testLeggi(int ints) throws IOException, DocumentException
	{
		r.setId(ints);
		r.leggi(ints);
		assertEquals(ints,r.getId());
	}
	@Test
	void testGetDesc() throws SQLException {
		r.setTitolo("Rivista A");
		rD.getDesc(r);
		assertNotNull(r.getTitolo());
	}
	@Test
	void testRetId() throws SQLException
	{
		r.setTitolo("Rivista A");
		assertEquals(4,rD.retId(r));
	}
	@Test
	void testCheckDisp() throws SQLException
	{
		r.setId(4);
		assertTrue(rD.checkDisp(r));
	}
	@Test
	void testRivistaSinngoloById() throws SQLException
	{
		r.setId(4);
		assertNotNull(rD.getRivistaSingoloById(r));
	}
	@Test
	void testGetInstance() {
		
		assertNotNull(tu);
	}

	@Test
	void testGetId() {
		tu.setId(3);
		assertNotEquals(0,tu.getId());
	}

	@Test
	void testGetNome() {
		tu.setNomeT("tempUserN");
		assertNotNull(tu.getNomeT());
	}

	@Test
	void testGetCognome() {
		tu.setCognomeT("tempUserC");
		assertNotNull(tu.getCognomeT());
	}

	@Test
	void testGetEmail() {
		tu.setEmailT("tempUserE@gmail.com");
		assertNotNull(tu.getEmailT());
	}

	@Test
	void testGetPassword() {
		tu.setPasswordT("tempUser12");
		assertNotNull(tu.getPasswordT());
	}

	@Test
	void testGetDescrizioneTU() {
		assertNotEquals("",tu.getDescrizioneT());
	}

	@Test
	void testGetDataDiNascita() {
		tu.setDataDiNascitaT(LocalDate.now());
		assertNotNull(tu.getDataDiNascitaT());
	}
	@ParameterizedTest
	@ValueSource(strings= {"ADMINT","EDITORET","SCRITTORET"})
	void testGetIdRuolo(String strings) {
		tu.setIdRuolo(strings);
		assertEquals(strings,tu.getIdRuolo());
	}
	
	@Test
	void testGetIdRuoloF() {
		tu.setIdRuolo("FT");
		assertEquals("UTENTET",tu.getIdRuolo());
		}
	@Test
	void testGetInstanceU() {
		assertNotNull(u);
	}

	@Test
	void testGetIdU() {
		u.setId(8);
		assertNotEquals(0,u.getId());
	}

	@Test
	void testGetNomeU() {
		u.setNome("userN8");
		assertNotNull(u.getNome());
	}

	@Test
	void testGetCognomeU() {
		u.setCognome("userC8");
		assertNotNull(u.getCognome());
	}

	@Test
	void testGetEmailU() {
		u.setEmail("userEmail@gmail.com");
		assertNotNull(u.getEmail());
	}

	@Test
	void testGetPasswordU() {
		u.setPassword("user152");
		assertNotNull(u.getPassword());
	}

	

	@Test
	void testGetDataDiNascitaU() {
		u.setDataDiNascita(LocalDate.now());
		assertNotNull(u.getDataDiNascita());
	}

	@ParameterizedTest
	@ValueSource(strings={"ADMIN","EDITORE","SCRITTORE"})
	void testGetIdRuoloU(String strings) {
		u.setIdRuolo(strings);
		assertEquals(strings,u.getIdRuolo());
	}
	
	@Test
	void testGetIdRuoloFU() {
		u.setIdRuolo("F");
		assertEquals("UTENTE",u.getIdRuolo());
		}
	@Test
	void testGetUtenti() throws IOException, SQLException
	{
		String mex="prendo lista Utenti";
		cUP.getUtenti();
		assertEquals("prendo lista Utenti",mex);
	}
	@Test
	void testCheckData() throws ParseException, SQLException
	{
		assertTrue(cAU.checkData("nome prova","cognome prova" ,"emailProva@gmail.com","prova452","1985/08/11"));
	}
	@Test
	void testAggiornaTotale() throws SQLException
	{
		User.getInstance().setId(5);
		User.getInstance().setDescrizione("utente aggiornato");
		assertTrue(cMU.aggiornaTot("nomeU","cognomeU" ,"nomeCognomeU@gmail.com","nome52", User.getInstance().getDescrizione(), LocalDate.of(1963, 8,11),"EDITORE"));
	}
	@Test
	void testCancellaUser() throws SQLException
	{
		User.getInstance().setId(5);
		assertTrue(cCU.cancellaUser());
	}

	@AfterAll
	static void  testCancellaDB() throws SQLException
	{
		int rows=0;
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement("drop schema ispw"))
		{
			rows=prepQ.executeUpdate();		}
		
		java.util.logging.Logger.getLogger("cancella db").log(Level.INFO, "database cancellato ");

		assertEquals(rows,11);

	}

}
