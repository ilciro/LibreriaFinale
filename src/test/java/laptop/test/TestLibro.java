/*package laptop.test;

import laptop.model.raccolta.Libro;
import org.junit.jupiter.api.Test;


import java.util.Enumeration;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestLibro {
    private final ResourceBundle RBBOOKCATEGORIES = ResourceBundle.getBundle("configurations/bookCategories");
    private final Libro l=new Libro();

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


}
*/