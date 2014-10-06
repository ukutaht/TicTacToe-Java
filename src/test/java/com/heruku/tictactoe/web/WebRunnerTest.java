package com.heruku.tictactoe.web;

import com.heruku.tictactoe.core.Board;
import com.heruku.tictactoe.core.Constants;
import com.heruku.tictactoe.core.Game;
import com.heruku.tictactoe.core.Player;
import com.heruku.tictactoe.players.HumanPlayer;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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
        players = Arrays.<Player>asList(new HumanPlayer(Constants.X, io));
        game = new Game(players, io);
        runner = new WebRunner(game, io);
    }

    @Test
    public void makesMove() {
        runner.makeMove(0);
        assertEquals(game.getBoard().squareAt(0), Constants.X);
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
    public void setsGameOVerNotification() {
        game = new Game(new Board("X O" +
                                  "O O" +
                                  "X X"), players, io);
        runner = new WebRunner(game, io);

        runner.makeMove(7);
        assertThat(runner.getMessage(), containsString("won"));
    }


    @Test
    public void doesNotMakeMoveWhenGameIsOver() {
        game = new Game(new Board("X O" +
                                  "OOO" +
                                  "X X"), players, io);
        runner = new WebRunner(game, io);

        runner.makeMove(7);
        assertTrue(game.getBoard().isEmptySquare(7));
    }
}