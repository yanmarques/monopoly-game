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

        do {
            randValue = Dice.nextPositiveInt(3);
            switch (randValue) {
                case 1:
                    //valor a pagar
                    nextCard = new PayMoney();
                    break;
                case 2:
                    //valor a cobrar
                    break;
                case 3:
                    //prisao
                    break;
                 default:
                     throw new RuntimeException("Invalid value for cards!");
            }
            this.stack.insert(new Node<>(nextCard));
            i++;
        }while(i < this.SIZE);
    }
}
