package com.org.interfaces;

import com.org.Node;

public interface ChainedList<T> extends List<T> {
    void insert(int position, Node<T> Node) throws ArrayIndexOutOfBoundsException;
    Node<T> remove(int position) throws ArrayIndexOutOfBoundsException;
    Node<T> get(int position) throws ArrayIndexOutOfBoundsException;
}
