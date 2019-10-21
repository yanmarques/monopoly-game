package com.prj.commom.card.un_luckies;

import com.prj.commom.Dice;
import com.prj.commom.Logger;
import com.prj.commom.card.LuckyCard;
import com.prj.entity.PathBoard;
import com.prj.entity.Player;
import com.prj.entity.bank.Banker;
import com.prj.entity.building.Ground;

public class Donation implements LuckyCard {
    @Override
    public void execute(Player player, PathBoard board) {
        Banker bank = board.getBanker();
        if (Dice.tryLuck()) {
            bank.charge(player, 500);
        } else if (player.hasGrounds()) {
            Ground ground = player.getFirstGround();
            double donation = ground.getPrice();

            bank.getRegistry().sell(ground, bank);
            bank.charge(player, donation);
            Logger.showInfo(player, "donate a house to bank, the money will go to poor people");
        } else {
            board.movePlayer(player, -10);
        }
    }
}
