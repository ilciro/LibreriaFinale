package laptop.added;

import laptop.database.RivistaDao;
import laptop.model.raccolta.Rivista;
import org.junit.jupiter.api.Test;
import web.bean.RivistaBean;

import java.io.IOException;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestRivista {
    private final Rivista r=new Rivista();
    private final RivistaDao rD=new RivistaDao();
    private final RivistaBean rB=new RivistaBean();
    public TestRivista() throws IOException {
    }
    @Test
    void settters()
    {
        r.setId(5);
        Rivista r1=rD.getData(r);
        rB.setTipologiaB(r1.getTitolo());
        rB.setTipologiaB(r1.getTipologia());
        rB.setAutoreB(r1.getAutore());
        rB.setLinguaB(r1.getLingua());
        rB.setEditoreB(rB.getEditoreB());
        rB.setDescrizioneB(r1.getDescrizione());
        rB.setDataB(Date.valueOf(r1.getDataPubb()));
        rB.setDispB(r1.getDisp());
        rB.setPrezzoB(r1.getPrezzo());
        rB.setCopieRimB(r1.getCopieRim());
        rB.setIdB(r1.getId());
        assertNotEquals(0,rB.getIdB());
    }
}
