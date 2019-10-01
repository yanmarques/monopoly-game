package com.prj.commom;

import com.prj.TestCase;
import com.prj.entity.Ground;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardNodeTest extends TestCase {
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
        Ground ground = this.dummyGround(666);
        BoardNode node = new BoardNode(ground);
        assertFalse(node.isLuckyCard());
        assertFalse(node.isStart());
        assertEquals(node.getGround(), ground);
    }
}
