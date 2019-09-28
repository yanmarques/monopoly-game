package com.org.stack;

import com.org.Node;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
    private Stack<Integer> stack;

    @BeforeEach
    public void setUp() {
        stack = new Stack<>();
    }

    @AfterEach
    public void teardown() {
        stack.clear();
        stack = null;
    }

    @Test
    public void insertsInCurrentOrder() {
        final Node<Integer> expectedAsLast = new Node<>(1);
        final Node<Integer> expectedAsFirst = new Node<>(2);

        stack.insert(expectedAsLast);
        stack.insert(expectedAsFirst);

        assertEquals(stack.remove(), expectedAsFirst);
        assertEquals(stack.remove(), expectedAsLast);
    }

    @Test
    public void keepCountingSize() {
        stack.insert(new Node<>(1));
        assertEquals(stack.getSize(), 1);

        stack.remove();
        assertEquals(stack.getSize(), 0);
    }

    @Test
    public void forgetsPreviousNodeOnRemove() {
        stack.insert(new Node<>(1));
        stack.insert(new Node<>(2));

        Node<Integer> actual = stack.remove();
        assertNull(actual.getPrevious());
    }

    @Test
    public void doNotforgetPreviousNodeOnRemove() {
        Node<Integer> expected = new Node<>(1);

        stack.insert(expected);
        stack.insert(new Node<>(2));

        Node<Integer> actual = stack.remove(false);
        assertEquals(actual.getPrevious(), expected);
    }

//    @Test
//    public void spyNode<Integer>OnTopWithoutTouchingIt() {
//        int expected = 1;
//
//        stack.insert(new Node<>(expected));
//        Node<Integer> top = stack.getInitial();
//        top.setValue(2);
//
//        assertEquals(stack.getInitial().getValue(), expected);
//    }

    @Test
    public void stackToArrayReversingInsertOrder() {
        Node<Integer> firstNode = new Node<>(1);
        Node<Integer> lastNode = new Node<>(2);

        stack.insert(firstNode);
        stack.insert(lastNode);

        ArrayList<Node<Integer>> actual = stack.toArray();
        assertEquals(actual.get(0), lastNode);
        assertEquals(actual.get(1), firstNode);
    }

    @Test
    public void cleanStackPointerAndSize() {
        stack.insert(new Node<>(1));
        stack.clear();

        assertEquals(stack.getSize(), 0);
        assertNull(stack.getInitial());
    }

    @Test
    public void blockAttemptToRemoveWhenEmpty() {
        assertThrows(IllegalAccessError.class, () -> stack.remove(false));
    }
}
