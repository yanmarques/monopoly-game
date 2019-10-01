package com.prj.entity.bank;

import com.org.Node;
import com.org.circle.DoubleCircledList;
import com.prj.entity.Ground;
import com.prj.entity.Player;

import java.util.ArrayList;

public class Banker extends Player {
    public static String NAME = "BANQUEIRO";

    private DoubleCircledList<Account> accounts;

    public Banker() {
        super(NAME);
        this.accounts = new DoubleCircledList<>();
        InternalAccount superAccount = new InternalAccount(this);
        this.accounts.insertLast(new Node<>(superAccount));
    }

    public void createAccount(Player player) {
        this.accounts.insertLast(new Node<>(new Account(player)));
    }

    public ArrayList<Player> getDefaultings() {
        ArrayList<Player> defaultings = new ArrayList<>();
        Account account;

        for (int i = 0; i < this.accounts.getSize(); i++) {
            account = this.accounts.get(i).getValue();

            if (account.isDefaulting()) {
                defaultings.add(account.getPlayer());
            }
        }

        return defaultings;
    }

    public void charge(Player player, double amount) throws IllegalArgumentException {
        assert amount > 0;
        Account account = this.findAccount(player);

        double playerBalance = account.getBalance();
        double credit = playerBalance - amount;

        if (playerBalance >= amount) {
            this.setAccountBalance(account, credit);
        } else {
            if (this.sellFirstBuilding(account)) {
                this.charge(player, amount);
            } else {
                this.setAccountBalance(account, credit);
            }
        }
    }

    public boolean hasAccount(Player player) {
        try {
            this.findAccount(player);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public void give(Player player, double amount) throws IllegalArgumentException {
        assert amount > 0;
        Account account = this.findAccount(player);
        double updatedBalance = account.getBalance() + amount;
        synchronized (this) {
            account.setBalance(updatedBalance);
        }
    }

    public double getBalance(Player player) {
        return this.findAccount(player).getBalance();
    }

    public void transfer(Player sender, Player receiver, double amount) {
        Account senderAccount = this.findAccount(sender);
        assert amount > 0 && senderAccount.getBalance() >= amount;

        synchronized (this) {
            this.charge(sender, amount);
            this.give(receiver, amount);
        }
    }

    public void sell(Ground ground, Player buyer) throws IllegalArgumentException {
        Player owner = ground.getOwner();

        this.transfer(buyer, owner, ground.getPrice());

        buyer.register(ground);
        owner.unregister(ground);
    }

    private boolean sellFirstBuilding(Account account) {
        Ground groundToSell = account.getPlayer().getFirstGround();

        if (groundToSell == null) {
            return false;
        }

        // sell the ground for the bank
        this.sell(groundToSell, this);
        return true;
    }

    private Account findAccount(Player player) throws IllegalArgumentException {
        Account account;
        for (int i = 0; i < this.accounts.getSize(); i++) {
            account = this.accounts.get(i).getValue();

            if (account.getPlayer() == player) {
                return account;
            }
        }

        throw new IllegalArgumentException("Any account found for ["+ player.getName() +"].");
    }

    private void setAccountBalance(Account account, double balance) {
        synchronized (this) {
            account.setBalance(balance);
        }
    }
}
