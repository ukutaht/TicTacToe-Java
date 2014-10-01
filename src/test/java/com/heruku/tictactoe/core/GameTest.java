package com.heruku.tictactoe.core;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {

    private Game game;

    @Before
    public void setupEmptyGame() {
        List<Player> players = new ArrayList<Player>();
        players.add(new HumanPlayer(Constants.X, new FakeUI(Arrays.asList(0))));
        players.add(new HumanPlayer(Constants.O, new FakeUI(Arrays.asList(0))));
        this.game = new Game(players, new FakeUI());
    }

    @Test
    public void playMoveMakesMove() {
        game.playMove();
        assertEquals(Constants.X, game.board.squareAt(0));
    }

    @Test
    public void playMoveAdvancesTurn() {
        game.playMove();
        assertEquals(Constants.O, game.currentPlayer().getMark());
    }

    @Test
    public void playMoveDoesNotMakeMoveIfInvalid() {
        game.playMove();
        game.playMove();
        assertEquals(Constants.X, game.board.squareAt(0));
    }

    @Test
    public void emptyGameIsNotOver() {
        testIsNotOver("         ");
    }

    @Test
    public void halfWayPlayedIsNotOver() {
        testIsNotOver("XO  O  X ");
    }

    @Test
    public void isOverHorizontalWin() {
        testIsOver("XXX      ");
    }

    @Test
    public void isOverVerticalWin() {
        testIsOver("X  X  X  ");
    }

    @Test
    public void isOverDiagonalWin() {
        testIsOver("X   X   X");
    }

    @Test
    public void isOverDraw() {
        testIsOver("XXOXOXOOX");
    }

    @Test
    public void winnerX() {
        testIsWinner("XXX      ", "X");
    }

    @Test
    public void winnerO() {
        testIsWinner("OOO      ", "O");
    }

    private void testIsNotOver(String boardString) {
        game = new Game(new Board(boardString));
        assertFalse(game.isOver());
    }

    private void testIsOver(String boardString) {
        game = new Game(new Board(boardString));
        assertTrue(game.isOver());
    }

    private void testIsWinner(String boardString, String expectedWinner) {
        game = new Game(new Board(boardString));
        assertTrue(game.hasWinner());
        assertEquals(expectedWinner, game.winner());
    }
}
