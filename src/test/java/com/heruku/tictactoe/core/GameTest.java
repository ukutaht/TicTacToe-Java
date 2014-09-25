package com.heruku.tictactoe.core;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

public class GameTest {

    Game game;

    @Before
    public void setupEmptyGame(){
        List<Player> players = new ArrayList<Player>();
        players.add(new FakePlayer());
        this.game = new Game(new FakeUI(Arrays.asList(0)), players);
    }

    public void setupHumanGame(List<Integer> moves){
        List<Player> players = new ArrayList<Player>();
        UI ui = new FakeUI(moves);
        players.add(new HumanPlayer("X", ui));
        players.add(new HumanPlayer("O", ui));
        this.game = new Game(ui, players);
    }

    @Test
    public void testXWins() {
        setupHumanGame(Arrays.asList(0, 8, 1, 7, 2));
        game.play();
        assertEquals("X", game.winner());
    }

    @Test
    public void testOWins() {
        setupHumanGame(Arrays.asList(0, 8, 1, 7, 3, 6));
        game.play();
        assertEquals("O", game.winner());
    }

    @Test
    public void testDrawGame() {
        setupHumanGame(Arrays.asList(0, 1, 2, 4, 3, 5, 7, 6, 8));
        game.play();
        assertTrue(game.hasDraw());
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
        game = new Game(null, new Board(boardString), null);
        assertFalse(game.isOver());
    }

    private void testIsOver(String boardString) {
        game = new Game(null, new Board(boardString), null);
        assertTrue(game.isOver());
    }

    private void testIsWinner(String boardString, String expectedWinner) {
        game = new Game(null, new Board(boardString), null);
        assertTrue(game.hasWinner());
        assertEquals(expectedWinner, game.winner());
    }
}
