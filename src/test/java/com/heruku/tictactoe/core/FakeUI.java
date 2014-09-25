package com.heruku.tictactoe.core;

import java.util.ArrayList;
import java.util.List;

public class FakeUI implements UI{
    private ArrayList<Integer> moves;

    public FakeUI(List<Integer> moves) {
        this.moves = new ArrayList<Integer>(moves);
    }

    @Override
    public int getMove() {
        return moves.remove(0);
    }

    @Override
    public boolean shouldPlayAgain() {
        return false;
    }

    @Override
    public void printBoard(String boardString) {}

    @Override
    public void notifyOfInvalidMove() {}

    @Override
    public void notifyWinner(String winner) {}

    @Override
    public void notifyDraw() {}

    @Override
    public String getPlayerTypeFor(String mark) {
        return Constants.HUMAN;
    }
}
