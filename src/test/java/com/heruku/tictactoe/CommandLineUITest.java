package com.heruku.tictactoe;

import org.junit.*;
import java.io.*;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

public class CommandLineUITest {
    Reader in;
    Writer out;
    CommandLineUI ui;

    public void setup(String input){
        in  = new StringReader(input);
        out = new StringWriter();
        ui = new CommandLineUI(in, out);
    }

    @Test
    public void testPrintBoard(){
        setup("");
        ui.printBoard("XXXOOOXXX");

        assertEquals(" X | X | X \n"
                   + "---+---+---\n"
                   + " O | O | O \n"
                   + "---+---+---\n"
                   + " X | X | X \n\n", out.toString());
    }

    @Test
    public void testPrintBoardPrintsIndices(){
        setup("");
        ui.printBoard(" XXO O XX");

        assertEquals(" 1 | X | X \n"
                   + "---+---+---\n"
                   + " O | 5 | O \n"
                   + "---+---+---\n"
                   + " 7 | X | X \n\n", out.toString());
    }

    @Test
    public void testGetMove(){
        setup("3\n");
        assertEquals(2, ui.getMove());
        assertThat(out.toString(), containsString("move"));
    }

    @Test
    public void testGetInputBadInput(){
        setup("whatever\n55\n");
        assertEquals(-1, ui.getMove());
        assertEquals(-1, ui.getMove());
        assertEquals(-1, ui.getMove());
    }

    @Test
    public void testNotifyWinnerX(){
        setup("");
        ui.notifyWinner("X");
        assertThat(out.toString(), containsString("X Wins"));
    }

    @Test
    public void testNotifyWinnerO(){
        setup("");
        ui.notifyWinner("O");
        assertThat(out.toString(), containsString("O Wins"));
    }

    @Test
    public void testNotifyDraw(){
        setup("");
        ui.notifyDraw();
        assertThat(out.toString(), containsString("draw"));
    }

    @Test
    public void testShouldPlayAgain(){
        setup("y\n");
        ui.shouldPlayAgain();
        assertThat(out.toString(), containsString("Play again"));
    }

    @Test
    public void testShouldPlayAgainTrue(){
        setup("y\n");
        assertTrue(ui.shouldPlayAgain());
    }

    @Test
    public void testShouldPlayAgainFalse(){
        setup("whatever\n");
        assertFalse(ui.shouldPlayAgain());
    }

    @Test
    public void testShouldPlayAgainIsFalseWhenNoInputGiven(){
        setup("");
        assertFalse(ui.shouldPlayAgain());
    }

    @Test
    public void testGetsHumanType(){
        setup("human\n");
        assertEquals("human", ui.getPlayerTypeFor("X"));
    }

    @Test
    public void testGetsComputerType(){
        setup("computer\n");
        assertEquals("computer", ui.getPlayerTypeFor("X"));
    }
}