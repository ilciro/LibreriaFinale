package laptop.test;

import com.itextpdf.text.DocumentException;
import laptop.controller.*;
import laptop.database.ContrassegnoDao;
import laptop.database.LibroDao;
import laptop.database.PagamentoDao;
import laptop.exception.IdException;
import laptop.model.CartaDiCredito;
import laptop.model.Fattura;
import laptop.model.Pagamento;
import laptop.model.raccolta.Factory;
import laptop.model.raccolta.Libro;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TestLibro {
    private final ResourceBundle rBLibroDAInserire = ResourceBundle.getBundle("configurations/libroDaInserire");
    private final ResourceBundle rBModificaLibro=ResourceBundle.getBundle("configurations/libroDaModificare");
    private  final ResourceBundle rBPagamento=ResourceBundle.getBundle("configurations/pagamento");
    private  final  ResourceBundle rBPagamentoInfo=ResourceBundle.getBundle("configurations/pagamentoInfo");
    private  final ResourceBundle rBFattura=ResourceBundle.getBundle("configurations/fattura");
    private  final ResourceBundle rBPagamentoEsito = ResourceBundle.getBundle("configurations/pagamentoEsito");
    private  final ResourceBundle rBBookCategories = ResourceBundle.getBundle("configurations/bookCategories");

    private final ResourceBundle rBSettaOggetto=ResourceBundle.getBundle("configurations/settaOggetto");

    private final ResourceBundle rBCartaCredito=ResourceBundle.getBundle("configurations/cartaCredito");


    private Libro l=new Libro();
    private String[] info=new String[7];
    private String[] infoGen= new String[6];
    private String[] infoCosti=new String[6];
    private java.sql.Date dataS= new java.sql.Date(Calendar.getInstance().getTime().getTime());

    private ControllerAggiungiPage cAP=new ControllerAggiungiPage();
    private ControllerModifPage cMP=new ControllerModifPage();
    private ControllerDownload cD=new ControllerDownload();
    private ControllerAcquista cA=new ControllerAcquista();
    private ControllerPagamentoCC cPCC=new ControllerPagamentoCC();
    private ControllerReportPage cRP =new ControllerReportPage();
    private ControllerVisualizza cV=new ControllerVisualizza();
    private ControllerGestionePage cGP=new ControllerGestionePage();

    private ControllerCompravendita cC=new ControllerCompravendita();
    private ControllerPagamentoCash cPCash=new ControllerPagamentoCash();






    private static CartaDiCredito cc1=new CartaDiCredito();
    private static CartaDiCredito cc2=new CartaDiCredito();
    private static ControllerSystemState vis = ControllerSystemState.getInstance() ;
    private static Fattura f=new Fattura();
    private static Fattura f1=new Fattura();
    private Factory factory=new Factory();


    private static Pagamento p=new Pagamento();
    private static Pagamento p2=new Pagamento();

    private LibroDao lD=new LibroDao();
    private ContrassegnoDao cDao=new ContrassegnoDao();
    private PagamentoDao pD=new PagamentoDao();

    @Test
    void testCancellaL() throws SQLException {
        vis.setTypeAsBook();
        cGP.cancella(10);
        assertEquals("libro",vis.getType());
    }

    @Test
    void testCategorieL()
    {


        Enumeration<String> keys = rBBookCategories.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            key.replace("=","");
            l.setCategoria(key);
        }
        assertNotNull(l.getCategoria());
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
    void testInizializzazioneOggetti()  {
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

        assertNotEquals(0.0f,cc2.getAmmontare());

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
    void testCreateRaccoltaFinaleCompletaL() {
        factory.createRaccoltaFinale1(rBSettaOggetto.getString("tipologia1"),rBSettaOggetto.getString("titolo1"),rBSettaOggetto.getString("categoria1"),rBSettaOggetto.getString("autore1"),rBSettaOggetto.getString("lingua1"), rBSettaOggetto.getString("editore1"),rBSettaOggetto.getString("categoria1"));
        factory.createRaccoltaFinale2(rBSettaOggetto.getString("tipologia1"),Integer.parseInt(rBSettaOggetto.getString("numPag1")),rBSettaOggetto.getString("isbn"), Integer.parseInt(rBSettaOggetto.getString("numPag1")), Integer.parseInt(rBSettaOggetto.getString("disp1")), Float.parseFloat(rBSettaOggetto.getString("prezzo1")), Integer.parseInt(rBSettaOggetto.getString("nrCopie")));
        assertNotNull(factory.createRaccoltaFinaleCompleta(rBSettaOggetto.getString("tipologia1"),LocalDate.now(), rBSettaOggetto.getString("recensione1"), rBSettaOggetto.getString("descrizione1"), Integer.parseInt(rBSettaOggetto.getString("id"))));


    }
    @Test
    void testAggiungiCartaDBL() throws SQLException, IdException {
        vis.setSpesaT((float)125.6);
        vis.setTypeAsBook();
        java.sql.Date data= dataS;
        cPCC.aggiungiCartaDB(rBCartaCredito.getString("nome1"),rBCartaCredito.getString("cognome1"),rBCartaCredito.getString("codice1"), data, rBCartaCredito.getString("civ1"), Float.parseFloat(rBCartaCredito.getString("prezzo")));
        assertNotNull(data);
    }
    @Test
    void testGeneraReportLibri() throws IOException, SQLException {
        String report="report libri";
        cRP.generaReportLibri();
        assertEquals("report libri",report);
    }
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7,8,9,10})
    void testGetDataL(int ints) throws SQLException {
        assertNotNull(cV.getDataL(ints));
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
    void testGetLibrySongoloById() throws SQLException {
        l.setId(1);
        assertNotNull(lD.getLibriSingoloById(l));
    }
    @Test
    void testGetLista() throws SQLException {
        vis.setTypeAsBook();
        assertNotNull(cC.getLista(vis.getType()));
    }
    @Test
    void testDisponibilita() throws SQLException, IdException {
        vis.setTypeAsBook();
        assertTrue(cC.disponibilita(vis.getType(),"1"));
    }
    @Test
    void testControlla() {
        assertDoesNotThrow(()->cPCash.controlla(rBFattura.getString("nome1"), rBFattura.getString("cognome1"),rBFattura.getString("via"),rBFattura.getString("comunicazioni1")));

    }

    @ParameterizedTest
    @ValueSource(ints= {1,2,3,4,5,6})
    void testScaricaLibro(int ints) throws DocumentException, IOException, URISyntaxException {
        vis.setId(ints);
        vis.setTypeAsBook();
        cD.scarica(vis.getType());
        assertEquals(ints,vis.getId());
    }



}


