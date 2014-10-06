package com.heruku.tictactoe.players;

import com.heruku.tictactoe.core.Board;
import com.heruku.tictactoe.core.Player;
import org.junit.Before;
import org.junit.Test;

import static com.heruku.tictactoe.core.Constants.O;
import static com.heruku.tictactoe.core.Constants.X;
import static org.junit.Assert.assertEquals;

public class ComputerPlayerTest {
    private Player computer;

    @Before
    public void setup() {
        computer = new ComputerPlayer(O);
    }

    @Test
    public void takesTheWin() {
        Board board = new Board("OO " +
                                "XX " +
                                " X ");
        assertEquals(2, computer.getMove(board));
    }

    @Test
    public void blockOpponentsWin() {
        Board board = new Board("XX " +
                                "   " +
                                "O  ");
        assertEquals(2, computer.getMove(board));
    }

    @Test
    public void blocksAsX() {
        computer = new ComputerPlayer(X);
        Board board = new Board("XXO" +
                                " O " +
                                "   ");
        assertEquals(6, computer.getMove(board));
    }
}
