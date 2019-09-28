package com.org.interfaces;

import com.org.Node;

public interface StorageAccessor<T> extends List<T> {
    void insert(Node<T> node);
    Node<T> remove();
}
