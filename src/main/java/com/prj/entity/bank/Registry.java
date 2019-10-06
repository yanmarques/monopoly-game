package com.prj.entity.bank;

import com.prj.entity.building.Ground;
import com.prj.entity.Player;

public class Registry {
    private Banker banker;

    public Registry(Banker banker) {
        this.banker = banker;
    }

    public void sell(Ground ground, Player buyer) throws IllegalArgumentException {
        Player owner = ground.getOwner();

        this.banker.transfer(buyer, owner, ground.getPrice());

        owner.unregister(ground);
        buyer.register(ground);
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
