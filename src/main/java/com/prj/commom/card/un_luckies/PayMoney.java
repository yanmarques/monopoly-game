package com.prj.commom.card.un_luckies;

import com.prj.entity.PathBoard;
import com.prj.commom.card.LuckyCard;
import com.prj.entity.Player;

public class PayMoney implements LuckyCard {
    private double moneyToPay = 1000;

    @Override
    public void execute(Player player, PathBoard board) {
        // da uma grana para o jogador
    }
}
