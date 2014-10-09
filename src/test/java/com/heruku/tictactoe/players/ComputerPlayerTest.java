package com.heruku.tictactoe.players;

import com.heruku.tictactoe.core.Board;
import com.heruku.tictactoe.core.Player;
import org.junit.Before;
import org.junit.Test;

import static com.heruku.tictactoe.core.PlayerMark.O;
import static com.heruku.tictactoe.core.PlayerMark.X;
import static org.junit.Assert.assertEquals;

public class ComputerPlayerTest {
    private Player computer;

    @Before
    public void setup() {
        computer = new ComputerPlayer(O);
    }

    @Test
    public void takesTheWin() {
        Board board = Board.THREE_BY_THREE("OO " +
                                "XX " +
                                " X ");
        assertEquals(2, computer.getMove(board));
    }

    @Test
    public void blockOpponentsWin() {
        Board board = Board.THREE_BY_THREE("XX " +
                                           "   " +
                                           "O  ");
        assertEquals(2, computer.getMove(board));
    }

    @Test
    public void blocksAsX() {
        computer = new ComputerPlayer(X);
        Board board = Board.THREE_BY_THREE("XXO" +
                                           " O " +
                                           "   ");
        assertEquals(6, computer.getMove(board));
    }
}
