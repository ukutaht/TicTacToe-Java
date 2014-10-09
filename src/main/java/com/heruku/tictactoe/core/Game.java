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

    public void start() {
        showBoard();
        notifyIO();
    }

    public void playMove() {
        int move = getCurrentPlayerMove();
        markMoveIfValid(move);
        showBoard();
        notifyIO();
    }

    public void notifyIO() {
        if (hasWinner()) {
            io.notifyWinner(winner());
        } else if (hasDraw()) {
            io.notifyOfDraw();
        } else {
            io.notifyOfTurn(currentPlayer());
        }
    }

    private void markMoveIfValid(int move) {
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
        return board.isOver();
    }

    public Player winner() {
        String winnerMark = board.winner();
        return findPlayerByMark(winnerMark);
    }

    public Player playerAt(int square) {
        String playerMark = board.squareAt(square);
        return findPlayerByMark(playerMark);
    }

    public Board getBoard() {
        return board;
    }

    public boolean hasWinner() {
        return board.hasWinner();
    }

    public boolean hasDraw() {
        return board.hasDraw();
    }

    public String boardString() {
        return board.toString();
    }

    private Player findPlayerByMark(String winnerMark) {
        for (Player player : players) {
            if (player.getMark().toString().equals(winnerMark))
                return player;
        }
        throw new RuntimeException("Invalid winner mark " + winnerMark);
    }

    private int getCurrentPlayerMove() {
        return currentPlayer().getMove(board);
    }

    private void showBoard() {
        io.showBoard(board);
    }

    private void notifyOfInvalidMove() {
        io.notifyOfInvalidMove();
    }

    private void makeMove(int move) {
        board = board.markSquare(move, currentPlayer().getMark().toString());
        Collections.rotate(players, 1);
    }

    private boolean isValid(int move) {
        return board.isValidMove(move) && !isOver();
    }
}
