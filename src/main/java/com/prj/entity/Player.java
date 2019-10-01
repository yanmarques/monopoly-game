package com.prj.entity;

import com.org.chained_list.SimpleChainedList;

public class Player {
    public static double INITIAL_BALANCE = 2500;

    private double balance;
    private String name;
    private SimpleChainedList<Ground> grounds;

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
}
