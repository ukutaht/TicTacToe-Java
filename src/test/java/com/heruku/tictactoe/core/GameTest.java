package com.heruku.tictactoe.core;


import com.heruku.tictactoe.players.HumanPlayer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.heruku.tictactoe.core.Constants.*;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

public class GameTest {

    private Game game;
    private FakeIO io;

    @Before
    public void setupEmptyGame() {
       setupGameWithBoard("         ");
    }

    public void setupGameWithBoard(String boardString) {
        List<Player> players = new ArrayList<Player>();
        players.add(new HumanPlayer(X, new FakeIO(Arrays.asList(0))));
        players.add(new HumanPlayer(O, new FakeIO(Arrays.asList(0))));
        Board board = Board.THREE_BY_THREE(boardString);
        this.io = new FakeIO();
        this.game = new Game(board, players, io);
    }


    @Test
    public void initShowsBoardAndTellsTurn() {
        game.start();
        assertThat(io.getOut(), containsString("board"));
        assertThat(io.getOut(), containsString("turn"));
    }

    @Test
    public void playMoveMakesMove() {
        game.playMove();
        assertEquals(X, game.board.squareAt(0));
    }

    @Test
    public void playMoveAdvancesTurn() {
        game.playMove();
        assertEquals(O, game.currentPlayer().getMark());
    }

    @Test
    public void playMoveDoesNotMakeMoveIfInvalid() {
        game.playMove();
        game.playMove();
        assertEquals(X, game.board.squareAt(0));
    }

    @Test
    public void showsBoard() {
        game.playMove();
        assertThat(io.getOut(), containsString("board"));
    }

    @Test
    public void notifiesIOofTheTurn() {
        game.playMove();
        assertThat(io.getOut(), containsString("turn"));
    }

    @Test
    public void notifiesIOThatGameHasWinner() {
        setupGameWithBoard(" XX O  O ");
        game.playMove();
        assertThat(io.getOut(), containsString("wins"));
    }

    @Test
    public void notifiesIOThatGameHasdraw() {
        setupGameWithBoard(" OXXOXOXO");
        game.playMove();
        assertThat(io.getOut(), containsString("draw"));
    }

    @Test
    public void emptyGameIsNotOver() {
        assertIsNotOver("         ");
    }

    @Test
    public void halfWayPlayedIsNotOver() {
        assertIsNotOver("XO  O  X ");
    }

    @Test
    public void isOverHorizontalWin() {
        assertIsOver("XXX      ");
    }

    @Test
    public void isOverVerticalWin() {
        assertIsOver("X  X  X  ");
    }

    @Test
    public void isOverDiagonalWin() {
        assertIsOver("X   X   X");
    }

    @Test
    public void isOverDraw() {
        assertIsOver("XXOXOXOOX");
    }

    @Test
    public void winnerX() {
        assertIsWinner("XXX      ", X);
    }

    @Test
    public void winnerO() {
        assertIsWinner("OOO      ", O);
    }

    private void assertIsNotOver(String boardString) {
        setupGameWithBoard(boardString);
        assertFalse(game.isOver());
    }

    private void assertIsOver(String boardString) {
        setupGameWithBoard(boardString);
        assertTrue(game.isOver());
    }

    private void assertIsWinner(String boardString, String expectedWinner) {
        setupGameWithBoard(boardString);
        assertTrue(game.hasWinner());
        assertEquals(expectedWinner, game.winner().getMark());
    }
}
