package com.heruku.tictactoe.commandline;

import com.heruku.tictactoe.core.*;
import java.io.*;

public class CommandLineRunner {
    Game game;
    private UI ui;

    public CommandLineRunner(UI ui){
        this.ui = ui;
    }

    public void play(){
       boolean playAgain = true;
       while(playAgain){
           buildGame();
           game.play();
           playAgain = ui.shouldPlayAgain();
       }
    }

    private void buildGame() {
        GameBuilder gameBuilder = new GameBuilder(ui);
        game = gameBuilder.withPlayerX(getPlayerType(Constants.X))
                          .withPlayerO(getPlayerType(Constants.O))
                          .build();
    }

    private String getPlayerType(String mark) {
        String type = "";
        while (!type.equals(Constants.COMPUTER) && !type.equals(Constants.HUMAN))
            type = ui.getPlayerTypeFor(mark);
        return type;
    }

    public static void main(String[] args) {
        Reader in  = new InputStreamReader(System.in);
        Writer out = new BufferedWriter(new OutputStreamWriter(System.out));
        CommandLineUI ui = new CommandLineUI(in, out);
        new CommandLineRunner(ui).play();
    }
}
