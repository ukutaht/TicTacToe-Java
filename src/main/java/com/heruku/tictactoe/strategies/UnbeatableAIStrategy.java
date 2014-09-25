package com.heruku.tictactoe.strategies;

import com.heruku.tictactoe.core.Board;
import com.heruku.tictactoe.core.Player;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class UnbeatableAIStrategy{

    public int getMove(Board board, List<Player> players) {
        int bestScore = -1;
        int bestMove = -1;
        Iterator<Integer> validMoves = board.validMoves();

        while (validMoves.hasNext()){
            int move = validMoves.next();
            int score = -negamax(board.markSquare(move, players.get(0).getMark()), players);
            if (score  > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }

        return bestMove;
    }

    private int negamax(Board board, List<Player> players){
        Collections.rotate(players, 1);

        if(board.hasWinner() || board.isFull())
            return getScore(board, players.get(0).getMark());

        int bestScore = -1;
        Iterator<Integer> validMoves = board.validMoves();

        while (validMoves.hasNext()){
            int move = validMoves.next();
            int score = -negamax(board.markSquare(move, players.get(0).getMark()), players);
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
