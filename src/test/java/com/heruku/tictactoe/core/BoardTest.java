package com.heruku.tictactoe.core;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BoardTest {

    private Board board;

    private void setupBoard(String boardString) {
        this.board = new Board(boardString);
    }

    @Test
    public void markSquare() {
        setupBoard("        ");
        Board newBoard = board.markSquare(0, "X");
        assertEquals("X", newBoard.squareAt(0));
    }

    @Test
    public void negativeMoveIsInvalid() {
        setupBoard("         ");
        assertFalse(board.isValidMove(-1));
    }

    @Test
    public void tooLargeMoveIndexIsInvalid() {
        setupBoard("         ");
        assertFalse(board.isValidMove(9));
    }

    @Test
    public void playedSquareIsInvalid() {
        setupBoard("         ");
        Board newBoard = board.markSquare(0, "X");
        assertFalse(newBoard.isValidMove(0));
    }

    @Test
    public void allMovesValid() {
        setupBoard("         ");
        List validMoves = board.validMoves();
        assertEquals(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8), validMoves);
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
        setupBoard("        ");
        assertTrue(board.isEmptySquare(0));
    }

    @Test
    public void isNotEmptySquare() {
        setupBoard("X       ");
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
    public void verticalWinner() {
        testIsWinner("X  X  X  ", "X");
        testIsWinner(" X  X  X ", "X");
        testIsWinner("  X  X  X", "X");
        testIsWinner("  O  O  O", "O");
    }

    @Test
    public void isDiagonalWinner() {
        testIsWinner("X   X   X", "X");
        testIsWinner("  X X X  ", "X");
        testIsWinner("  O O O  ", "O");
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