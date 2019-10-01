package com.prj.entity.bank;

import com.prj.entity.Player;

public class Account {
    public static double INITIAL_BALANCE = 2500;

    private Player player;
    private double balance;
    private boolean isDefaulting = false;

    public Account(Player player) {
        this.player = player;
        this.setBalance(INITIAL_BALANCE);
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isDefaulting() {
        return this.isDefaulting;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            this.isDefaulting = true;
        }

        this.balance = balance;
    }
}
