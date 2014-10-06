package com.heruku.tictactoe.commandline;

import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import static com.heruku.tictactoe.core.Constants.X;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

public class CommandLineRunnerTest {
    private Writer out;
    private CommandLineRunner runner;

    void setupWithStringIO(String input) {
        Reader in = new StringReader(input);
        out = new StringWriter();
        CommandLineIO io = new CommandLineIO(in, out);
        runner = new CommandLineRunner(io);
    }

    void setupFakeHumanGame(String moves) {
        setupWithStringIO("1\n1\n" + moves + "n\n");
    }

    @Test
    public void XWins() {
        setupFakeHumanGame("1\n9\n2\n8\n3\n");
        runner.play();
        assertEquals("X", runner.game.winner());
    }

    @Test
    public void OWins() {
        setupFakeHumanGame("1\n9\n2\n8\n4\n7\n");
        runner.play();
        assertEquals("O", runner.game.winner());
    }

    @Test
    public void drawGame() {
        setupFakeHumanGame("1\n2\n3\n5\n4\n6\n8\n7\n9\n");
        runner.play();
        assertTrue(runner.game.hasDraw());
    }

    @Test
    public void notifiesOfInvalidMOve() {
        setupWithStringIO("1\n1\n1\n1\n9\n2\n8\n4\n7\n");
        runner.play();
        assertThat(out.toString(), containsString("Invalid"));
    }

    @Test
    public void winnerGetsPrinted() {
        setupWithStringIO("1\n1\n9\n2\n8\n4\n7\n");
        runner.play();
        assertThat(out.toString(), containsString(X + " Wins!"));
    }

    @Test
    public void drawGetsPrinted() {
        setupWithStringIO("1\n1\n1\n2\n3\n5\n4\n6\n8\n7\n9\n");
        runner.play();
        assertThat(out.toString(), containsString("draw"));
    }

    @Test
    public void playsAnotherGame() {
        setupWithStringIO("1\n1\n1\n9\n2\n8\n4\n7\ny\n1\n1\n9\n2\n8\n4\n7\n");
        runner.play();
        assertThat(out.toString(), containsString("play again"));
    }

    @Test
    public void playsFourByFourGame() {
        setupWithStringIO("1\n2\n9\n13\n10\n14\n11\n15\n12\n\n");
        runner.play();
        assertThat(out.toString(), containsString(X + " Wins"));
    }
}