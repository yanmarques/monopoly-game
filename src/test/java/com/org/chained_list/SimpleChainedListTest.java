package com.org.chained_list;

import com.org.Node;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleChainedListTest {
    private SimpleChainedList<Integer> list;

    @BeforeEach
    public void setUp() {
        this.list = new SimpleChainedList<>();
    }

    @AfterEach
    public void teardown() {
        this.list.clear();
        this.list = null;
    }

    @Test
    public void insertsOnFirstPosition() throws ArrayIndexOutOfBoundsException {
        Node<Integer> first = new Node<>(1);
        this.list.insert(0, first);
        assertEquals(this.list.get(0), first);
    }

    @Test
    public void insertsMany() throws ArrayIndexOutOfBoundsException {
        Node<Integer> first = new Node<>(1);
        Node<Integer> middle = new Node<>(2);
        Node<Integer> last = new Node<>(3);

        this.list.insert(0, first);
        this.list.insert(1, middle);
        this.list.insert(2, last);

        assertEquals(this.list.get(1), middle);
        assertEquals(this.list.get(2), last);
    }

    @Test
    public void insertsBetweenExistentPositions() throws ArrayIndexOutOfBoundsException {
        Node<Integer> first = new Node<>(1);
        Node<Integer> middle = new Node<>(2);
        Node<Integer> last = new Node<>(3);
        Node<Integer> rebel = new Node<>(4);

        this.list.insert(0, first);
        this.list.insert(1, middle);
        this.list.insert(2, last);

        // Inserts the Node<Integer> in the middle position
        this.list.insert(1, rebel);

        assertEquals(this.list.getSize(), 4);
        assertEquals(this.list.get(1), rebel);
        assertEquals(this.list.get(2), middle);
    }

    @Test
    public void insertsInFirstPositionWithNonEmptyList() throws ArrayIndexOutOfBoundsException {
        Node<Integer> first = new Node<>(1);
        Node<Integer> last = new Node<>(2);
        Node<Integer> rebel = new Node<>(3);

        this.list.insert(0, first);
        this.list.insert(1, last);

        // Inserts the Node<Integer> in the middle position
        this.list.insert(0, rebel);

        assertEquals(this.list.getSize(), 3);
        assertEquals(this.list.get(0), rebel);
        assertEquals(this.list.get(1), first);
    }

    @Test
    public void removeMiddlePosition() throws ArrayIndexOutOfBoundsException {
        Node<Integer> first = new Node<>(1);
        Node<Integer> middle = new Node<>(2);
        Node<Integer> last = new Node<>(3);

        this.list.insert(0, first);
        this.list.insert(1, middle);
        this.list.insert(2, last);

        assertEquals(this.list.remove(1), middle);
        assertEquals(this.list.get(0), this.list.getInitial());
        assertEquals(this.list.get(1), last);
    }

    @Test
    public void removeLastPosition() throws ArrayIndexOutOfBoundsException {
        Node<Integer> first = new Node<>(1);
        Node<Integer> middle = new Node<>(2);
        Node<Integer> last = new Node<>(3);

        this.list.insert(0, first);
        this.list.insert(1, middle);
        this.list.insert(2, last);

        assertEquals(this.list.remove(2), last);
        assertEquals(this.list.get(0), this.list.getInitial());
        assertEquals(this.list.get(1), middle);
    }

    @Test
    public void removeFirstPosition() throws ArrayIndexOutOfBoundsException {
        Node<Integer> first = new Node<>(1);
        Node<Integer> middle = new Node<>(2);
        Node<Integer> last = new Node<>(3);

        this.list.insert(0, first);
        this.list.insert(1, middle);
        this.list.insert(2, last);

        assertEquals(this.list.remove(0), first);
        assertEquals(this.list.get(0), this.list.getInitial());
        assertEquals(this.list.get(1), last);
    }

    @Test
    public void countListLength() throws ArrayIndexOutOfBoundsException {
        Node<Integer> first = new Node<>(1);
        Node<Integer> middle = new Node<>(2);
        Node<Integer> last = new Node<>(3);

        this.list.insert(0, first);
        this.list.insert(1, middle);
        this.list.insert(2, last);

        assertEquals(this.list.getSize(), 3);
    }

    @Test
    public void blockInsertWithNegativePosition() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> this.list.insert(-1, new Node<>(1)));
    }

    @Test
    public void blockInsertWithOutOfListBounds() {
        this.list.insert(0, new Node<>(1));
        int invalidPosition = this.list.getSize() + 1;

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> this.list.insert(invalidPosition, new Node<>(1)));
    }
}
