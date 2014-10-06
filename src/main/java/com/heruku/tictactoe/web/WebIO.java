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

    @Override
    public void notifyOfTurn(Player player) {
        message = player.getMark() + " turn.";
    }

    @Override
    public void notifyWinner(Player winner) {
        message = winner.getMark() + " wins!";
    }

    @Override
    public void notifyOfDraw() {
        message = "It's a draw!";
    }

    public String getMessage() {
        return message;
    }
}
