package com.prj.entity.bank;

import com.org.Node;
import com.org.chained_list.DoubleChainedList;
import com.org.circle.DoubleCircledList;
import com.prj.entity.Ground;
import com.prj.entity.Player;

public class Banker extends Player {
    private DoubleCircledList<Account> accounts;

    public Banker() {
        super("BANQUEIRO");
        this.accounts = new DoubleCircledList<>();
        InternalAccount superAccount = new InternalAccount(this);
        this.accounts.insertLast(new Node<>(superAccount));
    }

    public void append(Player player) {
        this.accounts.insertLast(new Node<>(new Account(player)));
    }

    public void charge(Player player, double amount) throws IllegalArgumentException {
        Account account = this.findAccount(player);

        double playerBalance = account.getBalance();
        double credit = playerBalance - amount;

        if (playerBalance >= amount) {
            account.setBalance(credit);
        } else {
            if (this.sellFirstBuilding(account)) {
                this.charge(player, amount);
            } else {
                account.setBalance(credit);
            }
        }
    }

    public void give(Player player, double amount) throws IllegalArgumentException {
        Account account = this.findAccount(player);
        double updatedBalance = account.getBalance() + amount;
        account.setBalance(updatedBalance);
    }

    public void sell(Ground ground, Player buyer) throws IllegalArgumentException {
        Account buyerAccount = this.findAccount(buyer);
        Player owner = ground.getOwner();

        double sellPrice = ground.getPrice();
        assert buyerAccount.getBalance() >= sellPrice;

        this.charge(buyer, sellPrice);
        this.give(owner, sellPrice);

        ground.setOwner(buyer);
//        owner.getGrounds().remove(ground);
        buyer.getGrounds().insertLast(new Node<>(ground));
    }

    private boolean sellFirstBuilding(Account account) {
        DoubleChainedList<Ground> grounds = account.getPlayer().getGrounds();
        if (grounds.isEmpty()) {
            return false;
        }

        // sell the ground for the bank
        this.sell(grounds.removeFirst().getValue(), this);
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
}
