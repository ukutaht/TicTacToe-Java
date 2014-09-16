package com.heruku.tictactoe;

public interface UI {
    void printBoard(String boardString);

    void notifyOfInvalidMove();

    void notifyWinner(String winner);

    int getMove();

    boolean shouldPlayAgain();

    void notifyDraw();
}
