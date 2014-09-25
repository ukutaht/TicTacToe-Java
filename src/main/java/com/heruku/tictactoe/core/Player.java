package com.heruku.tictactoe.core;

public interface Player {
    String getMark();

    Board makeMove(Board board);
}
