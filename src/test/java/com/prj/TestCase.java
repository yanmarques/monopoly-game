package com.prj;

import com.prj.entity.Player;
import com.prj.entity.prison.LiberationCard;

public class TestCase {
    public LiberationCard dummyCard() {
        return new LiberationCard(this.dummyPlayer());
    }

    public Player dummyPlayer(String name) {
        return new Player(name);
    }

    public Player dummyPlayer() {
        return this.dummyPlayer("foo");
    }
}
