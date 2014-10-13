package com.heruku.tictactoe.core;

public class Move {
    private final int move;
    private static int ILLEGAL_MOVE = Integer.MIN_VALUE;

    public Move(int move) {
        this.move = move;
    }

    public static Move illegal() {
        return new Move(ILLEGAL_MOVE);
    }

    public int getValue() {
        if (isLegal())
            return move;
        throw new IllegalAccessError("Cannot access value of an illegal move");
    }

    public boolean isLegal() {
        return move != ILLEGAL_MOVE;
    }

    public boolean equals(Object other) {
        return this.getValue() == ((Move) other).getValue();
    }
}
