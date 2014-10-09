package com.heruku.tictactoe.core;


import com.heruku.tictactoe.players.HumanPlayer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.heruku.tictactoe.core.PlayerMark.O;
import static com.heruku.tictactoe.core.PlayerMark.X;
import static org.junit.Assert.*;

public class GameTest {

    public static final int MOVE = 0;
    private Game game;
    private FakeIO io;

    @Before
    public void setupEmptyGame() {
       setupGameWithBoard("         ");
    }

    public void setupGameWithBoard(String boardString) {
        List<Player> players = new ArrayList<Player>();
        players.add(new HumanPlayer(X, new FakeIO(Arrays.asList(MOVE))));
        players.add(new HumanPlayer(O, new FakeIO(Arrays.asList(MOVE))));
        Board board = Board.THREE_BY_THREE(boardString);
        this.io = new FakeIO();
        this.game = new Game(board, players, io);
    }

    @Test
    public void initShowsBoardAndTellsTurn() {
        game.start();
        assertIOReceived("showBoard");
        assertIOReceived("notifyOfTurn");
    }

    @Test
    public void playMoveMarksSquare() {
        game.playMove();
        assertEquals(X.toString(), game.board.squareAt(MOVE));
    }

    @Test
    public void playMoveAdvancesTurn() {
        game.playMove();
        assertEquals(O, game.currentPlayer().getMark());
    }

    @Test
    public void playMoveDoesNotMarkSquareIfInvalid() {
        game.playMove();
        game.playMove();
        assertEquals(X.toString(), game.board.squareAt(MOVE));
    }

    @Test
    public void showsBoard() {
        game.playMove();
        assertIOReceived("showBoard");
    }

    @Test
    public void notifiesIOofTheTurn() {
        game.playMove();
        assertIOReceived("notifyOfTurn");
    }

    @Test
    public void notifiesIOThatGameHasWinner() {
        setupGameWithBoard(" XX O  O ");
        game.playMove();
        assertIOReceived("notifyWinner");
    }

    @Test
    public void notifiesIOThatGameHasdraw() {
        setupGameWithBoard(" OXXOXOXO");
        game.playMove();
        assertIOReceived("notifyOfDraw");
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

    private void assertIsWinner(String boardString, PlayerMark expectedWinner) {
        setupGameWithBoard(boardString);
        assertTrue(game.hasWinner());
        assertEquals(expectedWinner, game.winner().getMark());
    }

    private void assertIOReceived(String method) {
        assertTrue(io.calledMethods().contains(method));
    }
}
