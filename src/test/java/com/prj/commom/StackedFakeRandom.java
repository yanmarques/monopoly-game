package com.prj.commom;

import com.org.Node;
import com.org.stack.Stack;

import java.util.Random;

public class StackedFakeRandom extends Random {
    private Stack<Integer> results;

    public StackedFakeRandom(int staticResult) {
        this.results = new Stack<>();
        this.results.insert(new Node<>(staticResult));
    }

    public StackedFakeRandom(Stack<Integer> results) {
        this.results = results;
    }

    @Override
    public int nextInt(int bound) {
        return this.results.remove().getValue();
    }
}
