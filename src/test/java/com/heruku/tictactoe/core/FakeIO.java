package com.heruku.tictactoe.core;

import java.util.ArrayList;
import java.util.List;

public class FakeIO implements IO {
    private ArrayList<Integer> moves;
    private String out;

    public FakeIO(List<Integer> moves) {
        this.moves = new ArrayList<Integer>(moves);
    }

    public FakeIO() {}

    public String getOut() {
        return this.out;
    }

    @Override
    public int getMove() {
        return moves.remove(0);
    }

    @Override
    public void showBoard(Board board) {
        out += "board";
    }

    @Override
    public void notifyOfInvalidMove() {
        this.out += "Invalid move";
    }

    @Override
    public void notifyOfTurn(Player player) {
        out += player.getMark() + " turn";
    }

    @Override
    public void notifyWinner(Player winner) {
        out += winner + "wins";
    }

    @Override
    public void notifyOfDraw() {
        out += "draw";
    }
}
