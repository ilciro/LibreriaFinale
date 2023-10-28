package laptop.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;


import laptop.exception.IdException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.itextpdf.text.DocumentException;

import laptop.controller.ControllerAcquista;
import laptop.controller.ControllerAggiungiPage;
import laptop.controller.ControllerCompravendita;
import laptop.controller.ControllerDownload;
import laptop.controller.ControllerGestionePage;
import laptop.controller.ControllerHomePageAfterLogin;
import laptop.controller.ControllerHomePageAfterLoginSE;
import laptop.controller.ControllerLogin;
import laptop.controller.ControllerModifPage;
import laptop.controller.ControllerModificaUtente;
import laptop.controller.ControllerPagamentoCash;
import laptop.controller.ControllerSystemState;
import laptop.database.ContrassegnoDao;
import laptop.database.PagamentoDao;
import laptop.exception.LogoutException;
import laptop.model.CartaDiCredito;
import laptop.model.Fattura;
import laptop.model.Pagamento;
import laptop.model.User;
import laptop.model.raccolta.Giornale;
import laptop.model.raccolta.Libro;
import laptop.model.raccolta.Rivista;
import laptop.utilities.CreateDefaultDB;

class TestLaptop1 {

	
	
	private java.sql.Date dataS= new java.sql.Date(Calendar.getInstance().getTime().getTime());
	private CartaDiCredito cc1=new CartaDiCredito(3,"8541-8596-5552-9858",(double)3000.0,(double)200,dataS,"pippo",(float)156.3);
	private CartaDiCredito cc2=new CartaDiCredito("pippo","pluto","1452-9958-7485-2222",new Date(),"852",(float)125.36);
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
	


	

	@BeforeAll
	static void creaDB() throws FileNotFoundException, ClassNotFoundException, SQLException
	{
		CreateDefaultDB.createDefaultDB();
	}
	
	@Test
	void testGetTipo() {
		assertEquals(3,cc1.getTipo());
		
	}

	@Test
	void testGetNumeroCC() {
		assertNotNull(cc2.getNumeroCC());
	}

	@Test
	void testGetLimite() {
		assertEquals((float)3000.0 , cc1.getLimite());
	}

	@Test
	void testGetAmmontare() {
		assertNotEquals(0,cc2.getAmmontare());
	}

	@Test
	void testGetScadenza() {
		assertNotNull(cc1.getScadenza());
	}

	@Test
	void testGetNomeUser() {
		assertEquals("pippo",cc2.getNomeUser());
	}

	@Test
	void testGetPrezzoTransazine() {
		assertNotEquals(0,cc1.getPrezzoTransazine());
	}

	@Test
	void testGetCognomeUser() {
		assertNotNull(cc2.getCognomeUser());
	}

	@Test
	void testGetCiv() {
		assertNotNull(cc2.getCiv());
	}
	
	@Test
	void testAnnullaOrdine() throws SQLException {
		assertFalse(cDao.annullaOrdineF(2));
	}
	
	@Test
	void testTotale() throws SQLException, IdException {
		l.setTitolo("Apocalypse town: Cronache dalla fine della civilta' urbana");
		l.setNrCopie(200);
		vis.setQuantita(5);
		vis.setId(1);
		vis.setTypeAsBook();
		l.setDisponibilita(1);
		assertEquals(0,cA.totale1(vis.getType(),l.getTitolo(), l.getDisponibilita(), vis.getQuantita()));
	}

	@Test
	void testTotaleG() throws SQLException ,IdException {
		g.setTitolo("La Republica1");
		vis.setId(3);
		g.setCopieRimanenti(50);
		vis.setQuantita(3);
		vis.setTypeAsDaily();
		g.setDisponibilita(1);
		assertNotEquals(0,cA.totale1(vis.getType(),g.getTitolo(),g.getDisponibilita(), vis.getQuantita()));
	}

	@Test
	void testTotaleR() throws SQLException, IdException {
		r.setTitolo("Rivista B");
		vis.setId(5);
		r.setCopieRim(30);
		vis.setQuantita(2);
		vis.setTypeAsMagazine();
		r.setDisp(1);
		assertNotEquals(0,cA.totale1(vis.getType(),r.getTitolo(),r.getDisp(),vis.getQuantita()));
	}
	
