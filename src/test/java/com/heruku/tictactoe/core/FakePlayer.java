package com.heruku.tictactoe.core;

public class FakePlayer implements Player {
    @Override
    public String getMark() {
        return "X";
    }

    @Override
    public Board makeMove(Board board) {
        return null;
    }
}
