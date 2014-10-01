package com.heruku.tictactoe.core;

import java.util.ArrayList;
import java.util.List;

public class FakeUI implements UI {
    private ArrayList<Integer> moves;

    public FakeUI(List<Integer> moves) {
        this.moves = new ArrayList<Integer>(moves);
    }

    public FakeUI() {}

    @Override
    public int getMove() {
        return moves.remove(0);
    }

    public boolean shouldPlayAgain() {
        return false;
    }

    @Override
    public void update(Game game) {}

    @Override
    public void notifyWinner(Game game) {}

    @Override
    public void notifyOfInvalidMove() {}

    @Override
    public GameType getGameType() {
        return GameType.HUMAN_VS_HUMAN;
    }
}
