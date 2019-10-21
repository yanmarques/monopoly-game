package com.prj.entity.bank;

import com.org.chained_list.DoubleChainedList;
import com.prj.TestCase;
import com.prj.entity.building.Ground;
import com.prj.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankerTest extends TestCase {
    private Banker banker;

    @BeforeEach
    public void setUp() {
        this.banker = this.dummyBanker();
    }

    @Test
    public void createWithDefaultSettings() {
        assertEquals(Banker.NAME, banker.getName());
        assertTrue(banker.hasAccount(banker));
    }

    @Test
    public void givesAnAmountOfMoneyToPlayer() {
        double amountGiven = 10;
        double expectedBalance = Account.INITIAL_BALANCE + amountGiven;

        Player player = this.dummyPlayer();
        banker.createAccount(player);
        banker.give(player, amountGiven);

        assertEquals(banker.getBalance(player), expectedBalance);
    }

    @Test
    public void doNotGiveNonPositiveValues() {
        double amountGiven = 0;

        Player player = this.dummyPlayer();
        banker.createAccount(player);

        assertThrows(AssertionError.class, () -> banker.give(player, amountGiven));
    }

    @Test
    public void markAccountAsDefaultingWhenPlayerHasNotMoney() {
        Player player = this.dummyPlayer();
        this.assertDefaultingAccount(player, Account.INITIAL_BALANCE);
    }

    @Test
    public void markAccountAsDefaultingWhenPlayerHasNoMoreGroundToSell() {
        Ground g1 = this.dummyGround(1);
        Ground g2 = this.dummyGround(2);

        double chargeAmount = Account.INITIAL_BALANCE + g1.getPrice() + g2.getPrice();

        Player player = this.dummyPlayer();
        player.register(g1);
        player.register(g2);

        this.assertDefaultingAccount(player, chargeAmount);

        assertFalse(player.hasGrounds());
        assertEquals(g1.getOwner(), banker);
        assertEquals(g2.getOwner(), banker);
    }

    @Test
    public void sellsFirstGroundAndDoNotBecameDefaulting() {
        Ground g1 = this.dummyGround(1);
        Ground g2 = this.dummyGround(1000);

        double chargeAmount = Account.INITIAL_BALANCE + g1.getPrice();

        Player player = this.dummyPlayer();
        player.register(g1);
        player.register(g2);

        banker.createAccount(player);
        banker.charge(player, chargeAmount);

        DoubleChainedList<Player> defaultings = banker.getDefaultings();
        assertTrue(defaultings.isEmpty());

        assertEquals(banker.getBalance(player), 0);
        assertEquals(player.getFirstGround(), g2);
        assertEquals(g2.getOwner(), player);
    }

    @Test
    public void doNotTransferNonPositiveValues() {
        Player p1 = this.dummyPlayer();
        Player p2 = this.dummyPlayer();

        banker.createAccount(p1);
        banker.createAccount(p2);

        assertThrows(IllegalArgumentException.class, () -> banker.transfer(p1, p2, 0));
    }

    @Test
    public void doNotTransferWhenSenderDoesNotHaveEnoughMoney() {
        Player p1 = this.dummyPlayer();
        Player p2 = this.dummyPlayer();

        banker.createAccount(p1);
        banker.createAccount(p2);

        // remove all his money
        banker.charge(p1, banker.getBalance(p1));

        assertThrows(IllegalArgumentException.class, () -> banker.transfer(p1, p2, 1));
    }

    private void assertDefaultingAccount(Player player, double maxAmount) {
        banker.createAccount(player);
        banker.charge(player, maxAmount + 1);

        assertEquals(banker.getBalance(player), -1);

        DoubleChainedList<Player> defaultings = banker.getDefaultings();
        assertEquals(defaultings.getSize(), 1);
        assertEquals(defaultings.get(0).getValue(), player);
    }
}
