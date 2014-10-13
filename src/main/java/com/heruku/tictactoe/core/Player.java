package com.heruku.tictactoe.core;

public interface Player {
    PlayerMark getMark();

    Move getTheMove(Board board);

    boolean isComputer();
}
