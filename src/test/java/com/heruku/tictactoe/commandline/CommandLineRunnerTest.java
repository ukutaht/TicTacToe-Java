package com.heruku.tictactoe.commandline;

import com.heruku.tictactoe.commandline.CommandLineRunner;
import com.heruku.tictactoe.commandline.CommandLineUI;
import com.heruku.tictactoe.core.UI;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

public class CommandLineRunnerTest {
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
    public void testNotifiesOfInvalidMOve(){
        setup("human\nhuman\n1\n1\n9\n2\n8\n4\n7\n");
        runner.play();
        assertThat(out.toString(), containsString("Invalid"));
    }

    @Test
    public void testPlaysAnotherGame(){
        setup("human\nhuman\n1\n9\n2\n8\n4\n7\ny\nhuman\nhuman\n9\n2\n8\n4\n7\n");
        runner.play();
        assertThat(out.toString(), containsString("play again"));
    }
}