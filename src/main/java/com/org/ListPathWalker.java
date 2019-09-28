package com.org;

import com.org.interfaces.PathWalker;

abstract public class ListPathWalker<T> extends MemoryList<T> implements PathWalker<T> {
    private Node<T> currentNode = null;
    private int currentPosition = 0;

    @Override
    public int getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public Node<T> getCurrentNode() {
        return currentNode;
    }

    @Override
    public void forwardTo(int position, boolean checkPositive) {
        this.ensurePositionExists(position, checkPositive);

        this.resetNode();
        while (this.getCurrentPosition() < position) {
            this.forwardOperation();
        }
    }

    @Override
    public void backwardTo(int position, boolean checkPositive) {
        this.ensurePositionExists(position, checkPositive);

        this.resetNode();
        while (this.getCurrentPosition() > position) {
            this.backwardOperation();
        }
    }

    protected void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    protected void resetNode() {
        this.currentNode = this.getInitial();
        this.setCurrentPosition(0);
    }

    protected void forwardOperation() {
        this.currentNode = this.getCurrentNode().getNext();
        this.currentPosition++;
    }

    protected void backwardOperation() {
        this.currentNode = this.getCurrentNode().getPrevious();
        this.currentPosition--;
    }
}
