package com.prj.entity;

import com.org.chained_list.DoubleChainedList;

public class Player {
    private String name;
    private DoubleChainedList<Ground> grounds;

    public Player(String name) {
        this.name = name;
        this.grounds = new DoubleChainedList<>();
    }

    public String getName() {
        return name;
    }

    public DoubleChainedList<Ground> getGrounds() {
        return this.grounds;
    }
}
