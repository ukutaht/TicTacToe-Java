package com.heruku.tictactoe.core;

import java.util.ArrayList;
import java.util.List;

public abstract class Board {
    private final String squares;

    Board(String squares) {
        this.squares = squares;
    }

    public abstract Board makeBoard(String squares);

    public abstract int[][] getWinningCombinations();

    public Board markSquare(Move move, String mark) {
        StringBuilder newString = new StringBuilder(squares);
        newString.setCharAt(move.getValue(), mark.toString().charAt(0));

        return makeBoard(newString.toString());
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

    public boolean isValidMove(Move move) {
        return move.isLegal() && validMoves().contains(move);
    }

    public boolean isFull() {
        return !squares.contains(" ");
    }

    public String winner() {
        for (int[] combination : getWinningCombinations()) {
            if (isWinning(combination)) {
                return squareAt(combination[0]);
            }
        }
        return null;
    }

    public List<Move> validMoves() {
        List<Move> validMoves = new ArrayList<Move>();

        for (int i = 0; i < squares.length(); i++) {
            Move move = new Move(i);
            if (isEmptySquare(move.getValue())) {
                validMoves.add(move);
            }
        }
        return validMoves;
    }

    public String toString() {
        return squares;
    }

    public boolean isOver() {
        return hasWinner() || hasDraw();
    }

    public boolean hasDraw() {
        return isFull() && !hasWinner();
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
}
