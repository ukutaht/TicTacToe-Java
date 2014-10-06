package com.heruku.tictactoe.core;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final String squares;

    public Board(String squares) {
        this.squares = squares;
    }

    public static Board THREE_BY_THREE() {
        return new Board("         ");
    }


    public static Board FOUR_BY_FOUR() {
        return new Board("                ");
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
        if (index < 0 || index >= squares.length())
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
        for (int[] combination : WinningCombinations.forSize(squares.length())) {
            if (isWinning(combination)) {
                return squareAt(combination[0]);
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

    public int size() {
        return squares.length();
    }

    private boolean isWinning(int[] combination) {
        String mark = squareAt(combination[0]);

        if (isEmpty(mark))
            return false;

        for (int squareIndex : combination) {
            if (!squareAt(squareIndex).equals(mark))
                return false;
        }
        return true;
    }

    private boolean isEmpty(String square) {
        return square.equals(" ");
    }

    public List<String> rows() {
        int sideLength = (int) Math.sqrt(squares.length());
        List<String> rows = new ArrayList<String>();

        for (int i = 0; i < squares.length(); i+=sideLength) {
            rows.add(squares.substring(i, i + sideLength));
        }

        return rows;
    }
}
