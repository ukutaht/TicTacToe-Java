package com.heruku.tictactoe.core;

public class ThreeByThreeBoard extends Board {
    public static final int[][] WINNING_COMBINATIONS = new int[][]{
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };

    private static String EMPTY = "         ";

    public ThreeByThreeBoard() {
        super(EMPTY);
    }

    public ThreeByThreeBoard(String squares) {
        super(squares);
    }

    @Override
    public Board makeBoard(String squares) {
        return new ThreeByThreeBoard(squares);
    }

    @Override
    public int[][] getWinningCombinations() {
        return WINNING_COMBINATIONS;
    }
}
