package com.heruku.tictactoe.core;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class FakeIO implements IO {
    private List<Move> moves;
    private List<String> called = new ArrayList<String>();

    public FakeIO(List<Integer> moves) {
        this(convertToMoves(moves));
    }

    private static Move[] convertToMoves(List<Integer> moves) {
        Move [] arrayOfMoves = new Move[moves.size()];
        for (int i = 0; i < moves.size(); i++) {
            arrayOfMoves[i] = new Move(moves.get(i));
        }
        return arrayOfMoves;
    }

    public FakeIO() {}

    public FakeIO(Move[] moves) {
       this.moves = new ArrayList<Move>(asList(moves));
    }

    public List<String> calledMethods() {
        return this.called;
    }

    @Override
    public Move getMove() {
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
