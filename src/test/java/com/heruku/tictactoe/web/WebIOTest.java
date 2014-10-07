package com.heruku.tictactoe.web;

import com.heruku.tictactoe.players.HumanPlayer;
import org.junit.Before;
import org.junit.Test;

import static com.heruku.tictactoe.core.Constants.X;
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
        io.setMove(11);

        assertEquals(11, io.getMove());
    }

    @Test
    public void notifiesOfTurn() {
        io.notifyOfTurn(new HumanPlayer(X, io));

       assertThat(io.getMessage(), containsString("X turn"));
    }

    @Test
    public void testNotifyWinner() {
        io.notifyWinner(new HumanPlayer(X, io));

        assertThat(io.getMessage(), containsString("X wins"));
    }

    @Test
    public void testNotifyOfDraw(){
        io.notifyOfDraw();

        assertThat(io.getMessage(), containsString("draw"));
    }
}