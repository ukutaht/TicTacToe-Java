package com.heruku.tictactoe;

import com.heruku.tictactoe.core.Game;
import com.heruku.tictactoe.strategies.HumanStrategy;
import com.heruku.tictactoe.core.Player;
import com.heruku.tictactoe.strategies.RandomStrategy;

import java.io.*;

public class CommandLineRunner {
    Game game;
    private UI ui;

    public CommandLineRunner(Game game, UI ui){
        this.game = game;
        this.ui = ui;
    }

    public void play(){
       boolean playAgain = true;
       while(playAgain){
           playGame();
           playAgain = ui.shouldPlayAgain();
       }
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
        Player.O(new RandomStrategy());
        Game game = new Game(Player.X(new HumanStrategy(ui)));
        new CommandLineRunner(game, ui).play();
    }
}
