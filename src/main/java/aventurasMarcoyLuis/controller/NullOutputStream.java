package aventurasMarcoyLuis.controller;

import java.io.IOException;
import java.io.OutputStream;

public class NullOutputStream extends OutputStream {

    public NullOutputStream() { super(); }
    // Null implementation of inherited abstract method
    public void write(int b) throws IOException { }
}
