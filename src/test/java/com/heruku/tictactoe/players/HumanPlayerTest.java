package com.heruku.tictactoe.players;

import com.heruku.tictactoe.core.*;
import org.junit.Before;
import org.junit.Test;

import static com.heruku.tictactoe.core.PlayerMark.X;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class HumanPlayerTest {
    public static final int MOVE = 0;
    private HumanPlayer player;
    private Board board;


    @Before
    public void setup() {
        IO io = new FakeIO(asList(MOVE));
        player = new HumanPlayer(X, io);
        board = new ThreeByThreeBoard();
    }

    @Test
    public void makesAMove() {
        Move move = player.getTheMove(board);
        assertEquals(new Move(MOVE), move);
    }
}