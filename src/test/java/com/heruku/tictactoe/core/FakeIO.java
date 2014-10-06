package com.heruku.tictactoe.core;

import java.util.ArrayList;
import java.util.List;

public class FakeIO implements IO {
    private ArrayList<Integer> moves;

    public FakeIO(List<Integer> moves) {
        this.moves = new ArrayList<Integer>(moves);
    }

    public FakeIO() {}

    @Override
    public int getMove() {
        return moves.remove(0);
    }

    @Override
    public void notifyOfInvalidMove() {}
}
