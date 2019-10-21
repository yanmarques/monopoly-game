package com.prj.entity.bank;

import com.org.Node;
import com.org.chained_list.DoubleChainedList;
import com.org.interfaces.WalkIterator;
import com.prj.commom.Logger;
import com.prj.entity.building.BaseBuilding;
import com.prj.entity.building.Ground;
import com.prj.entity.Player;

import java.util.Iterator;

public class Registry {
    private Banker banker;

    public Registry(Banker banker) {
        this.banker = banker;
    }

    public void sell(Ground ground, Player buyer) throws IllegalArgumentException {
        Logger.showInfo(buyer, "is buying this ground: " + ground);
        Player owner = ground.getOwner();

        this.banker.transfer(buyer, owner, ground.getPrice());
        Logger.info("writing papers...");
        try {
            Logger.showInfo(ground, "owner is " + owner);
            Logger.info(String.valueOf(owner.getGrounds().getSize()));
            Logger.info(String.valueOf(owner.getGrounds().getCurrentPosition()));
            Logger.showInfo(owner, "posses this ground: " + ownerHasGround(ground));
            owner.unregister(ground);
            Logger.info("ground unregistered");
            buyer.register(ground);
            Logger.info("ground registered");
        } catch (RuntimeException exc) {
            Logger.info("bad boys...getting money back to buyer");
            this.banker.give(buyer, ground.getPrice()); // give money back
        }
        Logger.info("registration finished!");
    }

    public void build(Ground ground, BaseBuilding building) {
        if (ground.getBuilding() != null) {
            throw new RuntimeException("Ground already has a building.");
        }

        Logger.showInfo(ground, "making this building on his ground...");
        this.banker.transfer(ground.getOwner(), this.banker, building.getPrice());

        ground.build(building);
    }

    public void chargeTaxes(Player player) {
        double totalTax = 0;

        Iterator<Ground> iterator = player.groundIterator();
        while (iterator.hasNext()) {
            Ground ground = iterator.next();
            totalTax += ground.getTax();
        }

        if (totalTax > 0) {
            Logger.showInfo(player, "taxes coming, paying a total of: " + totalTax);
            banker.charge(player, totalTax);
        }
    }

    public void chargeRent(Player player, Ground ground) {
        long rentAmount = ground.getRentAmount();
        Player receiver = ground.getOwner();

        Logger.showInfo(ground, "renting by player: " + player);

        synchronized (this) {
            banker.charge(player, rentAmount);
            banker.give(receiver, rentAmount);
        }
    }

    private boolean ownerHasGround(Ground ground) {
        WalkIterator<Ground> iterator = ground.getOwner().groundIterator();
        boolean exists = false;
        while (iterator.hasNext()) {
            if (ground == iterator.next()) {
                iterator.stop();
                Logger.showInfo(ground, "found");
                exists = true;
            }
        }

        return exists;
    }
}
