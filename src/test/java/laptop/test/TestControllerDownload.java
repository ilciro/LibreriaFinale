package laptop.test;

import com.itextpdf.text.DocumentException;
import laptop.controller.ControllerDownload;
import laptop.controller.ControllerSystemState;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestControllerDownload {

    private final ControllerDownload cD=new ControllerDownload();

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6})
    void testScaricaLibro(int ints) throws DocumentException, IOException, URISyntaxException {
        ControllerSystemState.getInstance().setId(ints);
        ControllerSystemState.getInstance().setTypeAsBook();
        assertDoesNotThrow(()->cD.scarica(ControllerSystemState.getInstance().getType()));
    }
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    void testScaricaRivista(int ints) throws DocumentException, IOException, URISyntaxException {
        ControllerSystemState.getInstance().setId(ints);
        ControllerSystemState.getInstance().setTypeAsMagazine();
        assertDoesNotThrow(()->cD.scarica(ControllerSystemState.getInstance().getType()));
    }
    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    void testScaricaGiornale(int ints) throws DocumentException, IOException, URISyntaxException {
        ControllerSystemState.getInstance().setId(ints);
        ControllerSystemState.getInstance().setTypeAsDaily();
        assertDoesNotThrow(()->cD.scarica(ControllerSystemState.getInstance().getType()));
    }
}
