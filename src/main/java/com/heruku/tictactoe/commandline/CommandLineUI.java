package com.heruku.tictactoe.commandline;

import com.heruku.tictactoe.core.Constants;
import com.heruku.tictactoe.core.UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class CommandLineUI implements UI {

    private final BufferedReader in;
    private final Writer out;
    private static final String BOARD_TEMPLATE =
            " 1 | 2 | 3 \n"
          + "---+---+---\n"
          + " 4 | 5 | 6 \n"
          + "---+---+---\n"
          + " 7 | 8 | 9 \n";

    public CommandLineUI(Reader in, Writer out){
        this.in = new BufferedReader(in);
        this.out = out;
    }

    @Override
    public void printBoard(String boardString){
        String boardOutput = buildBoardOutput(boardString);
        println(boardOutput);
    }

    @Override
    public void notifyOfInvalidMove(){
        println("Invalid move.\n");
    }

    @Override
    public void notifyWinner(String winner){
        println(winner + " Wins!\n");
    }

    @Override
    public int getMove() {
        println("Your move: ");
        String input = readln();
        if(!input.matches("[0-9]"))
            return -1;
        return Integer.parseInt(input) - 1;
    }

    @Override
    public boolean shouldPlayAgain(){
        println("Enter 'y' to play again");
        String answer = readln();
        return answer.equals("y");
    }

    @Override
    public void notifyDraw() {
        println("It's a draw!\n");
    }

    @Override
    public String getPlayerTypeFor(String mark) {
        println("Select player " + mark + " type(" + Constants.COMPUTER + "/" + Constants.HUMAN +"):");
        return readln();
    }

    private String buildBoardOutput(String boardString) {
        String boardOutput = BOARD_TEMPLATE;
        for (int i = 0; i < boardString.length(); i++) {
            if(boardString.charAt(i) != ' ')
                boardOutput = boardOutput.replaceFirst(Integer.toString(i + 1), "" + boardString.charAt(i));
        }
        return boardOutput;
    }

    private String readln(){
        String input;
        try {
            input = in.readLine();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        if (input == null)
            return "";
        return input;
    }

    private void println(String line) {
        try {
            out.write(line + "\n");
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
