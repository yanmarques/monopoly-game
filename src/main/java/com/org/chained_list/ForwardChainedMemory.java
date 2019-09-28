package com.org.chained_list;

import com.org.ListPathWalker;
import com.org.Node;
import com.org.interfaces.ChainedList;

abstract public class ForwardChainedMemory<T> extends ListPathWalker<T> implements ChainedList<T> {
    public Node<T> get(int position) throws ArrayIndexOutOfBoundsException {
        this.forwardTo(position, true);
        return this.getCurrentNode();
    }
}
