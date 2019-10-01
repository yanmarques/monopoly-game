package com.prj.entity.prison;

import com.prj.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrisionerTest extends TestCase {
    private Prisioner prisioner;

    @BeforeEach
    public void setUp() {
        this.prisioner = this.dummyPrisioner();
    }

    @Test
    public void createWithMaximumAttemptsFilled() {
        for (int i = 0; i < Prisioner.MAXIMUM_ATTEMPTS; i++) {
            prisioner.attemptLiberationCard();
        }

        assertTrue(prisioner.isFree());
    }

    @Test
    public void useAnotherAttemptsNumber() {
        Prisioner.MAXIMUM_ATTEMPTS = 1;
        prisioner = this.dummyPrisioner();

        prisioner.attemptLiberationCard();

        assertTrue(prisioner.isFree());
    }

    @Test
    public void attemptToLiberationSuccededOnLuck() {
        this.getNoLuck();
        assertFalse(prisioner.attemptLiberationCard());

        this.getLuck();
        assertTrue(prisioner.attemptLiberationCard());
    }
}
