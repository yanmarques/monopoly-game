package com.prj.entity.building;

import com.prj.entity.Player;

public class Ground extends BaseBuilding {
    private Player owner;
    private BaseBuilding building;

    public Ground(long price, int rentAmount, Player owner) {
        super(price, rentAmount);
        owner.register(this);
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public BaseBuilding getBuilding() {
        return this.building;
    }

    public void build(BaseBuilding building) {
        this.building = building;
        this.setPrice(this.getPrice() + building.getPrice());
        this.setRentAmount(this.getRentAmount() + building.getRentAmount());
    }

    @Override
    public String toString() {
        return "Ground [price=R$" + this.getPrice() + ",building=" + this.getBuilding() + "]";
    }
}
