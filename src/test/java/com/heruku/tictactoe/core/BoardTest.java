package com.heruku.tictactoe.core;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.heruku.tictactoe.core.PlayerMark.*;
import static org.junit.Assert.*;

public class BoardTest {

    private Board board;

    private void setupBoard(String boardString) {
        if (boardString.length() == 9)
            this.board = Board.THREE_BY_THREE(boardString);
        else
            this.board = Board.FOUR_BY_FOUR(boardString);
    }

    @Before
    public void setUp() {
        this.board = Board.THREE_BY_THREE();
    }

    @Test
    public void markSquare() {
        Board newBoard = board.markSquare(0, X.toString());
        assertEquals("X", newBoard.squareAt(0));
    }

    @Test
    public void negativeMoveIsInvalid() {
        assertFalse(board.isValidMove(-1));
    }

    @Test
    public void tooLargeMoveIndexIsInvalid() {
        assertFalse(board.isValidMove(9));
    }

    @Test
    public void validMoveOnFourByFour() {
        board = Board.FOUR_BY_FOUR();
        assertTrue(board.isValidMove(15));
    }

    @Test
    public void playedSquareIsInvalid() {
        Board newBoard = board.markSquare(0, X.toString());
        assertFalse(newBoard.isValidMove(0));
    }

    @Test
    public void allMovesValid() {
        assertEquals(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8), board.validMoves());
    }

    @Test
    public void allMovesValidOnFourByFour() {
        board = Board.FOUR_BY_FOUR();
        assertEquals(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15), board.validMoves());
    }

    @Test
    public void someMovesValid() {
        setupBoard("X O      ");
        List<Integer> validMoves = board.validMoves();
        assertEquals(Arrays.asList(1, 3, 4, 5, 6, 7, 8), validMoves);
    }

    @Test
    public void noMovesValid() {
        setupBoard("XXOXOOXXO");
        List<Integer> validMoves = board.validMoves();
        assertTrue(validMoves.isEmpty());
    }

    @Test
    public void isEmptySquare() {
        board = Board.THREE_BY_THREE();
        assertTrue(board.isEmptySquare(0));
    }

    @Test
    public void isNotEmptySquare() {
        setupBoard("X        ");
        assertFalse(board.isEmptySquare(0));
    }

    @Test
    public void horizontalWinner() {
        testIsWinner("XXX      ", "X");
        testIsWinner("   XXX   ", "X");
        testIsWinner("      XXX", "X");
        testIsWinner("      OOO", "O");
    }

    @Test
    public void horizontalWinnerOnFourByFour() {
        testIsWinner("XXXX            ", "X");
        testIsWinner("    XXXX        ", "X");
        testIsWinner("        XXXX    ", "X");
        testIsWinner("            XXXX", "X");
    }

    @Test
    public void verticalWinner() {
        testIsWinner("X  X  X  ", "X");
        testIsWinner(" X  X  X ", "X");
        testIsWinner("  X  X  X", "X");
    }

    @Test
    public void verticalWinnerOnFourByFour() {
        testIsWinner("X   X   X   X   ", "X");
        testIsWinner(" X   X   X   X  ", "X");
        testIsWinner("  X   X   X   X ", "X");
        testIsWinner("   X   X   X   X", "X");
    }

    @Test
    public void isDiagonalWinner() {
        testIsWinner("X   X   X", "X");
        testIsWinner("  X X X  ", "X");
    }

    @Test
    public void diagonalWinnerOnFourByFour() {
        testIsWinner("X    X    X    X", "X");
        testIsWinner("   X  X  X  X   ", "X");
    }

    @Test
    public void isNotFull() {
        setupBoard("     X   ");
        assertFalse(board.isFull());
    }

    @Test
    public void isFull() {
        setupBoard("XXXXXXXXX");
        assertTrue(board.isFull());
    }

    private void testIsWinner(String boardString, String expectedWinner) {
        setupBoard(boardString);
        assertTrue(board.hasWinner());
        assertEquals(expectedWinner, board.winner());
    }
}