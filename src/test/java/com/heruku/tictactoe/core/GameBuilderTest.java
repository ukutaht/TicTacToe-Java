package com.heruku.tictactoe.core;

import com.heruku.tictactoe.CommandLineUI;
import com.heruku.tictactoe.strategies.HumanStrategy;
import com.heruku.tictactoe.strategies.UnbeatableAIStrategy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Computer;

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
    public void testAddsPlayers(){
        gameBuilder.withPlayerX("computer").withPlayerO("human").build();
        assertTrue(Player.X().strategy instanceof UnbeatableAIStrategy);
        assertTrue(Player.O().strategy instanceof HumanStrategy);
    }

    @Test
    public void startsWithPlayerX(){
        Game game = gameBuilder.withPlayerX("human").withPlayerO("computer").build();
        assertEquals(Player.X(), game.currentPlayer);
    }
}