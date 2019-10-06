package com.prj.entity.bank;

import com.prj.commom.Logger;
import com.prj.entity.building.BaseBuilding;
import com.prj.entity.building.Ground;
import com.prj.entity.Player;

public class Registry {
    private Banker banker;

    public Registry(Banker banker) {
        this.banker = banker;
    }

    public void sell(Ground ground, Player buyer) throws IllegalArgumentException {
        Logger.shPlayer(buyer, "is buying a ground");
        Player owner = ground.getOwner();

        this.banker.transfer(buyer, owner, ground.getPrice());

        owner.unregister(ground);
        buyer.register(ground);
    }

    public void build(Ground ground, BaseBuilding building) {
        if (ground.getBuilding() != null) {
            throw new RuntimeException("Ground already has a building.");
        }

        Logger.shPlayer(ground.getOwner(), "making a building on his ground...");
        this.banker.transfer(ground.getOwner(), this.banker, building.getPrice());

        ground.build(building);
    }

    public void chargeTaxes(Player player) {
        double totalTax = 0;

        while (player.hasGrounds()) {
            Ground ground = player.getFirstGround();
            totalTax += ground.getTax();
        }

        banker.charge(player, totalTax);
    }

    public void chargeRent(Player player, Ground ground) {
        long rentAmount = ground.getRentAmount();
        Player receiver = ground.getOwner();

        banker.transfer(player, receiver, rentAmount);
    }
}
