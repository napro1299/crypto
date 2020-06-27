package io;

public interface Source {

    void lock(boolean locked);

    int read(TransitoryInputStream in);

    int write();
}
