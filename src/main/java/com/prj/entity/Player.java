package com.prj.entity;

import com.org.chained_list.SimpleChainedList;

public class Player {
    public static double INITIAL_BALANCE = 2500;

    private double balance;
    private String name;
    private SimpleChainedList<Ground> grounds;
    private int liberationCards = 0;

    public Player(String name) {
        this.name = name;
        this.grounds = new SimpleChainedList<>();
        this.setBalance(INITIAL_BALANCE);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public SimpleChainedList getGrounds() {
        return this.grounds;
    }

    public boolean hasLiberationCards() {
        return this.liberationCards > 0;
    }

    public void useLiberationCard() throws IllegalAccessError {
        if (! this.hasLiberationCards()) {
            throw new IllegalAccessError("Player has no liberation cards.");
        }

        this.liberationCards--;
    }

    public void receiveLiberationCard() {
        this.liberationCards++;
    }
}
