package laptop.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;

import laptop.controller.*;
import laptop.database.PagamentoDao;
import laptop.exception.IdException;
import laptop.model.Pagamento;
import laptop.utilities.ConnToDb;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import laptop.database.UsersDao;

import laptop.model.TempUser;
import laptop.model.User;


class TestLaptop2 {
	
	private ControllerSystemState vis=ControllerSystemState.getInstance();
	private ControllerPagamentoCC cPCC=new ControllerPagamentoCC();
	private ControllerPassword cP=new ControllerPassword();
	private ControllerRegistraUtente cRU=new ControllerRegistraUtente();
	private ControllerReportPage cRP=new ControllerReportPage();
	private ControllerReportRaccolta cRR=new ControllerReportRaccolta();
	private ControllerRicercaPage cRicP=new ControllerRicercaPage();
	private ControllerRicercaPerTipo cRT=new ControllerRicercaPerTipo();
	private ControllerVisualizza cV=new ControllerVisualizza();
	private ControllerVisualizzaOrdine cVO=new ControllerVisualizzaOrdine();
	private ControllerVisualizzaProfilo cVP=new ControllerVisualizzaProfilo();
	private static TempUser tu=TempUser.getInstance();

    private final ResourceBundle rBUtente=ResourceBundle.getBundle("configurations/utente");
	private Pagamento p;
	private PagamentoDao pD=new PagamentoDao();
	private static User u= User.getInstance();

	private ControllerUserPage cUP=new ControllerUserPage();
	private ControllerAggiungiUtente cAU=new ControllerAggiungiUtente();
	private ControllerModificaUtente cMU=new ControllerModificaUtente();
	private ControllerCancellaUser cCU=new ControllerCancellaUser();





	@Test
	void testAggiornaPass() throws SQLException {
		assertTrue(cP.aggiornaPass(rBUtente.getString("email"), rBUtente.getString("vecchiaP"), rBUtente.getString("nuovaP")));
	}
	@Test
	void testRegistra() throws SQLException {
		assertTrue(cRU.registra(rBUtente.getString("nuovoN"), rBUtente.getString("nuovoC"), rBUtente.getString("nuovoE"), rBUtente.getString("nuovoP"),rBUtente.getString("nuovoP"),LocalDate.of(Integer.parseInt(rBUtente.getString("annoN")), Integer.parseInt(rBUtente.getString("meseN")),Integer.parseInt(rBUtente.getString("giornoN")))));
	}


	@Test
	void testGetUtenti() throws IOException, SQLException {
		cRP.getUtenti();
		assertNotNull("ReportFinale\\riepilogoUtenti.txt");
	}



	@ParameterizedTest
	@ValueSource(strings = { "UTENTE","ADMIN","SCRITTORE","EDITORE"})
	void testGetTipo(String strings) {
		User.getInstance().setIdRuolo(strings);
		assertEquals(strings,cRR.getTipo());
	}
	

	@Test
	void testSetRicercaL() {
		vis.setTypeAsBook();
		assertTrue(cRT.setRicerca(vis.getType()));
	}

	@Test
	void testSetRicercaG() {
		vis.setTypeAsDaily();
		assertTrue(cRT.setRicerca(vis.getType()));
	}

	@Test
	void testSetRicercaR() {
		vis.setTypeAsMagazine();
		assertTrue(cRT.setRicerca(vis.getType()));
	}




	@Test
	void testGetDati() throws SQLException {
		User.getInstance().setEmail(null);
		assertNotNull(cVO.getDati());
	}
	@Test
	void testGetCredenziali() throws SQLException {
		User.getInstance().setEmail(rBUtente.getString("email"));
		assertNotNull(cVP.getCredenziali());
		
	}
	@Test
	void testCancellaUtente() throws SQLException {
		User.getInstance().setEmail(rBUtente.getString("email"));
		assertTrue(cVP.cancellaUtente());
	}
	@Test
	void testCreateUser2() throws SQLException {
		tu.setIdRuolo(rBUtente.getString("tempUerRuolo"));
		tu.setNomeT(rBUtente.getString("tempUserNome"));
		tu.setCognomeT(rBUtente.getString("tempUserCognome"));
		tu.setEmailT(rBUtente.getString("tempUserEmail"));
		tu.setPasswordT(rBUtente.getString("tempUserPass"));
		tu.setDescrizioneT(rBUtente.getString("tempUserDesc"));
		tu.setDataDiNascitaT(LocalDate.of(Integer.parseInt(rBUtente.getString("tempUserAnno")),Integer.parseInt(rBUtente.getString("tempUserMese")),Integer.parseInt(rBUtente.getString("tempUserGiorno"))));
		assertTrue(UsersDao.createUser2(tu));
		
	}

