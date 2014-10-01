package com.heruku.tictactoe.web;

import com.heruku.tictactoe.core.Game;
import com.heruku.tictactoe.core.GameType;
import com.heruku.tictactoe.core.UI;

public class WebUI implements UI {
    private Game game;
    private int move;

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setMove(Integer move) {
        this.move = move;
    }

    @Override
    public int getMove() {
        return move;
    }

    @Override
    public boolean shouldPlayAgain() {
        return false;
    }

    @Override
    public void update(Game game) {

    }

    @Override
    public void notifyWinner(Game game) {
    }

    @Override
    public void notifyOfInvalidMove() {

    }

    @Override
    public GameType getGameType() {
        return GameType.HUMAN_VS_HUMAN;
    }
}
