package com.org.stack;

import com.org.ListPathWalker;
import com.org.interfaces.StorageAccessor;

import java.util.ArrayList;
import com.org.Node;

public class Stack<T> extends ListPathWalker<T> implements StorageAccessor<T> {
    @Override
    public void forwardTo(int position, boolean checkPositive) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Stack can not be forwarded.");
    }

    public void insert(Node<T> node) {
        if (! isEmpty()) {
            node.setPrevious(this.getInitial());
        }

        this.setInitial(node);
        this.incrementSize();
    }

    public Node<T> remove() throws IllegalAccessError {
        return this.remove(true);
    }

    public Node<T> remove(boolean forget) throws IllegalAccessError {
        if (isEmpty()) {
            throw new IllegalAccessError("Stack is already empty.");
        }

        Node<T> current = this.getInitial();
        this.setInitial(current.getPrevious());

        if (forget) {
            current.setPrevious(null);
        }

        this.decrementSize();
        return current;
    }

    public ArrayList<Node<T>> toArray() {
        ArrayList<Node<T>> stack = new ArrayList<>();

        this.resetToInitialNode();
        while (this.getCurrentNode() != null) {
            stack.add(this.getCurrentNode());
            this.backwardOperation();
        }

        return stack;
    }
}
