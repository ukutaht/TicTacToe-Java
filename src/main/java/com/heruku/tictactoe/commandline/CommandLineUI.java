package com.heruku.tictactoe.commandline;

import com.heruku.tictactoe.core.Game;
import com.heruku.tictactoe.core.GameType;
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
                    + " 7 | 8 | 9 \n\n";

    public CommandLineUI(Reader in, Writer out) {
        this.in = new BufferedReader(in);
        this.out = out;
    }

    @Override
    public void update(Game game) {
        printBoard(game.boardString());
    }

    @Override
    public void notifyWinner(Game game) {
        if (game.hasWinner())
            print(game.winner() + " Wins!\n");
        else
            print("It's a draw!\n");
    }

    @Override
    public void notifyOfInvalidMove() {
        print("Invalid move, try again.\n");
    }

    @Override
    public int getMove() {
        print("Your move: ");
        String input = readLine();
        if (!input.matches("[0-9]"))
            return -1;
        return Integer.parseInt(input) - 1;
    }

    @Override
    public boolean shouldPlayAgain() {
        print("Enter 'y' to play again\n");
        String answer = readLine();
        return answer.equals("y");
    }

    @Override
    public GameType getGameType() {
        presentGameTypeOptions();

        int selection = Integer.parseInt(readLine()) - 1;
        return GameType.values()[selection];
    }

    private void presentGameTypeOptions() {
        print("Select game type:\n");

        for (int i = 0; i < GameType.values().length; i++) {
            print("\t" + (i + 1) + ". " + GameType.values()[i].getName());
            print("\n");
        }
    }

    private void printBoard(String boardString) {
        print(buildBoardOutput(boardString));
    }

    private String buildBoardOutput(String boardString) {
        String boardOutput = BOARD_TEMPLATE;
        for (int i = 0; i < boardString.length(); i++) {
            if (boardString.charAt(i) != ' ')
                boardOutput = boardOutput.replaceFirst(Integer.toString(i + 1), "" + boardString.charAt(i));
        }
        return boardOutput;
    }

    private String readLine() {
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

    private void print(String line) {
        try {
            out.write(line);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
