package com.heruku.tictactoe.commandline;

import com.heruku.tictactoe.core.Game;
import com.heruku.tictactoe.core.GameFactory;
import com.heruku.tictactoe.core.GameType;
import com.heruku.tictactoe.core.UI;

import java.io.*;

public class CommandLineRunner {
    Game game;
    private final UI ui;

    public CommandLineRunner(UI ui) {
        this.ui = ui;
    }

    public void play() {
        boolean playAgain = true;
        while (playAgain) {
            buildGame();
            playGame();
            playAgain = ui.shouldPlayAgain();
        }
    }

    private void buildGame() {
        GameType gameType = ui.getGameType();
        game = new GameFactory(ui).forSelection(gameType);
    }

    private void playGame() {
        while (!game.isOver()) {
            ui.update(game);
            game.playMove();
        }
        ui.notifyWinner(game);
    }

    public static void main(String[] args) {
        Reader in = new InputStreamReader(System.in);
        Writer out = new BufferedWriter(new OutputStreamWriter(System.out));
        CommandLineUI ui = new CommandLineUI(in, out);
        new CommandLineRunner(ui).play();
    }
}
