package com.heruku.tictactoe.core;

import static com.heruku.tictactoe.core.Constants.*;

public class ComputerPlayer implements Player {
    private final String mark;

    public ComputerPlayer(String mark) {
        this.mark = mark;
    }

    @Override
    public String getMark() {
        return mark;
    }

    @Override
    public int getMove(Board board) {
        int bestScore = -1;
        int bestMove = -1;

        for (Integer move : board.validMoves()) {
            int score = -negamax(board.markSquare(move, mark), opponentFor(mark));
            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }

        return bestMove;
    }

    private int negamax(Board board, String playerMark) {
        if (board.hasWinner() || board.isFull())
            return getScore(board, playerMark);

        int bestScore = -1;

        for (Integer move : board.validMoves()) {
            int score = -negamax(board.markSquare(move, playerMark), opponentFor(playerMark));
            if (score > bestScore) {
                bestScore = score;
            }
        }

        return bestScore;
    }


    private String opponentFor(String mark) {
        if (mark.equals(X))
            return O;
        return X;
    }

    private int getScore(Board board, String playerMark) {
        String winner = board.winner();

        if (winner == null)
            return 0;
        if (winner.equals(playerMark))
            return 1;
        return -1;
    }
}
