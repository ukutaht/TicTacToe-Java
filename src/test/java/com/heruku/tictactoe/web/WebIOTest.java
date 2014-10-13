package com.heruku.tictactoe.web;

import com.heruku.tictactoe.core.Board;
import com.heruku.tictactoe.core.Move;
import com.heruku.tictactoe.players.HumanPlayer;
import org.junit.Before;
import org.junit.Test;

import static com.heruku.tictactoe.core.PlayerMark.X;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class WebIOTest {

    private WebIO io;

    @Before
    public void setup() {
        this.io = new WebIO();
    }

    @Test
    public void setsAndGetsMove() {
        Move move = new Move(11);
        io.setMove(move);

        assertEquals(move, io.getMove());
    }

    @Test
    public void notifiesOfTurn() {
        io.notifyOfTurn(new HumanPlayer(X, io));

       assertThat(io.getNotification(), containsString("X turn"));
    }

    @Test
    public void testNotifyWinner() {
        io.notifyWinner(new HumanPlayer(X, io));

        assertThat(io.getNotification(), containsString("X wins"));
    }

    @Test
    public void testNotifyOfDraw(){
        io.notifyOfDraw();

        assertThat(io.getNotification(), containsString("draw"));
    }

    @Test
    public void rendersBoardHtml() {
        io.showBoard(Board.THREE_BY_THREE("XXX      "));

        assertThat(io.boardMarkup(1), containsString("div"));
    }
}