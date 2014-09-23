package com.heruku.tictactoe.strategies;

import com.heruku.tictactoe.core.Board;
import com.heruku.tictactoe.core.Player;
import com.heruku.tictactoe.core.Strategy;
import org.junit.Test;

import static org.junit.Assert.*;

public class RandomStrategyTest {
    @Test
   public void testItReturnsAValidMove(){
       Strategy strategy = new RandomStrategy();
       Board board = new Board("         ");
       int move = strategy.getMove(board, Player.X());

       assertTrue(board.isValidMove(move));
   }
}