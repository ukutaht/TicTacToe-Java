package com.heruku.tictactoe.core;

public interface Strategy {
    int getMove(Board board, Player player);
}
