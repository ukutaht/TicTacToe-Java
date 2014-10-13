package com.heruku.tictactoe.core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MoveTest {

    @Test
    public void holdsIntegerValueOfTheMove() {
        Move move = new Move(1);

        assertEquals(1, move.getValue());
    }

    @Test
    public void knowsAboutIllegalMoves() {
        Move move = Move.illegal();

        assertIllegal(move);
    }

    @Test
    public void moveWithValueIsLegal() {
        Move move = new Move(99);

        assertIsLegal(move);
    }

    @Test(expected = IllegalAccessError.class)
    public void illegalMovesDontHaveAValue() {
        Move move = Move.illegal();
        move.getValue();
    }

    private void assertIsLegal(Move move) {
        assertTrue(move.isLegal());
    }

    private void assertIllegal(Move move) {
        assertFalse(move.isLegal());
    }

}