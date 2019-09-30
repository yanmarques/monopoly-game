package com.prj.commom;

import com.prj.entity.Ground;
import com.prj.entity.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardNodeTest {
    @Test
    public void createAnInitialNode() {
        BoardNode node = new BoardNode(true);
        assertTrue(node.isStart());
        assertFalse(node.isLuckyCard());
    }

    @Test
    public void createALuckyCardNode() {
        BoardNode node = new BoardNode(false);
        assertTrue(node.isLuckyCard());
        assertFalse(node.isStart());
    }

    @Test
    public void createAGroundNode() {
        Ground ground = new Ground(666, new Player("test"));
        BoardNode node = new BoardNode(ground);
        assertFalse(node.isLuckyCard());
        assertFalse(node.isStart());
    }
}
