package com.heruku.tictactoe.core;

import com.heruku.tictactoe.strategies.MockStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameTest {

    Game game;
    Player player;

    @Before
    public void setupGame(){
        player = Player.X(new MockStrategy());
        this.game = new Game(player);
    }

    @Test
    public void testMove(){
        Game newGame = game.playMove(0);
        assertFalse(newGame.isValidMove(0));
    }

    @Test(expected=RuntimeException.class)
    public void testThrowsWhenPlayingInvalidMove(){
        game.playMove(0).playMove(0);
    }

    @Test
    public void testEmptyGameIsNotOver(){
        testIsNotOver("         ");
    }

    @Test
    public void testHalfWayPlayedIsNotOver(){
        testIsNotOver("XO  O  X ");
    }

    @Test
    public void testIsOverHorizontalWin(){
        testIsOver("XXX      ");
    }

    @Test
    public void testIsOverVerticalWin(){
        testIsOver("X  X  X  ");
    }

    @Test
    public void testIsOverDiagonalWin(){
        testIsOver("X   X   X");
    }

    @Test
    public void testIsOverDraw(){
        testIsOver("XXOXOXOOX");
    }

    @Test
    public void testWinnerX(){
        testIsWinner("XXX      ", "X");
    }

    @Test
    public void testWinnerO(){
        testIsWinner("OOO      ", "O");
    }

    private void testIsNotOver(String boardString){
        game = new Game(new Board(boardString), null);
        assertFalse(game.isOver());
    }

    private void testIsOver(String boardString) {
        game = new Game(new Board(boardString), null);
        assertTrue(game.isOver());
    }

    private void testIsWinner(String boardString, String expectedWinner) {
        game = new Game(new Board(boardString), null);
        assertTrue(game.hasWinner());
        assertEquals(expectedWinner, game.winner());
    }
}
