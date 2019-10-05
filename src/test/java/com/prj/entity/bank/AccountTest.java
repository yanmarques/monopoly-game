package com.prj.entity.bank;

import com.prj.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest extends TestCase {
    private Account account;

    @BeforeEach
    public void setUp() {
        this.account = this.dummyAccount();
    }

    @Test
    public void createAccountWithDefaults() {
        assertEquals(account.getBalance(), Account.INITIAL_BALANCE);
        assertFalse(account.isDefaulting());
    }

    @Test
    public void createAccountWithDifferentDefaultBalance() {
        double expectedBalance = 1;

        Account.INITIAL_BALANCE = expectedBalance;
        account = this.dummyAccount();

        assertEquals(account.getBalance(), expectedBalance);
    }

    @Test
    public void setsNewValueAsBalance() {
        double expectedBalance = 1;
        account.setBalance(expectedBalance);
        assertEquals(account.getBalance(), expectedBalance);
    }

    @Test
    public void setDefaultingAccountWhenBalanceIsNegative() {
        account.setBalance(-1);
        assertTrue(account.isDefaulting());
    }
}
