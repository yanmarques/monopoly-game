package com.prj.entity;

import com.org.Node;
import com.org.chained_list.SimpleChainedList;
import com.org.circle.DoubleCircledList;
import com.org.interfaces.WalkIterator;
import com.prj.entity.building.Ground;


public class Player {
    private String name;
    private SimpleChainedList<Ground> grounds;

    public Player(String name) {
        this.name = name;
        this.grounds = new SimpleChainedList<>();
    }

    public String getName() {
        return name;
    }

    public void register(Ground ground) {
        ground.setOwner(this);
        this.getGrounds().insert(this.getGrounds().getSize(), new Node<>(ground));
    }

    public void unregister(Ground ground) {
        this.grounds.remove(ground);
    }

    public boolean hasGrounds() {
        return ! this.grounds.isEmpty();
    }

    public WalkIterator<Ground> groundIterator() {
        return this.grounds.iterator();
    }

    public SimpleChainedList<Ground> getGrounds() {
        return this.grounds;
    }

    public Ground getFirstGround() {
        if (this.hasGrounds()) {
            return this.grounds.getInitial().getValue();
        }

        return null;
    }

    @Override
    public String toString() {
        return "Player [name=" + this.getName() + ",grounds=" + this.grounds.getSize() + "]";
    }
}
