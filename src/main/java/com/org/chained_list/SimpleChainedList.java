package com.org.chained_list;

import com.org.Node;

public class SimpleChainedList<T> extends ForwardChainedMemory<T> {
    @Override
    public void backwardTo(int position, boolean checkPositive) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Simple chain only forwards.");
    }

    public void insert(int position, Node<T> node) throws ArrayIndexOutOfBoundsException {
        this.ensurePositionExists(position, true);

        if (position == 0) {
            node.setNext(this.getInitial());
            this.setInitial(node);
        } else {
            Node actual = this.get(position - 1);
            node.setNext(actual.getNext());
            actual.setNext(node);
        }

        this.incrementSize();
    }

    public Node<T> remove(int position) throws ArrayIndexOutOfBoundsException {
        this.ensurePositionExists(position, true);

        Node<T> target;

        if (position == 0) {
            target = this.getInitial();
            this.setInitial(this.getInitial().getNext());
        } else {
            Node<T> actual = this.get(position - 1);
            target = actual.getNext();
            actual.setNext(target.getNext());
        }

        this.decrementSize();

        return target;
    }
}
