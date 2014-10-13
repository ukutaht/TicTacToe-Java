package com.heruku.tictactoe.core;

public class FourByFourBoard extends Board {
    public static final int[][] WINNING_COMBINATIONS = new int[][]{
            {0, 1, 2, 3},
            {4, 5, 6, 7},
            {8, 9, 10, 11},
            {12, 13, 14, 15},
            {0, 4, 8, 12},
            {1, 5, 9, 13},
            {2, 6, 10, 14},
            {3, 7, 11, 15},
            {0, 5, 10, 15},
            {3, 6, 9, 12},
    };
    private static final String EMPTY = "                ";

    public FourByFourBoard(String squares) {
        super(squares);
    }

    public FourByFourBoard() {
        super(EMPTY);
    }

    @Override
    public Board makeBoard(String squares) {
        return new FourByFourBoard(squares);
    }

    @Override
    public int[][] getWinningCombinations() {
        return WINNING_COMBINATIONS;
    }
}
