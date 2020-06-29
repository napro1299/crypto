package io;

import java.io.IOException;

public interface Source {

    int read() throws IOException;

    int write() throws IOException;

    void lock();

    void open();

    boolean isOpen();
}
