package com.prj.commom.card;

import com.org.Node;
import com.org.stack.Stack;
import com.prj.TestCase;
import com.prj.commom.Dice;
import com.prj.commom.StackedFakeRandom;
import com.prj.entity.PathBoard;
import com.prj.entity.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardStackTest extends TestCase {
    @Test
    public void takeMultipleCardInOrder() {
        int expectedCalls = 1;
        CounterCard card1 = new CounterCard();
        CounterCard card2 = new CounterCard();

        LuckyCard[] cards = new LuckyCard[2];
        cards[0] = card1;
        cards[1] = card2;

        Stack<Integer> orderStack = new Stack<>();
        orderStack.insert(new Node<>(0));
        orderStack.insert(new Node<>(1));

        Dice.setRandGenerator(new StackedFakeRandom(orderStack));

        CardStack stack = this.dummyStack(cards);

        Player testPlayer = new Player("test");

        stack.take(testPlayer);
        assertEquals(expectedCalls, card1.calls);

        stack.take(testPlayer);
        assertEquals(expectedCalls, card2.calls);
    }

    @Test
    public void rebuildStackWhenEmpty() {
        int expectedCalls = 2;
        CounterCard uniqueCard = new CounterCard();

        LuckyCard[] cards = new LuckyCard[1];
        cards[0] = uniqueCard;

        CardStack stack = this.dummyStack(cards);

        Player testPlayer = new Player("test");

        stack.take(testPlayer);
        stack.take(testPlayer);

        assertEquals(expectedCalls, uniqueCard.calls);
    }

    private CardStack dummyStack(LuckyCard[] cards) {
        CardStack.SIZE = cards.length;
        return new CardStack(new PathBoard(), cards);
    }
}
