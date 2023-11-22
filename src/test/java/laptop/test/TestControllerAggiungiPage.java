package laptop.test;

import laptop.controller.ControllerAggiungiPage;
import laptop.model.raccolta.Giornale;
import laptop.model.raccolta.Libro;
import laptop.model.raccolta.Rivista;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestControllerAggiungiPage {

    private final ResourceBundle RBGIORNALEDAINSERIRE=ResourceBundle.getBundle("configurations/giornaleDaInserire");
    private final ResourceBundle RBLIBRODAINSERIRE = ResourceBundle.getBundle("configurations/libroDaInserire");
    private final ResourceBundle RBRIVISTADAINSERIRE=ResourceBundle.getBundle("configurations/rivistaDaInserire");
    private final Giornale g=new Giornale();
    private final Libro l=new Libro();
    private final Rivista r=new Rivista();
    private final ControllerAggiungiPage cAP=new ControllerAggiungiPage();

    private final String[] info=new String[7];
    private final String[] infoGen= new String[6];
    private final java.sql.Date dataS= new java.sql.Date(Calendar.getInstance().getTime().getTime());




    @Test
    void testCheckInsertGiornale() throws SQLException {
        g.setTitolo(RBGIORNALEDAINSERIRE.getString("titolo"));
        g.setTipologia(RBGIORNALEDAINSERIRE.getString("tipologia"));
        g.setLingua(RBGIORNALEDAINSERIRE.getString("lingua"));
        g.setEditore(RBGIORNALEDAINSERIRE.getString("editore"));
        g.setDataPubb(LocalDate.of(2022, 12,12));
        g.setCopieRimanenti(Integer.parseInt(RBGIORNALEDAINSERIRE.getString("copieRimanenti")));
        g.setDisponibilita(Integer.parseInt(RBGIORNALEDAINSERIRE.getString("disponibilita")));
        g.setPrezzo(Float.parseFloat(RBGIORNALEDAINSERIRE.getString("prezzo")));
        assertTrue(cAP.checkDataG(g));
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
    void testCheckInsertRivista() throws SQLException {
        info[0]=RBRIVISTADAINSERIRE.getString("info[0]");
        info[1]=RBRIVISTADAINSERIRE.getString("info[1]");
        info[2]=RBRIVISTADAINSERIRE.getString("info[2]");
        info[3]=RBRIVISTADAINSERIRE.getString("info[3]");
        info[4]=RBRIVISTADAINSERIRE.getString("info[4]");
        r.setDescrizione(RBRIVISTADAINSERIRE.getString("descrizione"));
        r.setDataPubb(LocalDate.of(2022, 11,12));
        r.setDisp(Integer.parseInt(RBRIVISTADAINSERIRE.getString("disponibilita")));
        r.setPrezzo(Float.parseFloat(RBRIVISTADAINSERIRE.getString("prezzo")));
        r.setCopieRim(Integer.parseInt(RBRIVISTADAINSERIRE.getString("copieRimanenti")));
        assertTrue(cAP.checkDataR(info, r.getDataPubb(), r.getDisp(),r.getPrezzo() , r.getCopieRim(), r.getDescrizione()));

    }
}
