package aventurasMarcoyLuis.controller;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Clase para instanciar un Null OutputStream para que no printee nada.
 */
public class NullOutputStream extends OutputStream {
    /** Constructor */
    public NullOutputStream() { super(); }
    /** Null implementation of inherited abstract method */
    @Override
    public void write(int b) throws IOException { }
}