	@Test
	void testGetNomeById() throws SQLException
	{
		
		vis.setTypeAsBook();
		vis.setId(2);
		assertNotNull(cA.getNomeById());
	}
	@Test
	void testGetNomeByIdG() throws SQLException
	{
		vis.setTypeAsDaily();
		vis.setId(2);
		assertNotNull(cA.getNomeById());
	}
	@Test
	void testGetNomeByIdR() throws SQLException
	{
		vis.setTypeAsMagazine();
		vis.setId(2);
		assertNotNull(cA.getNomeById());
	}
	@Test 
	void testGetDispL() throws SQLException, IdException {
		vis.setTypeAsBook();
		vis.setId(1);
		
		assertNotEquals(0,cA.getDisp(vis.getType()));
	}
	@Test 
	void testGetDispG() throws SQLException, IdException {
		vis.setTypeAsDaily();
		vis.setId(1);
		assertEquals(0,cA.getDisp(vis.getType()));
	}
	@Test 
	void testGetDispR() throws SQLException, IdException {
		vis.setTypeAsMagazine();
		vis.setId(1);
		assertNotEquals(0,cA.getDisp(vis.getType()));
	}
	@Test 
	void testGetCostoL() throws SQLException
	{
		vis.setTypeAsBook();
		l.setId(1);
		assertNotEquals(0,cA.getCosto());
	}
	@Test 
	void testGetCostoG() throws SQLException
	{
		vis.setTypeAsDaily();
		g.setId(1);
		vis.setId(g.getId());
		assertNotEquals(0,cA.getCosto());
	}
	@Test 
	void testGetCostoR() throws SQLException
	{
		vis.setTypeAsMagazine();
		r.setId(1);
		assertNotEquals(0,cA.getCosto());
	}
	@Test
	void testCheckData() throws SQLException {
		info[0]="titolo ins prova";
		info[1]="ARTE";
		info[2]="autore ins prova";
		info[3]="ita";
		info[4]="editore ins prova";
		info[5]="ARTE";
		info[6]="provo ad inserire un libro";
		infoGen[0]=String.valueOf(100);
		infoGen[1]="15263452";
		infoGen[2]=String.valueOf(84);
		infoGen[3]=String.valueOf(1);
		infoGen[4]=String.valueOf((float)2.25);
		infoGen[5]=String.valueOf(100);
		
		l.setDataPubb(LocalDate.now());
		l.setRecensione("provo ad inserire un libro");
		l.setDesc("libro da inserire");
		
		
		assertTrue(cAP.checkData(info, l.getRecensione(), l.getDesc(), l.getDataPubb(), infoGen));
		
	}

	@Test
	void testCheckDataG() throws SQLException {
		g.setTitolo("giornale da inserire");
		g.setTipologia("quotidiano");
		g.setLingua("ita");
		g.setEditore("editore ins prova");
		g.setDataPubb(LocalDate.of(2022, 12,12));
		g.setCopieRimanenti(100);
		g.setDisponibilita(0);
		g.setPrezzo((float)2.45);
		assertTrue(cAP.checkDataG(g));
	}

	@Test
	void testCheckDataR() throws SQLException {
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
		assertTrue(cAP.checkDataR(info, r.getDataPubb(), r.getDisp(),r.getPrezzo() , r.getCopieRim(), r.getDescrizione()));
	
	}

	@Test
	void testDisponibilitaLibro() throws SQLException, IdException {
		vis.setTypeAsBook();
		assertTrue(cC.disponibilita(vis.getType(), "1"));
	}

	@Test
	void testDisponibilitaGiornale() throws SQLException, IdException {
		vis.setTypeAsDaily();
		assertFalse(cC.disponibilita(vis.getType(),"1"));
	}

	@Test
	void testDisponibilitaRivista() throws SQLException, IdException {
		vis.setTypeAsMagazine();
		assertTrue(cC.disponibilita(vis.getType(),"1"));
	}

	@Test
	void testGetLibri() throws SQLException {
		assertNotNull(cC.getLista("libro"));
	}

	@Test
	void testGetGiornali() throws SQLException {
		
		assertNotNull(cC.getLista("giornale"));
	}

