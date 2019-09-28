package com.org.interfaces;

import com.org.Node;

public interface PathWalker<T> extends List<T> {
    void forwardTo(int position, boolean checkPositive) throws UnsupportedOperationException;
    void backwardTo(int position, boolean checkPositive) throws UnsupportedOperationException;

    Node<T> getCurrentNode();
    int getCurrentPosition();
}
