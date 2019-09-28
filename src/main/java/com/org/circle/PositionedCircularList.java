package com.org.circle;

import com.org.Node;

public class PositionedCircularList<T> extends DoubleCircledList<T> {
    @Override
    public Node<T> get(int position) throws ArrayIndexOutOfBoundsException {
        int backwardLength = (this.getSize() - position) + this.getCurrentPosition();

        if (position > this.getCurrentPosition() && backwardLength > position) {
            this.forwardTo(position, false);
            return this.getCurrentNode();
        }

        this.backwardTo(position, false);
        return this.getCurrentNode();
    }

    public Node<T> forward() {
        return this.get(this.getCurrentPosition() + 1);
    }

    public Node<T> backward() {
        return this.get(this.getCurrentPosition() - 1);
    }

    @Override
    protected void resetNode() {
        if (this.getCurrentNode() == null) {
            super.resetNode();
        }
    }

    @Override
    protected void forwardOperation() {
        if (this.getCurrentPosition() >= this.getSize()) {
            this.setCurrentPosition(0);
        }

        super.forwardOperation();
    }

    @Override
    protected void backwardOperation() {
        if (this.getCurrentPosition() == -1) {
            this.setCurrentPosition(this.getSize() - 1);
        }

        super.backwardOperation();
    }
}
