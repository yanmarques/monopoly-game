package com.prj.entity.prison;

import com.org.Node;
import com.org.chained_list.DoubleChainedList;
import com.org.circle.DoubleCircledList;
import com.prj.commom.Logger;
import com.prj.entity.Player;
import com.prj.entity.bank.Banker;


public class Jail {
    private DoubleChainedList<Prisioner> prisioners;
    private DoubleChainedList<LiberationCard> liberationCards;
    private Banker banker;

    public Jail(Banker banker) {
        this.prisioners = new DoubleChainedList<>();
        this.liberationCards = new DoubleChainedList<>();
        this.banker = banker;
    }

    public void arrest(Player player) throws IllegalArgumentException {
        if (this.isJailed(player)) {
            throw new IllegalArgumentException("Player ["+ player.getName() +"] already in jail.");
        }

        Logger.showInfo(player, "was arrested.");
        Prisioner jailed = new Prisioner(player);
        this.prisioners.insertLast(new Node<>(jailed));
    }

    public DoubleChainedList<Player> liberate() {
        DoubleChainedList<Player> people = new DoubleChainedList<>();
        for (Prisioner prisioner : this.prisioners) {
            Player player = prisioner.getPlayer();

            if (prisioner.isFree()) {
                Logger.showInfo(player, "is leaving the jail...");
                this.handleFinancialStuff(prisioner);
                people.insertLast(new Node<>(player));
            } else if (prisioner.attemptLiberationCard()) {
                if (this.getCardCredit(player) == 0) {
                    this.addCardCreditTo(player);
                }
            }
        }

        return people;
    }

    public boolean isJailed(Player player) {
        for (Prisioner prisioner : this.prisioners) {
            if (prisioner.getPlayer() == player) {
                return true;
            }
        }

        return false;
    }

    public void addCardCreditTo(Player player) {
        LiberationCard card = this.getLiberationCard(player);

        if (card == null) {
            card = new LiberationCard(player);
            this.liberationCards.insertLast(new Node<>(card));
        }

        Logger.showInfo(player, "received credit on liberation card");
        card.receiveCredit();
    }

    public int getCardCredit(Player player) {
        LiberationCard card = this.getLiberationCard(player);

        if (card == null) {
            return 0;
        }

        return card.getCredits();
    }

    private LiberationCard getLiberationCard(Player player) {
        for (LiberationCard card : this.liberationCards) {
            if (card.getPlayer() == player) {
                return card;
            }
        }

        return null;
    }

    private void handleFinancialStuff(Prisioner prisioner) {
        boolean liberated = this.useCardCreditFrom(prisioner);
        if (! liberated) {
            this.banker.charge(prisioner.getPlayer(), 50);
        }

        this.terminateStay(prisioner);
    }

    private void terminateStay(Prisioner freeMan) {
        this.prisioners.remove(freeMan);
    }

    private boolean useCardCreditFrom(Prisioner prisioner) {
        Player player = prisioner.getPlayer();
        LiberationCard card = this.getLiberationCard(player);

        if (card != null && card.hasCredits()) {
            card.useCard();
            return true;
        }

        return false;
    }
}
