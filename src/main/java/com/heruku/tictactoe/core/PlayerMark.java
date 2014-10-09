package com.heruku.tictactoe.core;

public enum PlayerMark {
    X("X"),
    O("O");

    private String mark;

    PlayerMark(String mark) {
        this.mark = mark;
    }

    public String toString() {
        return mark;
    }

    public PlayerMark opponent() {
        if (this == X)
            return O;
        return X;
    }
}
