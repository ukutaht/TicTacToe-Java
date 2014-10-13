package com.heruku.tictactoe.players;

import com.heruku.tictactoe.core.Board;
import com.heruku.tictactoe.core.Move;
import com.heruku.tictactoe.core.Player;
import com.heruku.tictactoe.core.ThreeByThreeBoard;
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
        Board board = new ThreeByThreeBoard("OO " +
                "XX " +
                " X ");
        Move expected = new Move(2);

        assertEquals(expected, computer.getTheMove(board));
    }

    @Test
    public void blockOpponentsWin() {
        Board board = new ThreeByThreeBoard("XX " +
                "   " +
                "O  ");
        Move expected = new Move(2);
        assertEquals(expected, computer.getTheMove(board));
    }

    @Test
    public void blocksAsX() {
        computer = new ComputerPlayer(X);
        Board board = new ThreeByThreeBoard("XXO" +
                " O " +
                "   ");
        Move expected = new Move(6);
        assertEquals(expected, computer.getTheMove(board));
    }
}
