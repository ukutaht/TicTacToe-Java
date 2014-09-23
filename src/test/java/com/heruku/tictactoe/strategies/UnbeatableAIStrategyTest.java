package com.heruku.tictactoe.strategies;

import com.heruku.tictactoe.core.Board;
import com.heruku.tictactoe.core.Player;
import com.heruku.tictactoe.core.Strategy;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnbeatableAIStrategyTest {

    @Test
    public void testTakesTheWin(){
        UnbeatableAIStrategy strategy = new UnbeatableAIStrategy();
        int move = strategy.getMove(new Board("XX OO    "), Player.X());

        assertEquals(2, move);
    }

    @Test
    public void testBlocksOpponentWin(){
        UnbeatableAIStrategy strategy = new UnbeatableAIStrategy();
        int move = strategy.getMove(new Board("X XO    O"), Player.O());

        assertEquals(1, move);
    }
}