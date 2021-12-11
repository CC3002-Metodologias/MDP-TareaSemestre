package aventurasMarcoyLuis.interfaces;

import java.util.List;

/**
 * Interfaz de un Item
 */
public interface Items {

    /** Metodo que se emplea para hacer uso del item, que luego llama al metodo del personaje en cuestion que lo ocupa */
    void use(Iplayer p1);
    /** Metodo para eleminar el objeto del baul luego de ser usado */
    void deleteBaul(List<Items> baul);

    /** metodo para obtener el nombre del item */
    String getName();
}
