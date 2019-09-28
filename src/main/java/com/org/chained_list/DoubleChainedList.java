package com.org.chained_list;

import com.org.Node;

public class DoubleChainedList<T> extends ForwardChainedMemory<T> {
    @Override
    public void insert(int position, Node<T> node) throws ArrayIndexOutOfBoundsException {
        this.ensurePositionExists(position, true);

        if (position == 0) {
            this.insertFirst(node);
            return;
        }

        if (position == this.getSize()) {
            this.insertLast(node);
            return;
        }

        Node current = this.get(position - 1);
        node.setPrevious(current);
        node.setNext(current.getNext());
        current.setNext(node);
        node.getNext().setPrevious(node);

        this.incrementSize();
    }

    public void insertFirst(Node<T> node) {
        if (this.isEmpty()) {
            this.setInitial(node);
            this.setLast(node);
        } else {
            node.setNext(this.getInitial());
            this.getInitial().setPrevious(node);
            this.setInitial(node);
        }

        this.incrementSize();
    }

    public void insertLast(Node<T> node) {
        node.setPrevious(this.getLast());
        this.getLast().setNext(node);
        this.setLast(node);

        this.incrementSize();
    }

    @Override
    public Node<T> remove(int position) throws ArrayIndexOutOfBoundsException {
        this.ensurePositionExists(position, true);

        if (position == 0) {
            return this.removeFirst();
        }

        if (position == (this.getSize() - 1)) {
            return this.removeLast();
        }

        Node<T> target = this.get(position);
        Node<T> left = target.getPrevious();
        Node<T> right = target.getNext();

        left.setNext(right);
        right.setPrevious(left);

        this.decrementSize();
        return target;
    }

    public Node<T> removeFirst() {
        Node<T> target = this.getInitial();
        Node<T> right = target.getNext();

        right.setPrevious(null);
        this.setInitial(right);

        this.decrementSize();
        return target;
    }

    public Node<T> removeLast() {
        Node<T> target = this.getLast();
        Node<T> left = target.getPrevious();

        left.setNext(null);
        this.setLast(left);

        this.decrementSize();
        return target;
    }
}
