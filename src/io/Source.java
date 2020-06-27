package io;

public interface Source {

    int read(TransitoryInputStream in);

    int write();

    void lock();

    void open();

    boolean isOpen();
}
