package com.prj.entity;

import com.org.chained_list.SimpleChainedList;

public class Player {
    final public int INITIAL_BALANCE = 2500;

    private double balance;
    private String name;
    private SimpleChainedList<Ground> grounds;

    public Player(String name) {
        this.name = name;
        this.balance = this.INITIAL_BALANCE;
        this.grounds = new SimpleChainedList<>();
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
}
