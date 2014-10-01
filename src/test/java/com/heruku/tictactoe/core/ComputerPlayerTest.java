package com.heruku.tictactoe.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ComputerPlayerTest {
    private Player computer;

    @Before
    public void setup() {
        computer = new ComputerPlayer(Constants.O);
    }

    @Test
    public void testTakesTheWin() {
        Board board = new Board("OO " +
                "XX " +
                " X ");
        assertEquals(2, computer.getMove(board));
    }

    @Test
    public void testBlockOpponentsWin() {
        Board board = new Board("XX " +
                " O " +
                "   ");
        assertEquals(2, computer.getMove(board));
    }
}