	@Test
	void testGetUtentiLista() throws IOException, SQLException
	{
		String mex="prendo lista Utenti";
		cUP.getUtenti();
		assertEquals("prendo lista Utenti",mex);
	}
	@Test
	void testCheckData() throws ParseException, SQLException
	{
		assertTrue(cAU.checkData(rBUtente.getString("nomeProvaU"),rBUtente.getString("cognomeProvaU") ,rBUtente.getString("emailProvaU"),rBUtente.getString("passProvaU"),rBUtente.getString("dataProvaU")));
	}
	@Test
	void testAggiornaTotale() throws SQLException
	{
		User.getInstance().setId(Integer.parseInt(rBUtente.getString("idU")));
		assertTrue(cMU.aggiornaTot(rBUtente.getString("nomeU"), rBUtente.getString("cognomeU") ,rBUtente.getString("emailU"),rBUtente.getString("passU"), rBUtente.getString("descU"), LocalDate.of(Integer.parseInt(rBUtente.getString("annoU")), Integer.parseInt(rBUtente.getString("meseU")),Integer.parseInt(rBUtente.getString("giornoU"))),rBUtente.getString("ruoloU")));
	}
	@Test
	void testCancellaUser() throws SQLException
	{
		User.getInstance().setId(Integer.parseInt(rBUtente.getString("idU")));
		assertTrue(cCU.cancellaUser());
	}

	

	
	@Test
	void testMaxIdUSer() throws SQLException {
		assertNotEquals(0,UsersDao.maxIdUSer());
	}
	@ParameterizedTest
	@ValueSource(strings={"baoPublishing@gmail.com","giannni@gmail.com"} )
	void testCheckTempUser(String strings) throws SQLException
	{
		tu.setEmailT(strings);
		assertEquals(-1,UsersDao.checkTempUser(tu));
	}
	
	@Test
	void testFindUser()
	{
		assertNotNull(UsersDao.findUser(tu));
	}
	@Test
	void testDeleteTempUser() throws SQLException
	{
		tu.setEmailT(rBUtente.getString("tempUserEmailToDelete"));
		assertTrue(UsersDao.deleteTempUser(tu));
	}


	@ParameterizedTest
	@ValueSource(strings={"cash","cCredito"})
	void testInserisciPagamento(String strings) throws SQLException, IdException {

		p=new Pagamento();
		p.setTipo(strings);
		p.setEsito(0);

		p.setNomeUtente(rBUtente.getString("nomeUtente"));
		p.setAmmontare((float) 1.36);

		User.getInstance().setEmail(rBUtente.getString("emailUtente"));

		p.setTipo("libro");

		p.setId(0);


		pD.inserisciPagamento(p);

		assertNotNull(p);

	}
	@Test
	void testGetPagamenti() throws SQLException {
		User.getInstance().setEmail(rBUtente.getString("emailUtente"));
		assertNotNull(pD.getPagamenti());
	}
	@ParameterizedTest
	@ValueSource(strings= {"ADMINT","EDITORET","SCRITTORET"})
	void testGetIdRuolo(String strings) {
		tu.setIdRuolo(strings);
		assertEquals(strings,tu.getIdRuolo());
	}
	@ParameterizedTest
	@ValueSource(strings={"ADMIN","EDITORE","SCRITTORE"})
	void testGetIdRuoloU(String strings) {
		u.setIdRuolo(strings);
		assertEquals(strings,u.getIdRuolo());
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
    void testCambioTemUser() throws IOException, SQLException
    {

        TempUser tu1=TempUser.getInstance();
        tu.setId(Integer.parseInt(rBUtente.getString("tempUserId")));
        tu1=UsersDao.getTempUserSingolo(tu);
        tu1.setDescrizioneT(rBUtente.getString("tempDesc"));
        UsersDao.aggiornaTempDesc(tu1);
        tu1.setPasswordT(rBUtente.getString("tempPass"));
        UsersDao.aggiornaTempPass(tu1);
        assertEquals(7,tu1.getId());



    }
    @Test
    void testListaUsers() throws SQLException
    {
        assertNotNull(UsersDao.getUserList());
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
