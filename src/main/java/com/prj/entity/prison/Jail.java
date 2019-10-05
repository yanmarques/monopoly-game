package com.prj.entity.prison;

import com.org.Node;
import com.org.circle.DoubleCircledList;
import com.prj.entity.Player;

import java.util.ArrayList;

public class Jail {
    private DoubleCircledList<Prisioner> prisioners;
    private ArrayList<String> ids;
    private DoubleCircledList<LiberationCard> liberationCards;

    public Jail() {
        this.prisioners = new DoubleCircledList<>();
        this.liberationCards = new DoubleCircledList<>();
        this.ids = new ArrayList<>();
    }

    public void arrest(Player player) throws IllegalArgumentException {
        if (this.isJailed(player)) {
            throw new IllegalArgumentException("Player ["+ player.getName() +"] already in jail.");
        }

        Prisioner jailed = new Prisioner(player);
        this.prisioners.insertLast(new Node<>(jailed));
        this.ids.add(this.identifyPlayer(player));
    }

    public DoubleCircledList<Player> liberate() {
        DoubleCircledList<Player> people = new DoubleCircledList<>();
        for (int i = 0; i < this.prisioners.getSize(); i++) {
            Prisioner prisioner = this.prisioners.get(i).getValue();
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
        return this.ids.contains(this.identifyPlayer(player));
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
        LiberationCard card = null;
        for (int i = 0; i < this.liberationCards.getSize(); i++) {
            card = this.liberationCards.get(i).getValue();

            if (card.getPlayer() == player) {
                return card;
            }
        }

        return card;
    }

    private void handleFinancialStuff(Prisioner prisioner) {
        boolean liberated = this.useCardCreditFrom(prisioner);
        if (! liberated) {
            // paga dinheiro para o banco
        }

        this.terminateStay(prisioner);
    }

    private void terminateStay(Prisioner freeMan) {
        this.ids.remove(this.identifyPlayer(freeMan.getPlayer()));

        for (int i = 0; i < this.prisioners.getSize(); i++) {
            Prisioner prisioner = this.prisioners.get(i).getValue();
            if (prisioner == freeMan) {
                this.prisioners.remove(i);
                break;
            }
        }
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

    private String identifyPlayer(Player player) {
        return String.valueOf(player.hashCode());
    }
}
