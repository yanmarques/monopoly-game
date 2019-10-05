package com.prj.entity.bank;

public class InternalAccount extends Account {
    public InternalAccount(Banker banker) {
        super(banker);
    }

    @Override
    public double getBalance() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isDefaulting() {
        return false;
    }
}
