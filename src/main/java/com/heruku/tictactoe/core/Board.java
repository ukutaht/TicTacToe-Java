package com.heruku.tictactoe.core;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final String squares;
    private static final int[][] WINNING_COMBINATIONS = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };

    public Board(String squares) {
        this.squares = squares;
    }

    public static Board EMPTY_BOARD() {
        return new Board("         ");
    }

    public Board markSquare(int index, String mark) {
        StringBuilder newString = new StringBuilder(squares);
        newString.setCharAt(index, mark.charAt(0));
        return new Board(newString.toString());
    }

    public String squareAt(int index) {
        return String.valueOf(squares.charAt(index));
    }

    public boolean isEmptySquare(int index) {
        return isEmpty(squareAt(index));
    }

    public boolean hasWinner() {
        return winner() != null;
    }

    public boolean isValidMove(int index) {
        if (index < 0 || index > 8)
            return false;
        return isEmptySquare(index);
    }

    public boolean isFull() {
        for (int i = 0; i < squares.length(); i++) {
            if (isEmptySquare(i))
                return false;
        }
        return true;
    }

    public String winner() {
        for (int[] WINNING_COMBINATION : WINNING_COMBINATIONS) {
            if (isWinning(WINNING_COMBINATION)) {
                return squareAt(WINNING_COMBINATION[0]);
            }
        }
        return null;
    }

    public List<Integer> validMoves() {
        List<Integer> validMoves = new ArrayList<Integer>();
        for (int i = 0; i < squares.length(); i++) {
            if (isValidMove(i))
                validMoves.add(i);
        }
        return validMoves;
    }

    public String toString() {
        return squares;
    }

    private boolean isWinning(int[] combination) {
        String mark = squareAt(combination[0]);

        if (isEmpty(mark))
            return false;

        for (int i = 0; i < combination.length; i++) {
            if (!squareAt(combination[i]).equals(mark))
                return false;
        }
        return true;
    }

    private boolean isEmpty(String square) {
        return square.equals(" ");
    }
}
