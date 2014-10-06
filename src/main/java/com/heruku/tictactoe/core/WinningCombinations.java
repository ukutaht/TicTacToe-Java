package com.heruku.tictactoe.core;

class WinningCombinations {
    private static final int[][] THREE_BY_THREE = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };
    private static final int[][] FOUR_BY_FOUR = {
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

    public static int[][] forSize(int size) {
        switch (size) {
            case 9:  return THREE_BY_THREE;
            case 16: return FOUR_BY_FOUR;
            default: throw new RuntimeException("Winning combinations not found for size" + size);
        }
    }
}
