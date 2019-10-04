package com.prj.entity.prison;

import com.org.Node;
import com.org.chained_list.DoubleChainedList;
import com.org.circle.DoubleCircledList;
import com.prj.entity.Player;


public class Jail {
    private DoubleChainedList<Prisioner> prisioners;
    private DoubleChainedList<LiberationCard> liberationCards;

    public Jail() {
        this.prisioners = new DoubleChainedList<>();
        this.liberationCards = new DoubleChainedList<>();
    }

    public void arrest(Player player) throws IllegalArgumentException {
        if (this.isJailed(player)) {
            throw new IllegalArgumentException("Player ["+ player.getName() +"] already in jail.");
        }

        Prisioner jailed = new Prisioner(player);
        this.prisioners.insertLast(new Node<>(jailed));
    }

    public DoubleCircledList<Player> liberate() {
        DoubleCircledList<Player> people = new DoubleCircledList<>();
        for (Prisioner prisioner : this.prisioners) {
            Player player = prisioner.getPlayer();

            if (prisioner.isFree()) {
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
            // paga dinheiro para o banco
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
