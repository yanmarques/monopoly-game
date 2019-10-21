package com.prj.entity.bank;

import com.org.Node;
import com.org.chained_list.DoubleChainedList;
import com.prj.commom.Logger;
import com.prj.entity.building.Ground;
import com.prj.entity.Player;

import java.util.HashMap;

public class Banker extends Player {
    public static String NAME = "BANQUEIRO";

    private HashMap<Player, Account> accounts;
    private Registry registry;

    public Banker() {
        super(NAME);
        this.accounts = new HashMap<>();
        this.registry = new Registry(this);

        InternalAccount superAccount = new InternalAccount(this);
        this.accounts.put(this, superAccount);
    }

    public Registry getRegistry() {
        return registry;
    }

    public void createAccount(Player player) throws IllegalArgumentException {
        if (hasAccount(player)) {
            throw new IllegalArgumentException("Player "+ player.getName() +"already has an account.");
        }

        Logger.showInfo(player, "creating account...");
        this.accounts.put(player, new Account(player));
    }

    public DoubleChainedList<Player> getDefaultings() {
        DoubleChainedList<Player> defaultings = new DoubleChainedList<>();

        for (Account account : this.accounts.values()) {
            if (account.isDefaulting()) {
                defaultings.insertLast(new Node<>(account.getPlayer()));
            }
        }

        return defaultings;
    }

    public void charge(Player player, double amount) throws IllegalArgumentException {
        assert amount > 0;
        Account account = this.findAccountOrFail(player);

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
            this.findAccountOrFail(player);
        } catch (IllegalArgumentException e) {
            return false;
        }

        return true;
    }

    public void give(Player player, double amount) throws IllegalArgumentException {
        assert amount > 0;
        Account account = this.findAccountOrFail(player);
        double updatedBalance = account.getBalance() + amount;
        this.setAccountBalance(account, updatedBalance);
    }

    public double getBalance(Player player) {
        return this.findAccountOrFail(player).getBalance();
    }

    public void transfer(Player sender, Player receiver, double amount) throws IllegalArgumentException {
        Account senderAccount = this.findAccountOrFail(sender);
        if (!(amount > 0 && senderAccount.getBalance() >= amount)) {
            throw new IllegalArgumentException("Transfer information is not valid");
        }

        Logger.info("starting transfer from " + sender.getName() + " to " + receiver.getName() + " with R$" + amount);
        synchronized (this) {
            this.charge(sender, amount);
            this.give(receiver, amount);
        }
        Logger.info("finished transfer!");
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

    private Account findAccountOrFail(Player player) throws IllegalArgumentException {
        Account account = this.accounts.get(player);
        if (account == null)
            throw new IllegalArgumentException("Any account found for ["+ player.getName() +"].");
        return account;
    }

    private void setAccountBalance(Account account, double balance) {
        double oldBalance = account.getBalance();
        synchronized (this) {
            account.setBalance(balance);
        }
        Logger.showInfo(account.getPlayer(), "old balance: R$" + oldBalance);
        Logger.showInfo(account.getPlayer(), "new balance: R$" + account.getBalance());
    }
}
