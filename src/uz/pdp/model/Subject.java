package uz.pdp.model;

import uz.pdp.model.base.BaseModel;

public class Subject extends BaseModel {
    private String name;
    private double price;
    private int ball;

    public Subject() {
    }

    public Subject(String name) {
        this.name = name;
    }

    public Subject(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBall() {
        return ball;
    }

    public void setBall(int ball) {
        this.ball = ball;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(this.name);
        str.append(": Max ball - ");
        str.append(this.ball);
        str.append(" Price - ");
        str.append(this.price);
        return str.toString();
    }
}
