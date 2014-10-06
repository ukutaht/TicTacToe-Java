package com.heruku.tictactoe.web;

import com.heruku.tictactoe.core.Board;
import com.heruku.tictactoe.core.Game;
import com.heruku.tictactoe.core.Player;
import com.heruku.tictactoe.players.HumanPlayer;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.heruku.tictactoe.core.Constants.O;
import static com.heruku.tictactoe.core.Constants.X;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WebRunnerTest {

    private WebIO io;
    private Game game;
    private WebRunner runner;
    private List<Player> players;

    @Before
    public void setUp(){
        io = new WebIO();
        players = Arrays.<Player>asList(new HumanPlayer(X, io), new HumanPlayer(O, io));
        game = new Game(players, io);
        runner = new WebRunner(game, io);
    }

    private void setupRunner(String boardString) {
        game = new Game(new Board(boardString), players, io);
        runner = new WebRunner(game, io);
    }

    @Test
    public void makesMove() {
        runner.makeMove(0);
        assertEquals(game.getBoard().squareAt(0), X);
    }

    @Test
    public void setsTurnNotification() {
        runner.makeMove(0);
        assertThat(runner.getMessage(), containsString("turn"));
    }

    @Test
    public void makesNewGame() {
        runner.makeMove(0);
        runner.newGameFromString("HUMAN_VS_HUMAN");
        assertTrue(runner.getBoard().isEmptySquare(0));
    }

    @Test
    public void notifiesOfWin() {
        setupRunner("X O" +
                    "O O" +
                    "X X");

        runner.makeMove(7);
        assertThat(runner.getMessage(), containsString("wins"));
    }

    @Test
    public void notifiesOfDraw() {
        setupRunner(" OO" +
                    "OOX" +
                    "XXO");

        runner.makeMove(0);
        assertThat(runner.getMessage(), containsString("draw"));
    }
    @Test
    public void doesNotMakeMoveWhenGameIsOver() {
        setupRunner("X O" +
                    "OOO" +
                    "X X");

        runner.makeMove(7);
        assertTrue(game.getBoard().isEmptySquare(7));
    }
 }