package com.prj.entity.prison;

import com.prj.commom.Logger;
import com.prj.entity.Player;

public class LiberationCard {
    private int credits = 0;
    private Player player;

    public LiberationCard(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getCredits() {
        return credits;
    }

    public boolean hasCredits() {
        return this.getCredits() > 0;
    }

    public void useCard() throws IllegalAccessError {
        if (! this.hasCredits()) {
            throw new IllegalAccessError("Player has no liberation cards.");
        }

        Logger.showInfo(player, "used a liberation card credit");
        this.credits--;
    }

    public void receiveCredit() {
        this.credits++;
    }
}
