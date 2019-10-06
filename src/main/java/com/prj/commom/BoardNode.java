package com.prj.commom;

import com.prj.entity.building.Ground;

public class BoardNode {
    private boolean isStart = false;
    private Ground ground = null;

    public BoardNode(Ground ground) {
        this.ground = ground;
    }

    public BoardNode(boolean isStart) {
        this.isStart = isStart;
    }

    public boolean isStart() {
        return isStart;
    }

    public boolean isLuckyCard() {
        return ! this.isStart() && this.getGround() == null;
    }

    public Ground getGround() {
        return ground;
    }
}
