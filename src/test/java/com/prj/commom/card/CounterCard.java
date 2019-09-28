package com.prj.commom.card;

import com.prj.entity.PathBoard;
import com.prj.entity.Player;

public class CounterCard implements LuckyCard {
    int calls = 0;

    @Override
    public void execute(Player player, PathBoard board) {
        this.calls++;
    }
}
