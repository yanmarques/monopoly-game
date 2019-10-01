package com.prj.entity.prison;

import com.org.Node;
import com.org.stack.Stack;
import com.prj.commom.Dice;
import com.prj.commom.StackedFakeRandom;
import com.prj.entity.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrisionerTest {
    @Test
    public void createWithMaximumAttemptsFilled() {
        Prisioner prisioner = this.dummyPrisioner();

        for (int i = 0; i < Prisioner.MAXIMUM_ATTEMPTS; i++) {
            prisioner.attemptLiberationCard();
        }

        assertTrue(prisioner.isFree());
    }

    @Test
    public void useAnotherAttemptsNumber() {
        Prisioner.MAXIMUM_ATTEMPTS = 1;
        Prisioner prisioner = this.dummyPrisioner();

        prisioner.attemptLiberationCard();

        assertTrue(prisioner.isFree());
    }

    @Test
    public void attemptToLiberationSuccededOnLuck() {
        Prisioner prisioner = this.dummyPrisioner();

        Stack<Integer> fakeResults = new Stack<>();
        fakeResults.insert(new Node<>(1));
        fakeResults.insert(new Node<>(1));

        fakeResults.insert(new Node<>(1));
        fakeResults.insert(new Node<>(2));

        Dice.setRandGenerator(new StackedFakeRandom(fakeResults));

        assertFalse(prisioner.attemptLiberationCard());
        assertTrue(prisioner.attemptLiberationCard());
    }

    private Prisioner dummyPrisioner() {
        return new Prisioner(new Player("foo"));
    }
}
