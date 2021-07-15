package com.java.chess.model;

public class Player extends Account{
    public Person person;
    public boolean whiteSide;

    public Player(Person person, boolean whiteSide) {
        this.person = person;
        this.whiteSide = whiteSide;
    }

    @Override
    public Person getPerson() {
        return person;
    }

    @Override
    public void setPerson(Person person) {
        this.person = person;
    }

    public boolean isWhiteSide() {
        return whiteSide;
    }

    public void setWhiteSide(boolean whiteSide) {
        this.whiteSide = whiteSide;
    }

    @Override
    public String toString() {
        return "Player{" +
                "person=" + person +
                ", whiteSide=" + whiteSide +
                '}';
    }
}
