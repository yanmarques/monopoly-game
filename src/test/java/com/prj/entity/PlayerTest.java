package com.prj.entity;

import com.prj.TestCase;
import com.prj.entity.building.Ground;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest extends TestCase {
    private Player player;

    @BeforeEach
    public void setUp() {
        this.player = this.dummyPlayer();
    }

    @Test
    public void createPlayerWithDefaults() {
        String expectedName = "baz";
        player = this.dummyPlayer(expectedName);

        assertEquals(player.getName(), expectedName);
        assertFalse(player.hasGrounds());
        assertNull(player.getFirstGround());
    }

    @Test
    public void registerAGroundWithCurrentOwner() {
        Ground ground = this.dummyGround(1);
        player.register(ground);

        assertEquals(player.getFirstGround(), ground);
        assertEquals(ground.getOwner(), player);
    }

    @Test
    public void unregisterAGround() {
        Ground ground = this.dummyGround(1);
        player.register(ground);
        player.unregister(ground);

        assertFalse(player.hasGrounds());
    }
}
