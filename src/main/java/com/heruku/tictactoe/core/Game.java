package com.heruku.tictactoe.core;

import java.util.Collections;
import java.util.List;

public class Game {
    private UI ui;
    Board board;
    List<Player> players;

    public Game(List<Player> players, UI ui) {
        this.board = Board.EMPTY_BOARD();
        this.players = players;
        this.ui = ui;
    }

    public Game(Board board) {
        this.board = board;
    }

    public void playMove() {
        int move = getCurrentPlayerMove();
        if (isValid(move)) {
            makeMove(move);
        } else {
            notifyOfInvalidMove();
        }
    }

    public Player currentPlayer() {
        return players.get(0);
    }

    public boolean isOver() {
        return hasWinner() || hasDraw();
    }

    public String winner() {
        return board.winner();
    }

    public boolean hasWinner() {
        return board.hasWinner();
    }

    public boolean hasDraw() {
        return board.isFull() && !hasWinner();
    }

    public String boardString() {
        return board.toString();
    }

    private void notifyOfInvalidMove() {
        ui.notifyOfInvalidMove();
    }

    private void makeMove(int move) {
        board = board.markSquare(move, currentPlayer().getMark());
        Collections.rotate(players, 1);
    }

    private boolean isValid(int move) {
        return board.isValidMove(move);
    }

    private int getCurrentPlayerMove() {
        return currentPlayer().getMove(board);
    }
}
