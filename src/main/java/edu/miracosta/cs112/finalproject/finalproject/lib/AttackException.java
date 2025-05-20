package edu.miracosta.cs112.finalproject.finalproject.lib;

public class AttackException extends Exception {

    public AttackException() {
        super("Error attacking!");
    }

    public AttackException(String s) {
        super(s);
    }

}
