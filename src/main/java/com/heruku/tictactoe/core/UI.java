package com.heruku.tictactoe.core;

public interface UI {

    int getMove();

    boolean shouldPlayAgain();

    void update(Game game);

    void notifyWinner(Game game);

    void notifyOfInvalidMove();

    GameType getGameType();
}