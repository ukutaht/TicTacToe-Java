package com.heruku.tictactoe;



import com.heruku.tictactoe.core.Game;
import com.heruku.tictactoe.core.UI;
import com.heruku.tictactoe.strategies.HumanStrategy;
import com.heruku.tictactoe.core.Player;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

public class CommandLineRunnerTest {
    Game game;
    Reader in;
    Writer out;
    UI ui;
    CommandLineRunner runner;

    public void setup(String input){
        in  = new StringReader(input);
        out = new StringWriter();
        ui = new CommandLineUI(in, out);
        runner = new CommandLineRunner(ui);
    }

    @Test
    public void testXWins(){
        setup("human\nhuman\n1\n9\n2\n8\n3\n");
        runner.play();
        assertEquals("X", runner.game.winner());
        assertThat(out.toString(), containsString("X Wins"));
    }

    @Test
    public void testOWins(){
        setup("human\nhuman\n1\n9\n2\n8\n4\n7\n");
        runner.play();
        assertEquals("O", runner.game.winner());
        assertThat(out.toString(), containsString("O Wins"));
    }

    @Test
    public void testNotifiesOfInvalidMOve(){
        setup("human\nhuman\n1\n1\n9\n2\n8\n4\n7\n");
        runner.play();
        assertThat(out.toString(), containsString("Invalid"));
    }

    @Test
    public void testPlaysAnotherGame(){
        setup("human\nhuman\n1\n9\n2\n8\n4\n7\ny\nhuman\nhuman\n9\n2\n8\n4\n7\n");
        runner.play();
        assertThat(out.toString(), containsString("Play again"));
    }
}