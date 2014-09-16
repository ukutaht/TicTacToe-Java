package com.heruku.tictactoe.core;
import org.junit.*;
import static org.junit.Assert.*;

public class BoardTest {

    Board board;

    private void setupBoard(String boardString){
        this.board = new Board(boardString);
    }

    @Test
    public void testMarkSquare(){
        setupBoard("        ");
        Board newBoard = board.markSquare(0, "X");
        assertEquals("X", newBoard.squareAt(0));
    }

    @Test
    public void testNegativeMoveIsInvalid(){
        setupBoard("         ");
        assertFalse(board.isValidMove(-1));
    }

    @Test
    public void testTooLargeMoveIndexIsInvalid(){
        setupBoard("         ");
        assertFalse(board.isValidMove(9));
    }

    @Test
    public void testPlayedSquareIsInvalid(){
        setupBoard("         ");
        Board newBoard = board.markSquare(0, "X");
        assertFalse(newBoard.isValidMove(0));
    }

    @Test
    public void testIsEmptySquare(){
        setupBoard("        ");
        assertTrue(board.isEmptySquare(0));
    }

    @Test
    public void testIsNotEmptySquare(){
        setupBoard("X       ");
        assertFalse(board.isEmptySquare(0));
    }

    @Test
    public void testHorizontalWinner(){
        testIsWinner("XXX      ", "X");
        testIsWinner("   XXX   ", "X");
        testIsWinner("      XXX", "X");
        testIsWinner("      OOO", "O");
    }

    @Test
    public void testVerticalWinner(){
        testIsWinner("X  X  X  ", "X");
        testIsWinner(" X  X  X ", "X");
        testIsWinner("  X  X  X", "X");
        testIsWinner("  O  O  O", "O");
    }

    @Test
    public void testIsDiagonalWinner(){
        testIsWinner("X   X   X", "X");
        testIsWinner("  X X X  ", "X");
        testIsWinner("  O O O  ", "O");
    }

    @Test
    public void testIsNotFull(){
        setupBoard("     X   ");
        assertFalse(board.isFull());
    }

    @Test
    public void testIsFull(){
        setupBoard("XXXXXXXXX");
        assertTrue(board.isFull());
    }

    private void testIsWinner(String boardString, String expectedWinner) {
        setupBoard(boardString);
        assertTrue(board.hasWinner());
        assertEquals(expectedWinner, board.winner());
    }
}