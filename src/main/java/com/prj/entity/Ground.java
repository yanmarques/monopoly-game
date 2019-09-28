package com.prj.entity;

import com.prj.entity.building.BaseBuilding;

public class Ground {
    private long price;
    private Player owner;
    private BaseBuilding building;

    public Ground(long price, Player owner) {
        this.setPrice(price);
        this.setOwner(owner);
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void build(BaseBuilding building) {

    }
}
