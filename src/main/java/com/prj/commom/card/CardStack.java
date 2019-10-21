package com.prj.commom.card;

import com.org.Node;
import com.org.stack.Stack;
import com.prj.commom.Dice;
import com.prj.commom.Logger;
import com.prj.entity.PathBoard;
import com.prj.entity.Player;

public class CardStack {
    public static int SIZE = 60;

    private Stack<LuckyCard> stack;
    private PathBoard runningBoard;
    private LuckyCard[] cards;

    public CardStack(PathBoard board, LuckyCard[] cards) {
        this.stack = new Stack<>();
        this.runningBoard = board;
        this.setCards(cards);
    }

    public void setCards(LuckyCard[] cards) {
        this.cards = cards;
    }

    public void take(Player player) {
        if (this.stack.isEmpty()) {
            this.generateRandomCards();
        }

        Logger.showInfo(player, "taking a lucky card...");
        LuckyCard card = this.stack.remove().getValue();
        card.execute(player, this.runningBoard);
    }

    private void generateRandomCards() {
        LuckyCard nextCard;
        int i, randValue;

        i = 0;

        do {
            randValue = Dice.nextInt(this.cards.length);
            nextCard = this.cards[randValue];
            this.stack.insert(new Node<>(nextCard));
            i++;
        } while (i < SIZE);
    }
}
