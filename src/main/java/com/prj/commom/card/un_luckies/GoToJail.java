package com.prj.commom.card.un_luckies;

import com.prj.commom.card.LuckyCard;
import com.prj.entity.PathBoard;
import com.prj.entity.Player;

public class GoToJail implements LuckyCard {
    @Override
    public void execute(Player player, PathBoard board) {
        board.putInJail(player);
    }
}
