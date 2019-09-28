package com.org;

public class Node <T> {
    private T value;
    private Node previous, next;

    public Node(T value) {
        this.value = value;
    }

    public Node(T value, Node previous, Node next) {
        this.value = value;
        this.previous = previous;
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node<T> clone() {
        return new Node<>(getValue(), getPrevious(), getNext());
    }
}