	@Test
	void testGetRiviste() throws SQLException {
		assertNotNull(cC.getLista("rivista"));
	}

	@ParameterizedTest
	@ValueSource(strings= {"ADMIN","EDITORE","SCRITTORE"})
	void testRetTipoUser(String strings) {
		User.getInstance().setIdRuolo(strings);
		assertEquals(strings,User.getInstance().getIdRuolo());
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
	void testAnnullaOrdineL() throws SQLException
	{
		vis.setTypeAsBook();
		vis.setMetodoP("cash");
		Fattura f=new Fattura("pippo","pluto","via paperopoli 12","","1",(float)16.3);
		Fattura f1=new Fattura("pippo","pluto","via paperopoli 12","","2",(float)18.5);
		
		cDao.inserisciFattura(f);
		cDao.inserisciFattura(f1);

		Pagamento p=new Pagamento(1, "cash", 0, "pippo", (float)16.3, "cash");
		Pagamento p1=new Pagamento(2, "cash", 0, "pippo", (float)18.5, "cash");


		pD.inserisciPagamento(p);
		pD.inserisciPagamento(p1);
		
		cD.annullaOrdine();
		assertEquals("cash",vis.getMetodoP());
	}
	@Test
	void testAnnullaOrdineG() throws SQLException
	{
		vis.setTypeAsDaily();
		vis.setMetodoP("cash");
		Fattura f=new Fattura("pippo","pluto","via paperopoli 12","","1",(float)16.3);
		Fattura f1=new Fattura("pippo","pluto","via paperopoli 12","","2",(float)18.5);
		
		cDao.inserisciFattura(f);
		cDao.inserisciFattura(f1);

		Pagamento p=new Pagamento(1, "cash", 0, "pippo", (float)16.3, "cash");
		Pagamento p1=new Pagamento(2, "cash", 0, "pippo", (float)18.5, "cash");


		pD.inserisciPagamento(p);
		pD.inserisciPagamento(p1);
		
		cD.annullaOrdine();
		assertEquals("cash",vis.getMetodoP());
	}
	@Test
	void testAnnullaOrdineR() throws SQLException
	{
		vis.setTypeAsMagazine();
		vis.setMetodoP("cCredito");
		
		

		Pagamento p=new Pagamento(1, "cc", 0, "pippo", (float)16.3, "cash");
		Pagamento p1=new Pagamento(2, "cc", 0, "pippo", (float)18.5, "cash");


		pD.inserisciPagamento(p);
		pD.inserisciPagamento(p1);
		
		cD.annullaOrdine();
		assertEquals("cCredito",vis.getMetodoP());
	}

	@Test
	void testCancellaL() throws SQLException {
		vis.setId(20);
		vis.setTypeAsBook();
		cGP.cancella(vis.getId());
		assertEquals(20,vis.getId());
		
	}
	@Test
	void testCancellaG() throws SQLException {
		vis.setId(11);
		vis.setTypeAsDaily();
		cGP.cancella(vis.getId());
		assertEquals(11,vis.getId());
		
	}
	@Test
	void testCancellaR() throws SQLException {
		vis.setId(5);
		vis.setTypeAsMagazine();
		cGP.cancella(vis.getId());
		assertEquals(5,vis.getId());
		
	}

	@Test
	void testGetRivistaS() throws SQLException {
		assertNotNull(cGP.getLista("rivista"));
	}

	@Test
	void testGetLibroS() throws SQLException {
		assertNotNull(cGP.getLista("libro"));
	}

	@Test
	void testGetGiornaleS() throws SQLException {
		assertNotNull(cGP.getLista("giornale"));
	}

	@Test
	void testLogout() throws LogoutException {
		u.setNome("Zerocalcare");
		assertTrue(ControllerHomePageAfterLogin.logout());
		
	}
	
	@Test
	void testLogoutSE() throws LogoutException {
		u.setNome("Bao Publishing");
		assertTrue(ControllerHomePageAfterLoginSE.logout());
		
	}
	@Test
	void testControlla() throws SQLException {
		
		assertTrue(cL.controlla("zerocalcare@gmail.com", "Zerocalcare21"));
	}

	@Test
	void testGetRuoloTempUSer() throws SQLException  {
			assertEquals("W",cL.getRuoloTempUSer("zerocalcare@gmail.com"));
	}
	@Test
	void testPrendi() throws SQLException {
		u.setEmail("giannni@gmail.com");
		assertNotNull(cMU.prendi());
	}

	@Test
	void testAggiorna() throws SQLException
	{
		assertTrue(cMU.aggiorna("g", "c","emailProva@gmail.com","provaPass","descProva",LocalDate.of(1952, 6,6) ,"giannni@gmail.com"));
	}
	

	@Test
	void testAggiornaTot() throws SQLException {
		u.setId(2);
		assertTrue(cMU.aggiornaTot("gianni","morandi" ,"gMorandi@gmail.com","71Giannone" ,"un mito" ,LocalDate.of(1952, 6,7),"A"));
	}
	
	@Test
	void testGetLibriById() throws SQLException {
		assertNotNull(cMP.getLibriById(1));
	}

	@Test
	void testGetGiornaliById() throws SQLException {
		assertNotNull(cMP.getGiornaliById(2));
	}

	@Test
	void testCheckDataGM() throws SQLException {
		ControllerSystemState.getInstance().setId(1);
		cambio[0]="cambio giornale";
		cambio[1]="quotidinao";
		cambio[2]="cambio ediitore";
		cambio[3]="italiano";
		cMP.checkDataG(cambio,LocalDate.of(2022, 11,20), 0, (float)1.25, 10);
		
	}

	@Test
	void testGetRivistaById() throws SQLException {
		assertNotNull(cMP.getRivistaById(5));
	}

	
	@Test
	void testCheckDataRM() throws SQLException {
		cambioR[0]="cambio rivista";
		cambioR[1]="MENSILE";
		cambioR[2]="cambio autore";
		cambioR[3]="ita";
		cambioR[4]="cambio editore";
		cMP.checkDataR(cambioR, LocalDate.of(2023, 5,11), 0, (float)2.36, 100, 1, "rivista cambiata2");
	}

	@Test
	void testCheckDataL() throws NullPointerException, SQLException {
		info[0]="cambio libro";
		info[2]="cambio autore";
		info[3]="italiano";
		info[4]="cambio editore";
		info[5]="TECNOLOGIA_MEDICINA";
		
		infoCosti[0]="100";
		infoCosti[1]="12345674";
		infoCosti[3]="1";
		infoCosti[4]=String.valueOf((float)2.45);
		infoCosti[5]="50";
		ControllerSystemState.getInstance().setId(18);
		//vedere qui
		cMP.checkDataL(info, "aggiorno libro","provo ad aggiornare", LocalDate.of(2023,1,5), infoCosti);
	}
	
	@Test
	void testControllaL() throws SQLException,  IdException {
		vis.setTypeAsBook();
		vis.setId(1);
		vis.setSpesaT((float)11.25);
		cPC.controlla("pippo","pluto","via paperopoli 12","");
		assertEquals(1,vis.getId());
	}
	@Test
	void testControllaG() throws SQLException,  IdException {
		vis.setTypeAsDaily();
		vis.setId(1);
		vis.setSpesaT((float)5.50);
		cPC.controlla("franco","rossi", "via rossi 8","dopo le 12");
		assertEquals(1,vis.getId());

	}
	@Test
	void testControllaR() throws SQLException,  IdException {
		vis.setId(3);
		vis.setTypeAsMagazine();
		vis.setSpesaT((float)6.25);
		cPC.controlla("franco","rossi", "via rossi 8","dopo le 12");
		assertEquals(3,vis.getId());
		
	}
	@Test
	void testRitornaMessaggio()
	{
		vis.setTypeAsBook();
		String s=cC.ritornaMessaggio();
		assertEquals("Benvenuto... ecco la lista dei libri nel nostro catalogo...",s);
	}
	@Test
	void testPopolaBottoneV()
	{
		vis.setTypeAsBook();
		String s=cC.popolaBottoneV();
		assertEquals("Mostra Libro",s);
	}
	@Test
	void testPopolaBottoneA()
	{
		vis.setTypeAsBook();
		String s=cC.popolaBottoneA();
		assertEquals("Acquista Libro",s);
	}
	
	

}
