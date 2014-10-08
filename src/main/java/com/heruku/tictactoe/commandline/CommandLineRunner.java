package com.heruku.tictactoe.commandline;

import com.heruku.tictactoe.core.*;

import java.io.*;

public class CommandLineRunner {
    Game game;
    private final CommandLineIO IO;

    public CommandLineRunner(CommandLineIO io) {
        this.IO = io;
    }

    public void play() {
        boolean playAgain = true;
        while (playAgain) {
            buildGame();
            playGame();
            playAgain = IO.shouldPlayAgain();
        }
    }

    private void buildGame() {
        GameType gameType = IO.getGameType();
        BoardType boardType = IO.getBoardType();
        game = new GameFactory(IO).build(boardType, gameType);
    }

    private void playGame() {
        game.start();
        while (!game.isOver()) {
            game.playMove();
        }
    }

    public static void main(String[] args) {
        Reader in = new InputStreamReader(System.in);
        Writer out = new BufferedWriter(new OutputStreamWriter(System.out));
        CommandLineIO ui = new CommandLineIO(in, out);
        new CommandLineRunner(ui).play();
    }
}
