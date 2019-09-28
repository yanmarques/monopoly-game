package com.prj.entity.building;

abstract public class BaseBuilding {
    protected int taxPercentage = 20;

    private long price;
    private int rentAmount;
    private double tax;

    public BaseBuilding(long price, int rentAmount) {
        this.setPrice(price);
        this.setRentAmount(rentAmount);
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
        this.calculateTax();
    }

    public int getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(int rentAmount) {
        this.rentAmount = rentAmount;
    }

    public double getTax() {
        return tax;
    }

    private void calculateTax() {
        this.tax = (this.price * this.taxPercentage) / 100;
    }
}
