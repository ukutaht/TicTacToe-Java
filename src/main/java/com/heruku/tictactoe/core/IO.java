package com.heruku.tictactoe.core;

public interface IO {

    Move getMove();

    void showBoard(Board board);

    void notifyOfInvalidMove();

    void notifyOfTurn(Player player);

    void notifyWinner(Player player);

    void notifyOfDraw();
}