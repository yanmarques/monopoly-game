package com.prj.commom;

import com.org.Node;
import com.org.stack.Stack;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DiceTest {
    @Test
    public void alwaysReturnPositiveIntegers() {
        int expected = 1;
        Dice.setRandGenerator(new StackedFakeRandom(0));
        assertEquals(Dice.nextPositiveInt(66), expected);
    }

    @Test
    public void playSumsTwoRandomIntegers() {
        int expected = 5;

        Stack<Integer> sumStack = new Stack<>();
        sumStack.insert(new Node<>(1));
        sumStack.insert(new Node<>(2));

        Dice.setRandGenerator(new StackedFakeRandom(sumStack));

        assertEquals(Dice.play(), expected);
    }

    @Test
    public void getLuckWhenGeneratorReturnSameNumbers() {
        int number = 1;
        Stack<Integer> sameNumbers = new Stack<>();
        sameNumbers.insert(new Node<>(number));
        sameNumbers.insert(new Node<>(number));

        Dice.setRandGenerator(new StackedFakeRandom(sameNumbers));
        assertTrue(Dice.tryLuck());
    }

    @Test
    public void getNoLuckWhenGeneratorReturnDifferentNumbers() {
        Stack<Integer> differentNumbers = new Stack<>();
        differentNumbers.insert(new Node<>(1));
        differentNumbers.insert(new Node<>(2));

        Dice.setRandGenerator(new StackedFakeRandom(differentNumbers));
        assertFalse(Dice.tryLuck());
    }
}
