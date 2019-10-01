package com.prj.entity;

import com.prj.TestCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest extends TestCase {
    @Test
    public void createPlayerWithDefaults() {
        String expectedName = "foo";

        Player player = this.dummyPlayer(expectedName);

        assertEquals(player.getName(), expectedName);
        assertEquals(player.getBalance(), Player.INITIAL_BALANCE);
        assertTrue(player.getGrounds().isEmpty());
    }

    @Test
    public void createPlayerWithDifferentDefaultBalance() {
        double expectedBalance = 1;

        Player.INITIAL_BALANCE = expectedBalance;
        Player player = this.dummyPlayer();

        assertEquals(player.getBalance(), expectedBalance);
    }

    @Test
    public void setsNewValueAsBalance() {
        double expectedBalance = 1;

        Player player = this.dummyPlayer();
        player.setBalance(expectedBalance);

        assertEquals(player.getBalance(), expectedBalance);
    }

}
