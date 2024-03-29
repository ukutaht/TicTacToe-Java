package com.heruku.tictactoe.commandline;

import com.heruku.tictactoe.core.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class CommandLineIO implements IO {

    private final BufferedReader in;
    private final Writer out;

    public CommandLineIO(Reader in, Writer out) {
        this.in = new BufferedReader(in);
        this.out = out;
    }

    public void printBoard(Board board) {
        print("\n\n");
        print(buildBoardOutput(board));
    }

    @Override
    public void showBoard(Board board) {
        print("\n\n");
        print(buildBoardOutput(board));
    }

    @Override
    public void notifyOfInvalidMove() {
        print("Invalid move, try again\n");
    }

    @Override
    public void notifyOfTurn(Player player) {
        print(player.getMark() + " turn:");
    }

    @Override
    public void notifyWinner(Player player) {
        print(player.getMark() + " wins!\n");
    }

    @Override
    public void notifyOfDraw() {
        print("It's a draw!");
    }

    @Override
    public Move getMove() {
        String input = readLine();
        if (!isDigit(input))
            return Move.illegal();
        return new Move(toZeroIndexedInt(input));
    }

    public boolean shouldPlayAgain() {
        print("Enter 'y' to play again\n");
        String answer = readLine();
        return answer.equals("y");
    }

    public GameType getGameType() {
        presentGameTypeOptions();

        int selection = toZeroIndexedInt(readLine());
        return GameType.values()[selection];
    }

    public BoardType getBoardType() {
        presentBoardTypeOptions();

        int selection = toZeroIndexedInt(readLine());
        return BoardType.values()[selection];
    }

    private void presentBoardTypeOptions() {
        print("Select board type:\n");

        for (int i = 0; i < BoardType.values().length; i++) {
            print("\t" + toOneIndexed(i) + ". " + BoardType.values()[i].getName());
            print("\n");
        }
    }

    private void presentGameTypeOptions() {
        print("Select game type:\n");

        for (int i = 0; i < GameType.values().length; i++) {
            print("\t" + toOneIndexed(i) + ". " + GameType.values()[i].getName());
            print("\n");
        }
    }

    private String buildBoardOutput(Board board) {
        String boardOutput = BoardTemplates.getTemplateForBoard(board);
        for (int i = 0; i < board.size(); i++) {
            if (!board.isEmptySquare(i))
                boardOutput = fillSquare(boardOutput, i, board.squareAt(i));
        }
        return boardOutput;
    }

    private String fillSquare(String boardOutput, int index, String mark) {
        String padding;
        if (index < 9)
            padding = "";
        else
            padding = " ";
        return boardOutput.replaceFirst(Integer.toString(index + 1), mark + padding);
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

    private boolean isDigit(String input) {
        return input.matches("\\d+");
    }

    private int toZeroIndexedInt(String line) {
        return Integer.parseInt(line) - 1;
    }

    private int toOneIndexed(int index) {
        return index + 1;
    }
}
