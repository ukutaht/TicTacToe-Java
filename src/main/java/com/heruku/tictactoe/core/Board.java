package com.heruku.tictactoe.core;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final String squares;
    private final int[][] winningCombinations;

    public Board(String squares, int[][] winningCombinations) {
        this.squares = squares;
        this.winningCombinations = winningCombinations;
    }

    public static Board THREE_BY_THREE() {
        return new Board("         ", WinningCombinations.THREE_BY_THREE);
    }

    public static Board THREE_BY_THREE(String squares) {
        return new Board(squares, WinningCombinations.THREE_BY_THREE);
    }

    public static Board FOUR_BY_FOUR() {
        return new Board("                ", WinningCombinations.FOUR_BY_FOUR);
    }

    public static Board FOUR_BY_FOUR(String squares) {
        return new Board(squares, WinningCombinations.FOUR_BY_FOUR);
    }

    public Board markSquare(int index, String mark) {
        StringBuilder newString = new StringBuilder(squares);
        newString.setCharAt(index, mark.toString().charAt(0));
        return new Board(newString.toString(), winningCombinations);
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
        for (int[] combination : winningCombinations) {
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

    public boolean isOver() {
        return hasWinner() || hasDraw();
    }

    public boolean hasDraw() {
        return isFull() && !hasWinner();
    }
}
