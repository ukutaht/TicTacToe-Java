package com.heruku.tictactoe.core;

import java.util.Collections;
import java.util.List;

public class Game {
    private IO io;
    Board board;
    List<Player> players;

    public Game(List<Player> players, IO io) {
        this.board = Board.THREE_BY_THREE();
        this.players = players;
        this.io = io;
    }

    public Game(Board board, List<Player> players, IO io) {
        this.board = board;
        this.players = players;
        this.io = io;
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
        io.notifyOfInvalidMove();
    }

    private void makeMove(int move) {
        board = board.markSquare(move, currentPlayer().getMark());
        Collections.rotate(players, 1);
    }

    private boolean isValid(int move) {
        return board.isValidMove(move) && !isOver();
    }

    private int getCurrentPlayerMove() {
        return currentPlayer().getMove(board);
    }

    public Board getBoard() {
        return board;
    }
}
