package com.prj.entity;

import com.prj.TestCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest extends TestCase {
    @Test
    public void createPlayerWithDefaults() {
        String expectedName = "baz";
        Player player = this.dummyPlayer(expectedName);

        assertEquals(player.getName(), expectedName);
        assertTrue(player.getGrounds().isEmpty());
    }
}
