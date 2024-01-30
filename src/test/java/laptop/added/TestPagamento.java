package laptop.added;

import laptop.exception.IdException;
import laptop.model.Pagamento;
import org.junit.jupiter.api.Test;
import web.bean.PagamentoBean;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestPagamento {
    private final Pagamento p=new Pagamento();
    private final PagamentoBean pB=new PagamentoBean();

    @Test
    void setters() throws IdException {
        p.setMetodo("cash");
        p.setId(0);
        p.setNomeUtente("franco");
        p.setAmmontare(18.6f);
        p.setTipo("ECONOMIA");
        p.setIdOggetto(2);

        pB.setMetodoB(p.getMetodo());
        pB.setIdB(p.getId());
        pB.setNomeUtenteB(p.getNomeUtente());
        pB.setAmmontareB(p.getAmmontare());
        pB.setIdOggettoB(p.getIdOggetto());

        assertNotEquals(0,pB.getIdOggettoB());
        }
}
