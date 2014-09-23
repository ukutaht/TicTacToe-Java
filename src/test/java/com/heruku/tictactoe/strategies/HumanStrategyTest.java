package com.heruku.tictactoe.strategies;

import com.heruku.tictactoe.CommandLineUI;
import com.heruku.tictactoe.core.Board;
import com.heruku.tictactoe.core.Player;
import com.heruku.tictactoe.core.Strategy;
import org.junit.*;
import java.io.*;

import static org.junit.Assert.*;


public class HumanStrategyTest {
    Reader in;
    Writer out;
    CommandLineUI ui;
    Strategy strategy;

    public void setup(String input){
        in  = new StringReader(input);
        out = new StringWriter();
        ui = new CommandLineUI(in, out);
        strategy = new HumanStrategy(ui);
    }

    @Test
    public void testGetsInputFromUI(){
        setup("3\n");
        assertEquals(2, strategy.getMove(new Board("         "), Player.X()));
    }
}