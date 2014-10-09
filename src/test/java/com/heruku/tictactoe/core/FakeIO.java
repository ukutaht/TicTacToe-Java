package com.heruku.tictactoe.core;

import java.util.ArrayList;
import java.util.List;

public class FakeIO implements IO {
    private ArrayList<Integer> moves;
    private List<String> called = new ArrayList<String>();

    public FakeIO(List<Integer> moves) {
        this.moves = new ArrayList<Integer>(moves);
    }

    public FakeIO() {}

    public List<String> calledMethods() {
        return this.called;
    }

    @Override
    public int getMove() {
        return moves.remove(0);
    }

    @Override
    public void showBoard(Board board) {
        called.add("showBoard");
    }

    @Override
    public void notifyOfInvalidMove() {
        called.add("notifyOfInvalidMove");
    }

    @Override
    public void notifyOfTurn(Player player) {
        called.add("notifyOfTurn");
    }

    @Override
    public void notifyWinner(Player winner) {
        called.add("notifyWinner");
    }

    @Override
    public void notifyOfDraw() {
        called.add("notifyOfDraw");
    }
}
