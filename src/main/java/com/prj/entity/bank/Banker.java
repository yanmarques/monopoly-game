package com.prj.entity.bank;

import com.org.Node;
import com.org.chained_list.DoubleChainedList;
import com.prj.entity.building.Ground;
import com.prj.entity.Player;

public class Banker extends Player {
    public static String NAME = "BANQUEIRO";

    private DoubleChainedList<Account> accounts;
    private Registry registry;

    public Banker() {
        super(NAME);
        this.accounts = new DoubleChainedList<>();
        InternalAccount superAccount = new InternalAccount(this);
        this.accounts.insertLast(new Node<>(superAccount));
        this.registry = new Registry(this);
    }

    public Registry getRegistry() {
        return registry;
    }

    public void createAccount(Player player) {
        this.accounts.insertLast(new Node<>(new Account(player)));
    }

    public DoubleChainedList<Player> getDefaultings() {
        DoubleChainedList<Player> defaultings = new DoubleChainedList<>();

        for (Account account : this.accounts) {
            if (account.isDefaulting()) {
                defaultings.insertLast(new Node<>(account.getPlayer()));
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
            if (this.tryNegotiateFunds(account)) {
                this.charge(player, amount);
            } else {
                this.setAccountBalance(account, credit);
            }
        }
    }

    public boolean hasAccount(Player player) {
        try {
            this.findAccount(player);
        } catch (IllegalArgumentException e) {
            return false;
        }

        return true;
    }

    public void give(Player player, double amount) throws IllegalArgumentException {
        assert amount > 0;
        Account account = this.findAccount(player);
        double updatedBalance = account.getBalance() + amount;
        this.setAccountBalance(account, updatedBalance);
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

    private boolean tryNegotiateFunds(Account account) {
        Ground groundToSell = account.getPlayer().getFirstGround();

        if (groundToSell == null) {
            return false;
        }

        // sell the ground for the bank
        this.getRegistry().sell(groundToSell, this);
        return true;
    }

    private Account findAccount(Player player) throws IllegalArgumentException {
        for (Account account : this.accounts) {
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
