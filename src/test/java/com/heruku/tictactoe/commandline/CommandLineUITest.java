package com.heruku.tictactoe.commandline;

import com.heruku.tictactoe.core.Board;
import com.heruku.tictactoe.core.Game;
import com.heruku.tictactoe.core.GameType;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

public class CommandLineUITest {
    private Writer out;
    private CommandLineUI ui;

    void setup(String input) {
        Reader in = new StringReader(input);
        out = new StringWriter();
        ui = new CommandLineUI(in, out);
    }

    public Game gameFromBoardState(String boardState) {
        return new Game(new Board(boardState));
    }

    @Test
    public void updateShowsBoard() {
        setup("");
        ui.update(gameFromBoardState(" XXO O XX"));

        assertEquals(" 1 | X | X \n"
                   + "---+---+---\n"
                   + " O | 5 | O \n"
                   + "---+---+---\n"
                   + " 7 | X | X \n\n", out.toString());
    }

    @Test
    public void notifiesOfInvalidMove() {
        setup("");
        ui.notifyOfInvalidMove();

        assertThat(out.toString(), containsString("Invalid"));
    }

    @Test
    public void getMove() {
        setup("3\n");
        assertEquals(2, ui.getMove());
        assertThat(out.toString(), containsString("move"));
    }

    @Test
    public void getInputBadInput() {
        setup("whatever\n55\n\n");
        assertEquals(-1, ui.getMove());
        assertEquals(-1, ui.getMove());
        assertEquals(-1, ui.getMove());
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
        assertEquals(GameType.HUMAN_VS_HUMAN, ui.getGameType());
    }

    @Test
    public void humanVsComputer() {
        setup("2\n");
        assertEquals(GameType.HUMAN_VS_COMPUTER, ui.getGameType());
    }

    @Test
    public void computerVsHuman() {
        setup("3\n");
        assertEquals(GameType.COMPUTER_VS_HUMAN, ui.getGameType());
    }

    @Test
    public void computerVsComputer() {
        setup("4\n");
        assertEquals(GameType.COMPUTER_VS_COMPUTER, ui.getGameType());
    }
}