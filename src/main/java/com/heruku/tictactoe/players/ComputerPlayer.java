package com.heruku.tictactoe.players;

import com.heruku.tictactoe.core.Board;
import com.heruku.tictactoe.core.Player;

import java.util.List;
import java.util.Random;

import static com.heruku.tictactoe.core.Constants.O;
import static com.heruku.tictactoe.core.Constants.X;

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
    public boolean isComputer() {
        return true;
    }

    @Override
    public int getMove(Board board) {
        List<Integer> validMoves = board.validMoves();

        if(validMoves.size() > 11)
            return sample(validMoves);

        int bestScore = -10;
        int bestMove = -10;

        for (Integer move : validMoves) {
            int score = -negamax(board.markSquare(move, mark), opponentFor(mark), -10, 10);
            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }

        return bestMove;
    }

    private int sample(List<Integer> validMoves) {
        Random random = new Random();
        int randomIndex = random.nextInt(validMoves.size());
        return validMoves.get(randomIndex);
    }

    private int negamax(Board board, String playerMark, int alpha, int beta) {
        if (board.hasWinner() || board.isFull())
            return getScore(board, playerMark);

        int bestScore = -10;

        for (Integer move : board.validMoves()) {
            int score = -negamax(board.markSquare(move, playerMark), opponentFor(playerMark), -beta, -alpha);
            bestScore = Math.max(score, bestScore);
            alpha = Math.max(bestScore, alpha);
            if(alpha >= beta)
                break;
        }

        return bestScore;
    }

    private String opponentFor(String mark) {
        return mark.equals(X) ? O : X;
    }

    private int getScore(Board board, String playerMark) {
        if (board.hasDraw())
            return 0;
        if (board.winner().equals(playerMark))
            return 1;
        return -1;
    }
}