package com.heruku.tictactoe.core;

public interface IO {

    int getMove();

    void notifyOfInvalidMove();

    void notifyOfTurn(Player player);

    void notifyWinner(Player player);

    void notifyOfDraw();
}