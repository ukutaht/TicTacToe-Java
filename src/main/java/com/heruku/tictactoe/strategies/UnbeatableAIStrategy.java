package com.heruku.tictactoe.strategies;

import com.heruku.tictactoe.core.Board;
import com.heruku.tictactoe.core.Player;
import com.heruku.tictactoe.core.Strategy;

import java.util.Iterator;

public class UnbeatableAIStrategy implements Strategy{

    public int getMove(Board board, Player player) {
        int bestScore = -1;
        int bestMove = -1;
        Iterator<Integer> validMoves = board.validMoves();

        while (validMoves.hasNext()){
            int move = validMoves.next();
            int score = -negamax(board.markSquare(move, player.getMark()), player.next());
            if (score  > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }

        return bestMove;
    }

    private int negamax(Board board, Player player){
        if(board.hasWinner() || board.isFull())
            return getScore(board, player.getMark());

        int bestScore = -1;
        Iterator<Integer> validMoves = board.validMoves();

        while (validMoves.hasNext()){
            int move = validMoves.next();
            int score = -negamax(board.markSquare(move, player.getMark()), player.next());
            if (score  > bestScore) {
                bestScore = score;
            }
        }

        return bestScore;
    }

    private int getScore(Board board, String mark){
        if(board.winner() == mark)
            return 1;
        if(board.hasWinner())
            return -1;
         return 0;
    }
}
