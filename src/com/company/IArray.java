package com.company;

import java.io.IOException;

public interface IArray {
    void add(double x1, double y1, double x2, double y2, double x3, double y3);

    Object get(int index);

    Object remove(int index);

    void clear();

    void save(String filename) throws IOException;

    void load(String filename) throws IOException;
}
