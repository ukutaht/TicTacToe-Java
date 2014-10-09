package com.heruku.tictactoe.core;

public interface Player {
    PlayerMark getMark();

    int getMove(Board board);

    boolean isComputer();
}
