package com.heruku.tictactoe.core;

import com.heruku.tictactoe.commandline.CommandLineUI;
import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import static org.junit.Assert.*;

public class GameBuilderTest {

    Reader in;
    Writer out;
    UI ui;
    GameBuilder gameBuilder;

    @Before
    public void setup(){
        in  = new StringReader("");
        out = new StringWriter();
        ui = new CommandLineUI(in, out);
        gameBuilder = new GameBuilder(ui);
    }

    @Test
    public void startsWithPlayerX(){
        Game game = gameBuilder.withPlayerX("human").withPlayerO("computer").build();
        assertEquals("X", game.currentPlayer().getMark());
    }
}