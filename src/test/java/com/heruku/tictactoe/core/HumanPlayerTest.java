package com.heruku.tictactoe.core;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class HumanPlayerTest {
    HumanPlayer player;
    Board board;

    public void setup(List moves){
        player = new HumanPlayer(Constants.X, new FakeUI(moves));
        board = new Board("         ");
    }

    @Test
    public void testMakesAMove() {
        setup(Arrays.asList(0));
        board = player.makeMove(board);
        assertEquals(Constants.X, board.squareAt(0));
    }

    @Test
    public void testDoesNotMakeAnInvalidMove() {
        setup(Arrays.asList(100, 8));
        board = player.makeMove(board);
        assertEquals(Constants.X, board.squareAt(8));
    }
}