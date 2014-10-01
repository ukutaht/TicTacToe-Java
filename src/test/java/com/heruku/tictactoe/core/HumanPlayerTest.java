package com.heruku.tictactoe.core;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class HumanPlayerTest {
    private HumanPlayer player;
    private Board board;

    @Before
    public void setup() {
        UI ui = new FakeUI(Arrays.asList(0));
        player = new HumanPlayer(Constants.X, ui);
        board = new Board("         ");
    }

    @Test
    public void testMakesAMove() {
        int move = player.getMove(board);
        assertEquals(0, move);
    }
}