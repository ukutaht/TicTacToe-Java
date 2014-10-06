package com.heruku.tictactoe.core;

public interface Player {
    String getMark();

    int getMove(Board board);

    boolean isComputer();
}
