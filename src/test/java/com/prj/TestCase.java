package com.prj;

import com.org.Node;
import com.org.stack.Stack;
import com.prj.commom.Dice;
import com.prj.commom.StackedFakeRandom;
import com.prj.entity.building.Ground;
import com.prj.entity.Player;
import com.prj.entity.bank.Account;
import com.prj.entity.bank.Banker;
import com.prj.entity.prison.Jail;
import com.prj.entity.prison.LiberationCard;
import com.prj.entity.prison.Prisioner;
import org.junit.jupiter.api.BeforeEach;

import java.util.Random;

public class TestCase {
    @BeforeEach
    public void baseSetUp() {
        Dice.setRandGenerator(new Random());
    }

    public Ground dummyGround(long price) {
        return new Ground(price, 100, this.dummyPlayer());
    }

    public Account dummyAccount() {
        return new Account(this.dummyPlayer());
    }

    public Banker dummyBanker() {
        return new Banker();
    }

    public Jail dummyJail() {
        return new Jail(this.dummyBanker());
    }

    public Prisioner dummyPrisioner() {
        return new Prisioner(new Player("foo"));
    }

    public LiberationCard dummyCard() {
        return new LiberationCard(this.dummyPlayer());
    }

    public Player dummyPlayer(String name) {
        return new Player(name);
    }

    public Player dummyPlayer() {
        return this.dummyPlayer("foo");
    }

    public void getLuck() {
        Stack<Integer> fakeResults = new Stack<>();
        fakeResults.insert(new Node<>(1));
        fakeResults.insert(new Node<>(1));

        Dice.setRandGenerator(new StackedFakeRandom(fakeResults));
    }


    public void getNoLuck() {
        Stack<Integer> fakeResults = new Stack<>();
        fakeResults.insert(new Node<>(1));
        fakeResults.insert(new Node<>(2));

        Dice.setRandGenerator(new StackedFakeRandom(fakeResults));
    }
}
