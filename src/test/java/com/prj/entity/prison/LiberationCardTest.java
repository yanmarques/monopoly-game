package com.prj.entity.prison;

import com.prj.TestCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LiberationCardTest extends TestCase {
    @Test
    public void doNotUseLiberationCardsWhenNoCards() {
        LiberationCard card = this.dummyCard();
        assertThrows(IllegalAccessError.class, card::useCard);
    }

    @Test
    public void useReceivedLiberationCard() {
        LiberationCard card = this.dummyCard();

        card.receiveCredit();
        assertTrue(card.hasCredits());

        card.useCard();
        assertFalse(card.hasCredits());
    }
}
