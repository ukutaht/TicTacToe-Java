package com.heruku.tictactoe.commandline;

import com.heruku.tictactoe.core.FakeUI;
import com.heruku.tictactoe.core.UI;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

import static com.heruku.tictactoe.core.Constants.X;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

public class CommandLineRunnerTest {
    private Writer out;
    private UI ui;
    private CommandLineRunner runner;

    void setupWithStringIO(String input) {
        Reader in = new StringReader(input);
        out = new StringWriter();
        ui = new CommandLineUI(in, out);
        runner = new CommandLineRunner(ui);
    }

    void setupFakeHumanGame(List<Integer> moves) {
        ui = new FakeUI(moves);
        runner = new CommandLineRunner(ui);
    }

    @Test
    public void XWins() {
        setupFakeHumanGame(Arrays.asList(0, 8, 1, 7, 2));
        runner.play();
        assertEquals("X", runner.game.winner());
    }

    @Test
    public void OWins() {
        setupFakeHumanGame(Arrays.asList(0, 8, 1, 7, 3, 6));
        runner.play();
        assertEquals("O", runner.game.winner());
    }

    @Test
    public void drawGame() {
        setupFakeHumanGame(Arrays.asList(0, 1, 2, 4, 3, 5, 7, 6, 8));
        runner.play();
        assertTrue(runner.game.hasDraw());
    }

    @Test
    public void notifiesOfInvalidMOve() {
        setupWithStringIO("1\n1\n1\n9\n2\n8\n4\n7\n");
        runner.play();
        assertThat(out.toString(), containsString("Invalid"));
    }

    @Test
    public void winnerGetsPrinted() {
        setupWithStringIO("1\n9\n2\n8\n4\n7\n");
        runner.play();
        assertThat(out.toString(), containsString(X + " Wins!"));
    }

    @Test
    public void drawgetsPrinted() {
        setupWithStringIO("1\n1\n2\n3\n5\n4\n6\n8\n7\n9\n");
        runner.play();
        assertThat(out.toString(), containsString("draw"));
    }

    @Test
    public void playsAnotherGame() {
        setupWithStringIO("1\n1\n9\n2\n8\n4\n7\ny\n1\n9\n2\n8\n4\n7\n");
        runner.play();
        assertThat(out.toString(), containsString("play again"));
    }
}