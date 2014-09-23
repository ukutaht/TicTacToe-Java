package com.heruku.tictactoe;

import com.heruku.tictactoe.core.Game;
import com.heruku.tictactoe.core.GameBuilder;
import com.heruku.tictactoe.core.UI;
import com.heruku.tictactoe.strategies.HumanStrategy;
import com.heruku.tictactoe.core.Player;
import com.heruku.tictactoe.strategies.UnbeatableAIStrategy;

import java.io.*;

public class CommandLineRunner {
    Game game;
    private UI ui;

    public CommandLineRunner(Game game, UI ui){
        this.game = game;
        this.ui = ui;
    }

    public CommandLineRunner(UI ui){
        this.ui = ui;
    }

    public void play(){
       boolean playAgain = true;
       while(playAgain){
           configureGame();
           playGame();
           playAgain = ui.shouldPlayAgain();
       }
    }

    private void configureGame() {
        GameBuilder gameBuilder = new GameBuilder(ui);
        game = gameBuilder.withPlayerX(getPlayerType("X"))
                          .withPlayerO(getPlayerType("O"))
                          .build();
    }

    private String getPlayerType(String mark) {
        String type = "";
        while (!type.equals("computer") && !type.equals("human"))
            type = ui.getPlayerTypeFor(mark);
        return type;
    }

    public void playGame(){
        while(!game.isOver()){
            printBoard();
            Integer move = game.getCurrentPlayerMove();
            game = game.playMove(move);
        }
        printBoard();
        notifyWinner();
    }

    private void printBoard(){
        ui.printBoard(game.boardString());
    }

    private void notifyWinner(){
        if (game.hasWinner())
            ui.notifyWinner(game.winner());
        else
            ui.notifyDraw();
    }

    public static void main(String[] args) {
        Reader in  = new InputStreamReader(System.in);
        Writer out = new BufferedWriter(new OutputStreamWriter(System.out));
        CommandLineUI ui = new CommandLineUI(in, out);
        new CommandLineRunner(ui).play();
    }
}
