package com.prj.entity.prison;

import com.prj.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LiberationCardTest extends TestCase {
    private LiberationCard card;

    @BeforeEach
    public void setUp() {
        this.card = this.dummyCard();
    }

    @Test
    public void doNotUseLiberationCardsWhenNoCards() {
        assertThrows(IllegalAccessError.class, card::useCard);
    }

    @Test
    public void useReceivedLiberationCard() {
        card.receiveCredit();
        assertTrue(card.hasCredits());

        card.useCard();
        assertFalse(card.hasCredits());
    }
}
