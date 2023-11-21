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
    private final ResourceBundle RBLIBRODAINSERIRE = ResourceBundle.getBundle("configurations/libroDaInserire");
    private final ResourceBundle RBMODIFICALIBRO=ResourceBundle.getBundle("configurations/libroDaModificare");
    private  final ResourceBundle RBPAGAMENTO=ResourceBundle.getBundle("configurations/pagamento");
    private  final  ResourceBundle RBPAGAMENTOINFO=ResourceBundle.getBundle("configurations/pagamentoInfo");
    private  final ResourceBundle RBFATTURA=ResourceBundle.getBundle("configurations/fattura");
    private  final ResourceBundle RBPAGAMENTOESITO = ResourceBundle.getBundle("configurations/pagamentoEsito");
    private  final ResourceBundle RBBOOKCATEGORIES = ResourceBundle.getBundle("configurations/bookCategories");

    private final ResourceBundle RBSETTAOOGETTO=ResourceBundle.getBundle("configurations/settaOggetto");

    private final ResourceBundle RBCARTADICREDITO=ResourceBundle.getBundle("configurations/cartaCredito");


    private final Libro l=new Libro();
    private final String[] info=new String[7];
    private final String[] infoGen= new String[6];
    private final String[] infoCosti=new String[6];
    private final java.sql.Date dataS= new java.sql.Date(Calendar.getInstance().getTime().getTime());

    private final ControllerAggiungiPage cAP=new ControllerAggiungiPage();
    private final ControllerModifPage cMP=new ControllerModifPage();
    private final ControllerDownload cD=new ControllerDownload();
    private final ControllerAcquista cA=new ControllerAcquista();
    private final ControllerPagamentoCC cPCC=new ControllerPagamentoCC();
    private final ControllerReportPage cRP =new ControllerReportPage();
    private final ControllerVisualizza cV=new ControllerVisualizza();
    private final ControllerGestionePage cGP=new ControllerGestionePage();
    private final ControllerCompravendita cC=new ControllerCompravendita();
    private final ControllerPagamentoCash cPCash=new ControllerPagamentoCash();
    private final CartaDiCredito cc1=new CartaDiCredito();
    private final CartaDiCredito cc2=new CartaDiCredito();
    private final ControllerSystemState vis = ControllerSystemState.getInstance() ;
    private final Fattura f=new Fattura();
    private final Fattura f1=new Fattura();
    private final Factory factory=new Factory();
    private final  Pagamento p=new Pagamento();
    private final  Pagamento p2=new Pagamento();

    private final LibroDao lD=new LibroDao();
    private final ContrassegnoDao cDao=new ContrassegnoDao();
    private final PagamentoDao pD=new PagamentoDao();

    @Test
    void testCancellaL() throws SQLException {
        vis.setTypeAsBook();
        cGP.cancella(10);
        assertEquals("libro",vis.getType());
    }

    @Test
    void testCategorieL()
    {


        Enumeration<String> keys = RBBOOKCATEGORIES.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement().replace("=","");
            l.setCategoria(key);
        }
        assertNotNull(l.getCategoria());
    }





    @Test
    void testCheckInsertLibro() throws SQLException {
        info[0]=RBLIBRODAINSERIRE.getString("info[0]");
        info[1]=RBLIBRODAINSERIRE.getString("info[1]");
        info[2]=RBLIBRODAINSERIRE.getString("info[2]");
        info[3]=RBLIBRODAINSERIRE.getString("info[3]");
        info[4]=RBLIBRODAINSERIRE.getString("info[4]");
        info[5]=RBLIBRODAINSERIRE.getString("info[5]");
        info[6]=RBLIBRODAINSERIRE.getString("info[6]");
        infoGen[0]=String.valueOf(100);
        infoGen[1]=RBLIBRODAINSERIRE.getString("infoGen[1]");
        infoGen[2]=RBLIBRODAINSERIRE.getString("infoGen[2]");
        infoGen[3]=RBLIBRODAINSERIRE.getString("infoGen[3]");
        infoGen[4]=RBLIBRODAINSERIRE.getString("infoGen[4]");
        infoGen[5]=RBLIBRODAINSERIRE.getString("infoGen[5]");

        l.setCategoria(RBLIBRODAINSERIRE.getString("info[1]"));
        l.setDataPubb(dataS.toLocalDate());
        l.setRecensione(RBLIBRODAINSERIRE.getString("recensione"));
        l.setDesc(RBLIBRODAINSERIRE.getString("descrizione"));


        assertTrue(cAP.checkData(info, l.getRecensione(), l.getDesc(), l.getDataPubb(), infoGen));

    }
    @Test
    void testInizializzazioneOggetti()  {
        cc1.setTipo(Integer.parseInt(RBPAGAMENTO.getString("tipo")));
        cc1.setNumeroCC(RBPAGAMENTO.getString("numero"));
        cc1.setLimite(Double.parseDouble(RBPAGAMENTO.getString("limite")));
        cc1.setAmmontare(Double.parseDouble(RBPAGAMENTO.getString("ammontare")));
        cc1.setScadenza(dataS);
        cc1.setNomeUser(RBPAGAMENTO.getString("nomeUser"));
        cc1.setPrezzoTransazine(Float.parseFloat(RBPAGAMENTO.getString("prezzoTransazione")));

        cc2.setNomeUser(RBPAGAMENTO.getString("nome"));
        cc2.setCognomeUser(RBPAGAMENTO.getString("cognome"));
        cc2.setNumeroCC(RBPAGAMENTO.getString("codice"));
        cc2.setCiv(RBPAGAMENTO.getString("civ"));
        cc2.setScadenza(dataS);
        cc2.setAmmontare(Float.parseFloat(RBPAGAMENTO.getString("ammontare")));

        assertNotEquals(0.0f,cc2.getAmmontare());

    }
    @Test
    void testModificaL() throws NullPointerException, SQLException {
        ControllerSystemState.getInstance().setId(18);
        info[0]=RBMODIFICALIBRO.getString("info[0]");
        info[2]=RBMODIFICALIBRO.getString("info[2]");
        info[3]=RBMODIFICALIBRO.getString("info[3]");
        info[4]=RBMODIFICALIBRO.getString("info[4]");
        info[5]=RBMODIFICALIBRO.getString("info[5]");

        infoCosti[0]="100";
        infoCosti[1]=RBMODIFICALIBRO.getString("infoCosti[1]");
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

        f.setNome(RBFATTURA.getString("nome1"));
        f.setCognome(RBFATTURA.getString("cognome1"));
        f.setVia(RBFATTURA.getString("via"));
        f.setCom(RBFATTURA.getString("comunicazioni1"));
        f.setNumero(RBFATTURA.getString("numeroF1"));
        f.setAmmontare(Float.parseFloat(RBFATTURA.getString("ammontare1")));

        f1.setNome(RBFATTURA.getString("nome1"));
        f1.setCognome(RBFATTURA.getString("cognome1"));
        f1.setVia(RBFATTURA.getString("via"));
        f1.setCom(RBFATTURA.getString("comunicazioni2"));
        f1.setNumero(RBFATTURA.getString("numeroF2"));
        f1.setAmmontare(Float.parseFloat(RBFATTURA.getString("ammontare2")));
        cDao.inserisciFattura(f);
        cDao.inserisciFattura(f1);

        p.setId(Integer.parseInt(RBPAGAMENTO.getString("id1")));
        p.setMetodo(RBPAGAMENTO.getString("metodo"));
        p.setEsito(Integer.parseInt(RBPAGAMENTO.getString("esito")));
        p.setNomeUtente(RBPAGAMENTO.getString("nomeUtente"));
        p.setAmmontare(Float.parseFloat(RBPAGAMENTO.getString("spesa1")));
        p.setTipo(RBPAGAMENTO.getString("tipo"));

        p2.setId(Integer.parseInt(RBPAGAMENTO.getString("id2")));
        p2.setMetodo(RBPAGAMENTO.getString("metodo"));
        p2.setEsito(Integer.parseInt(RBPAGAMENTO.getString("esito")));
        p2.setNomeUtente(RBPAGAMENTO.getString("nomeUtente"));
        p2.setAmmontare(Float.parseFloat(RBPAGAMENTO.getString("spesa2")));
        p2.setTipo(RBPAGAMENTO.getString("tipo"));

        pD.inserisciPagamento(p);
        pD.inserisciPagamento(p2);

        cD.annullaOrdine();
        assertEquals("cash",vis.getMetodoP());
    }
    @Test
    void testTotale() throws SQLException, IdException {
        l.setTitolo(RBSETTAOOGETTO.getString("titoloL"));
        l.setNrCopie(Integer.parseInt(RBSETTAOOGETTO.getString("copieL")));
        vis.setQuantita(3);
        vis.setId(Integer.parseInt(RBSETTAOOGETTO.getString("idL")));
        vis.setTypeAsBook();
        l.setDisponibilita(Integer.parseInt(RBSETTAOOGETTO.getString("disponibilita")));
        assertEquals(0,cA.totale1(vis.getType(),l.getTitolo(), l.getDisponibilita(), vis.getQuantita()));
    }
    @Test
    void testCreateRaccoltaFinaleCompletaL() {
        factory.createRaccoltaFinale1(RBSETTAOOGETTO.getString("tipologia1"),RBSETTAOOGETTO.getString("titolo1"),RBSETTAOOGETTO.getString("categoria1"),RBSETTAOOGETTO.getString("autore1"),RBSETTAOOGETTO.getString("lingua1"), RBSETTAOOGETTO.getString("editore1"),RBSETTAOOGETTO.getString("categoria1"));
        factory.createRaccoltaFinale2(RBSETTAOOGETTO.getString("tipologia1"),Integer.parseInt(RBSETTAOOGETTO.getString("numPag1")),RBSETTAOOGETTO.getString("isbn"), Integer.parseInt(RBSETTAOOGETTO.getString("numPag1")), Integer.parseInt(RBSETTAOOGETTO.getString("disp1")), Float.parseFloat(RBSETTAOOGETTO.getString("prezzo1")), Integer.parseInt(RBSETTAOOGETTO.getString("nrCopie")));
        assertNotNull(factory.createRaccoltaFinaleCompleta(RBSETTAOOGETTO.getString("tipologia1"),LocalDate.now(), RBSETTAOOGETTO.getString("recensione1"), RBSETTAOOGETTO.getString("descrizione1"), Integer.parseInt(RBSETTAOOGETTO.getString("id"))));


    }
    @Test
    void testAggiungiCartaDBL() throws SQLException, IdException {
        vis.setSpesaT((float)125.6);
        vis.setTypeAsBook();
        java.sql.Date data= dataS;
        cPCC.aggiungiCartaDB(RBCARTADICREDITO.getString("nome1"),RBCARTADICREDITO.getString("cognome1"),RBCARTADICREDITO.getString("codice1"), data, RBCARTADICREDITO.getString("civ1"), Float.parseFloat(RBCARTADICREDITO.getString("prezzo")));
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
        cPCC.pagamentoCC(RBCARTADICREDITO.getString("nome1"));
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
        assertDoesNotThrow(()->cPCash.controlla(RBFATTURA.getString("nome1"), RBFATTURA.getString("cognome1"),RBFATTURA.getString("via"),RBFATTURA.getString("comunicazioni1")));

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




