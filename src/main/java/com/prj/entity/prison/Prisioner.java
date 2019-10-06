package com.prj.entity.prison;

import com.org.Node;
import com.org.queue.Queue;
import com.prj.commom.Dice;
import com.prj.commom.Logger;
import com.prj.entity.Player;

import java.util.concurrent.Callable;

public class Prisioner {
    public static int MAXIMUM_ATTEMPTS = 3;

    private Player player;
    private Queue<Callable<Boolean>> remainingAttempts;

    public Prisioner(Player player) {
        this.player = player;
        this.fillAttempts();
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isFree() {
        return this.remainingAttempts.isEmpty();
    }

    public boolean attemptLiberationCard() {
        Logger.shPlayer(player, "trying get liberation card on luck...");
        try {
            return this.remainingAttempts.remove().getValue().call();
        } catch (Exception e) {
            return false;
        }
    }

    private void fillAttempts() {
        this.remainingAttempts = new Queue<>();

        Callable<Boolean> exitAttempt = Dice::tryLuck;

        for (int i = 0; i < MAXIMUM_ATTEMPTS; i++) {
            remainingAttempts.insert(new Node<>(exitAttempt));
        }
    }
}
