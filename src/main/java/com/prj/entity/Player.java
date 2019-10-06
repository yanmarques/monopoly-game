package com.prj.entity;

import com.org.Node;
import com.org.chained_list.DoubleChainedList;
import com.prj.entity.building.Ground;

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

    public void register(Ground ground) {
        ground.setOwner(this);
        this.grounds.insertLast(new Node<>(ground));
    }

    public void unregister(Ground ground) {
        this.grounds.remove(ground);
    }

    public boolean hasGrounds() {
        return ! this.grounds.isEmpty();
    }

    public Ground getFirstGround() {
        if (this.hasGrounds()) {
            return this.grounds.getInitial().getValue();
        }

        return null;
    }
}
