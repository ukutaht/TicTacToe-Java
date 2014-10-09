package com.heruku.tictactoe.players;

import com.heruku.tictactoe.core.Board;
import com.heruku.tictactoe.core.Player;
import com.heruku.tictactoe.core.PlayerMark;

import java.util.List;
import java.util.Random;

public class ComputerPlayer implements Player {
    private final PlayerMark mark;

    public ComputerPlayer(PlayerMark mark) {
        this.mark = mark;
    }

    @Override
    public PlayerMark getMark() {
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
            int score = -negamax(board.markSquare(move, mark.toString()), mark.opponent(), -10, 10);
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

    private int negamax(Board board, PlayerMark mark, int alpha, int beta) {
        if (board.hasWinner() || board.isFull())
            return getScore(board, mark);

        int bestScore = -10;

        for (Integer move : board.validMoves()) {
            int score = -negamax(board.markSquare(move, mark.toString()), mark.opponent(), -beta, -alpha);
            bestScore = Math.max(score, bestScore);
            alpha = Math.max(bestScore, alpha);
            if(alpha >= beta)
                break;
        }

        return bestScore;
    }

    private int getScore(Board board, PlayerMark mark) {
        if (board.hasDraw())
            return 0;
        if (board.winner().equals(mark.toString()))
            return 1;
        return -1;
    }
}