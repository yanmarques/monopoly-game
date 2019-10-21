package com.prj.entity.prison;

import com.org.Node;
import com.org.chained_list.DoubleChainedList;
import com.org.circle.DoubleCircledList;
import com.prj.TestCase;
import com.prj.entity.Player;
import com.prj.entity.bank.Banker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JailTest extends TestCase {
    private Jail jail;

    @BeforeEach
    public void setUp() {
        this.jail = this.dummyJail();
    }

    @Test
    public void doNotArrestAnAlreadyArrestedPlayer() {
        Player badGuy = this.dummyPlayer();

        jail.arrest(badGuy);

        assertThrows(IllegalArgumentException.class, () -> jail.arrest(badGuy));
    }

    @Test
    public void liberationAddsPlayerCreditsAfterLuck() {
        int expectedCredits = 1;
        Player badGuy = this.dummyPlayer();
        jail.arrest(badGuy);

        this.getLuck();
        DoubleChainedList<Player> liberatedPlayers = jail.liberate();

        assertTrue(liberatedPlayers.isEmpty());
        assertTrue(jail.isJailed(badGuy));
        assertEquals(jail.getCardCredit(badGuy), expectedCredits);
    }

    @Test
    public void playerIsLiberatedAfterMaximumAttempts() {
        Banker bank = new Banker();
        Player badGuy = this.dummyPlayer();
        bank.createAccount(badGuy);
        Jail jail = new Jail(bank);

        DoubleChainedList<Player> liberatedPlayers;

        jail.arrest(badGuy);
        for (int i = 0; i < Prisioner.MAXIMUM_ATTEMPTS; i++) {
            liberatedPlayers = jail.liberate();
            assertTrue(liberatedPlayers.isEmpty());
        }

        Node<Player> goodGuyAgain = jail.liberate().getInitial();

        assertNotNull(goodGuyAgain);
        assertFalse(jail.isJailed(badGuy));
        assertEquals(goodGuyAgain.getValue(), badGuy);
    }

    @Test
    public void doNotGiveCreditsWhenPlayerAlreadyHasCredits() {
        Player badGuy = this.dummyPlayer();

        jail.arrest(badGuy);
        for (int i = 0; i < Prisioner.MAXIMUM_ATTEMPTS; i++) {
            this.getLuck();
            jail.liberate();
            assertEquals(jail.getCardCredit(badGuy), 1);
        }

        Node<Player> goodGuyAgain = jail.liberate().getInitial();

        assertNotNull(goodGuyAgain);
        assertEquals(jail.getCardCredit(badGuy), 0);
    }

    @Test
    public void allowsArrestingPlayersWithSameName() {
        Player p1 = this.dummyPlayer("myname");
        Player p2 = this.dummyPlayer("myname");

        jail.arrest(p1);
        jail.arrest(p2);

        assertTrue(jail.isJailed(p1));
        assertTrue(jail.isJailed(p2));
    }

    @Test
    public void playerStartsWithZeroedCreditCard() {
        Player player = this.dummyPlayer();

        jail.arrest(player);

        assertEquals(jail.getCardCredit(player), 0);
    }
}
