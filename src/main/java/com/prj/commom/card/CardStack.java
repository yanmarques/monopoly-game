package com.prj.commom.card;

import com.org.Node;
import com.org.stack.Stack;
import com.prj.commom.Dice;
import com.prj.entity.PathBoard;
import com.prj.commom.card.un_luckies.PayMoney;
import com.prj.entity.Player;

public class CardStack {
    final public int SIZE = 60;

    private Stack<LuckyCard> stack;
    private PathBoard runningBoard;

    public CardStack(PathBoard board) {
        this.stack = new Stack<>();
        this.runningBoard = board;
        this.generateRandomNotices();
    }

    public void take(Player player) {
        if (this.stack.isEmpty()) {
            this.generateRandomNotices();
        }

        LuckyCard card = this.stack.remove().getValue();
        card.execute(player, this.runningBoard);
    }

    private void generateRandomNotices() {
        LuckyCard nextCard = null;
        int i, randValue;
        i = 0;

        LuckyCard[] cards = new LuckyCard[3];
        cards[0] = new PayMoney();
//        cards[1] = outra;
//        cards[2] = classe;

        do {
            randValue = Dice.nextInt(3);
            this.stack.insert(new Node<>(cards[randValue]));
            i++;
        }while(i < this.SIZE);
    }
}
