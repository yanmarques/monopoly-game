package com.org.circle;

import com.org.Node;
import com.org.chained_list.DoubleChainedList;

public class DoubleCircledList<T> extends DoubleChainedList<T> {
    public void insertFirst(Node<T> node) {
        if (this.isEmpty()) {
            this.fillEmpty(node);
        } else {
            node.setNext(this.getInitial());
            node.setPrevious(this.getLast());
            this.getInitial().setPrevious(node);
            this.setInitial(node);
            this.getLast().setNext(node);
        }

        this.incrementSize();
    }

    public void insertLast(Node<T> node) {
        if (this.isEmpty()) {
            this.fillEmpty(node);
        } else {
            node.setPrevious(this.getLast());
            node.setNext(this.getInitial());
            this.getLast().setNext(node);
            this.getInitial().setPrevious(node);
            this.setLast(node);
        }

        this.incrementSize();
    }

    protected void fillEmpty(Node<T> node) {
        node.setPrevious(node);
        node.setNext(node);

        this.setInitial(node);
        this.setLast(node);
    }
}
