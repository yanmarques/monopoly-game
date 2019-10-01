package com.prj.commom;

import com.prj.entity.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    public void createPlayerWithDefaults() {
        String expectedName = "foo";

        Player player = this.dummyPlayer(expectedName);

        assertEquals(player.getName(), expectedName);
        assertEquals(player.getBalance(), Player.INITIAL_BALANCE);
        assertTrue(player.getGrounds().isEmpty());
        assertFalse(player.hasLiberationCards());
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

    @Test
    public void doNotUseLiberationCardsWhenNoCards() {
        Player player = this.dummyPlayer();
        assertThrows(IllegalAccessError.class, player::useLiberationCard);
    }

    @Test
    public void useReceivedLiberationCard() {
        Player player = this.dummyPlayer();

        player.receiveLiberationCard();
        assertTrue(player.hasLiberationCards());

        player.useLiberationCard();
        assertFalse(player.hasLiberationCards());
    }

    private Player dummyPlayer(String name) {
        return new Player(name);
    }

    private Player dummyPlayer() {
        return this.dummyPlayer("foo");
    }
}
