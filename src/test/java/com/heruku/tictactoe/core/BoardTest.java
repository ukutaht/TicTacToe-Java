package com.heruku.tictactoe.core;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.heruku.tictactoe.core.PlayerMark.X;
import static org.junit.Assert.*;

public class BoardTest {

    private Board board;

    private void setupBoard(String boardString) {
        if (boardString.length() == 9)
            this.board = new ThreeByThreeBoard(boardString);
        else
            this.board = new FourByFourBoard(boardString);
    }

    @Before
    public void setUp() {
        this.board = new ThreeByThreeBoard();
    }


    @Test
    public void markSquareWithMove() {
        Move move = new Move(0);
        Board newBoard = board.markSquare(move, X.toString());

        assertEquals("X", newBoard.squareAt(0));
    }

    @Test
    public void validMoveIsValid() {
        Move move = new Move(4);

        assertTrue(board.isValidMove(move));
    }

    @Test
    public void negativeMoveIsInvalid() {
        Move move = new Move(-1);

        assertFalse(board.isValidMove(move));
    }

    @Test
    public void tooLargeMoveIndexIsInvalid() {
        Move move = new Move(9);

        assertFalse(board.isValidMove(move));
    }

    @Test
    public void validMoveOnFourByFour() {
        board = new FourByFourBoard();
        Move move = new Move(15);

        assertTrue(board.isValidMove(move));
    }

    @Test
    public void playedSquareIsInvalid() {
        Move move = new Move(0);
        Board newBoard = board.markSquare(move, X.toString());

        assertFalse(newBoard.isValidMove(move));
    }

    @Test
    public void allMovesValid() {
        assertEquals(makeMoves(0, 1, 2, 3, 4, 5, 6, 7, 8), board.validMoves());
    }

    @Test
    public void allMovesValidOnFourByFour() {
        board = new FourByFourBoard();

        assertEquals(makeMoves(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15), board.validMoves());
    }

    @Test
    public void someMovesValid() {
        setupBoard("X O      ");
        List<Move> validMoves = board.validMoves();

        assertEquals(makeMoves(1, 3, 4, 5, 6, 7, 8), validMoves);
    }

    @Test
    public void noMovesValid() {
        setupBoard("XXOXOOXXO");
        List<Move> validMoves = board.validMoves();

        assertTrue(validMoves.isEmpty());
    }

    @Test
    public void isEmptySquare() {
        board = new ThreeByThreeBoard();
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

    private List<Move> makeMoves(int... moves) {
        List<Move> moveList = new ArrayList<Move>();

        for (int i = 0; i < moves.length; i++) {
            moveList.add(new Move(moves[i]));
        }
        return moveList;
    }
}