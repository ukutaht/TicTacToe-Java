package com.heruku.tictactoe.commandline;

import com.heruku.tictactoe.core.Board;
import com.heruku.tictactoe.core.Move;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import static com.heruku.tictactoe.core.BoardType.FOUR_BY_FOUR;
import static com.heruku.tictactoe.core.BoardType.THREE_BY_THREE;
import static com.heruku.tictactoe.core.GameType.*;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

public class CommandLineIOTest {
    private Writer out;
    private CommandLineIO ui;

    void setup(String input) {
        Reader in = new StringReader(input);
        out = new StringWriter();
        ui = new CommandLineIO(in, out);
    }

    @Test
    public void showsBoard() {
        setup("");
        ui.printBoard(Board.THREE_BY_THREE(" XXO O XX"));

        assertEquals("\n\n 1 | X | X \n"
                   + "---+---+---\n"
                   + " O | 5 | O \n"
                   + "---+---+---\n"
                   + " 7 | X | X \n\n", out.toString());
    }

    @Test
    public void showsFourByFourBoard() {
        setup("");
        ui.printBoard(Board.FOUR_BY_FOUR(" XXO O XXO     X"));

        assertEquals("\n\n 1 | X | X | O \n"
                   + "---+---+---+---\n"
                   + " 5 | O | 7 | X \n"
                   + "---+---+---+---\n"
                   + " X | O | 11| 12\n"
                   + "---+---+---+---\n"
                   + " 13| 14| 15| X \n\n", out.toString());
    }

    @Test
    public void notifiesOfInvalidMove() {
        setup("");
        ui.notifyOfInvalidMove();

        assertThat(out.toString(), containsString("Invalid"));
    }

    @Test
    public void getMoveDigitsAreLegal() {
        setup("4\n");
        Move move = ui.getMove();

        assertTrue(move.isLegal());
    }

    @Test
    public void getMoveConvertsToZeroIndexed() {
        setup("4\n");
        Move move = ui.getMove();

        assertEquals(3, move.getValue());
    }

    @Test
    public void getMoveNonDigitsAreIllegal() {
        setup("watman\n");
        Move move = ui.getMove();

        assertFalse(move.isLegal());
    }

    @Test
    public void getMoveEmptyIsIllegal() {
        setup("\n");
        Move move = ui.getMove();

        assertFalse(move.isLegal());
    }

    @Test
    public void shouldPlayAgainTrue() {
        setup("y\n");
        assertTrue(ui.shouldPlayAgain());
    }

    @Test
    public void shouldPlayAgainFalse() {
        setup("whatever\n");
        assertFalse(ui.shouldPlayAgain());
    }

    @Test
    public void shouldPlayAgainIsFalseWhenNoInputGiven() {
        setup("");
        assertFalse(ui.shouldPlayAgain());
    }

    @Test
    public void humanVsHuman() {
        setup("1\n");
        assertEquals(HUMAN_VS_HUMAN, ui.getGameType());
    }

    @Test
    public void humanVsComputer() {
        setup("2\n");
        assertEquals(HUMAN_VS_COMPUTER, ui.getGameType());
    }

    @Test
    public void computerVsHuman() {
        setup("3\n");
        assertEquals(COMPUTER_VS_HUMAN, ui.getGameType());
    }

    @Test
    public void computerVsComputer() {
        setup("4\n");
        assertEquals(COMPUTER_VS_COMPUTER, ui.getGameType());
    }

    @Test
    public void threeByThree() {
        setup("1\n");
        assertEquals(THREE_BY_THREE, ui.getBoardType());
    }

    @Test
    public void FourByFour() {
        setup("2\n");
        assertEquals(FOUR_BY_FOUR, ui.getBoardType());
    }
}