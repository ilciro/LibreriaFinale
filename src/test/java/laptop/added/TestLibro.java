package laptop.added;

import laptop.database.LibroDao;
import laptop.model.raccolta.Libro;
import org.junit.jupiter.api.Test;
import web.bean.LibroBean;

import java.io.IOException;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestLibro {
    private final LibroDao lD=new LibroDao();
    private final Libro l=new Libro();
    private final LibroBean lB=new LibroBean();

    public TestLibro() throws IOException {
    }

    @Test
    void testSetter()
    {
        l.setId(19);
        Libro l1=lD.getData(l);

        lB.setTitoloB(l1.getTitolo());
        lB.setNumeroPagineB(l1.getNrPagine());
        lB.setCodIsbnB(l1.getCodIsbn());
        lB.setEditoreB(l1.getEditore());
        lB.setAutoreB(l1.getAutore());
        lB.setCategoriaB(l1.getCategoria());
        lB.setDateB(Date.valueOf(l1.getDataPubb()));
        lB.setRecensioneB(l1.getRecensione());
        lB.setNrCopieB(l1.getNrCopie());
        lB.setDescB(l1.getDesc());
        lB.setDisponibilitaB(l1.getDisponibilita());
        lB.setPrezzoB(l1.getPrezzo());
        lB.setIdB(l1.getId());

        assertNotEquals(0,lB.getIdB());



    }


}