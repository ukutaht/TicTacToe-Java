package com.heruku.tictactoe.strategies;

import com.heruku.tictactoe.core.Board;
import com.heruku.tictactoe.core.Player;
import com.heruku.tictactoe.core.Strategy;

import java.util.Random;

public class RandomStrategy implements Strategy {
    private Random random;

    public RandomStrategy(){
        this.random = new Random();
    }

    public int getMove(Board board, Player player){
        int move = randomMove(9);
        while(!board.isValidMove(move)){
            move = randomMove(9);
        }
        return move;
    }

    private int randomMove(int max){
        return random.nextInt(max) + 1;
    }
}
