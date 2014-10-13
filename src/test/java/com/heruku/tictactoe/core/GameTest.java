package com.heruku.tictactoe.core;


import com.heruku.tictactoe.players.HumanPlayer;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.heruku.tictactoe.core.BoardType.THREE_BY_THREE;
import static com.heruku.tictactoe.core.GameType.HUMAN_VS_HUMAN;
import static com.heruku.tictactoe.core.PlayerMark.O;
import static com.heruku.tictactoe.core.PlayerMark.X;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class GameTest {

    public static final int MOVE = 0;
    private Game emptyGame;
    private FakeIO io;

    @Before
    public void setup() {
        io = new FakeIO();
        emptyGame = new GameFactory(io).build(THREE_BY_THREE, HUMAN_VS_HUMAN);
    }

    public Game setupGameWithMoves(List<Integer> moves) {
        io = new FakeIO(moves);
        return new GameFactory(io).build(THREE_BY_THREE, HUMAN_VS_HUMAN);
    }

    private Game setupGameWithMoves(Move...moves) {
        io = new FakeIO(moves);
        return new GameFactory(io).build(THREE_BY_THREE, HUMAN_VS_HUMAN);
    }

    public Game setupGameWithBoard(String boardString) {
        io = new FakeIO();
        List<Player> players = Arrays.<Player>asList(new HumanPlayer(X, io), new HumanPlayer(O, io));
        Board board = new ThreeByThreeBoard(boardString);
        return new Game(board, players, io);
    }

    @Test
    public void initShowsBoardAndTellsTurn() {
        emptyGame.start();
        assertIOReceived("showBoard");
        assertIOReceived("notifyOfTurn");
    }

    @Test
    public void playMoveMarksSquare() {
        Game game = setupGameWithMoves(asList(0));
        game.playMove();

        assertEquals(X.toString(), game.board.squareAt(0));
    }

    @Test
    public void playMoveAdvancesTurn() {
        Game game = setupGameWithMoves(asList(0));

        assertEquals(X, game.currentPlayer().getMark());
        game.playMove();
        assertEquals(O, game.currentPlayer().getMark());
    }

    @Test
    public void playMoveDoesNotMarkSquareIfInvalid() {
        Game game = setupGameWithMoves(asList(0, 0));
        game.playMove();

        assertEquals(X, game.playerAt(0).getMark());
        game.playMove();
        assertEquals(X, game.playerAt(0).getMark());
    }

    @Test
    public void playMoveDoesNotMarkSquareIfMoveIsIllegal() {
        Game game = setupGameWithMoves(new Move(0), Move.illegal());
        game.playMove();

        assertEquals(X, game.playerAt(0).getMark());
        game.playMove();
        assertEquals(X, game.playerAt(0).getMark());
    }

    @Test
    public void showsBoardWhenPlayingMove() {
        Game game = setupGameWithMoves(asList(0));
        game.playMove();

        assertIOReceived("showBoard");
    }

    @Test
    public void notifiesIOofTheTurnWhenNoWinnerOrDraw() {
        emptyGame.notifyIO();
        assertIOReceived("notifyOfTurn");
    }

    @Test
    public void notifiesIOThatGameHasWinner() {
        Game game = setupGameWithBoard("XXX O  O ");
        game.notifyIO();

        assertIOReceived("notifyWinner");
    }

    @Test
    public void notifiesIOThatGameHasdraw() {
        Game game = setupGameWithBoard("XOXXOXOXO");
        game.notifyIO();

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
        Game game = setupGameWithBoard(boardString);
        assertFalse(game.isOver());
    }

    private void assertIsOver(String boardString) {
        Game game = setupGameWithBoard(boardString);
        assertTrue(game.isOver());
    }

    private void assertIsWinner(String boardString, PlayerMark expectedWinner) {
        Game game = setupGameWithBoard(boardString);
        assertTrue(game.hasWinner());
        assertEquals(expectedWinner, game.winner().getMark());
    }

    private void assertIOReceived(String method) {
        assertTrue(io.calledMethods().contains(method));
    }
}
