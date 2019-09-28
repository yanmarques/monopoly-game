package com.org.interfaces;

import com.org.Node;

public interface List<T> {
    Node<T> getInitial();
    Node<T> getLast();
    int getSize();
    boolean isEmpty();
    void clear();
}
