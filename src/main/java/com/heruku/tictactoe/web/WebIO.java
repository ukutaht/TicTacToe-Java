package com.heruku.tictactoe.web;

import com.heruku.tictactoe.core.*;

public class WebIO implements IO {
    private Integer move;
    private String message;

    public void setMove(Integer move) {
        this.move = move;
    }

    @Override
    public int getMove() {
        return move;
    }

    @Override
    public void notifyOfInvalidMove() {}

    public void notifyWinner(Game game) {
        this.message = game.winner() + " has won!";
    }

    public void notifyTurn(Game game) {
        message = game.currentPlayer().getMark() + " turn.";
    }

    public String getMessage() {
        return message;
    }
}
