package com.heruku.tictactoe.core;

import java.util.Collections;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private UI ui;

    public Game(UI ui, List<Player> players){
        board = new Board("         ");
        this.players = players;
        this.ui = ui;
    }

    Game(UI ui, Board board, List<Player> players){
        this.board = board;
        this.players = players;
        this.ui = ui;
    }

    public void play() {
        while(!isOver()){
            printBoard();
            playMove();
        }
        printBoard();
        notifyWinner();
    }

    public void playMove(){
        board  = currentPlayer().makeMove(board);
        Collections.rotate(players, 1);
    }


    private void printBoard(){
        ui.printBoard(boardString());
    }

    private void notifyWinner(){
        if (hasWinner())
            ui.notifyWinner(winner());
        else
            ui.notifyDraw();
    }

    public boolean isValidMove(int index){
        return board.isValidMove(index);
    }

    public Player currentPlayer() {
        return players.get(0);
    }

    public boolean isOver(){
        return hasWinner() || hasDraw();
    }

    public String winner() {
        return board.winner();
    }

    public boolean hasWinner() {
        return board.hasWinner();
    }

    public boolean hasDraw() {
        return board.isFull() && !hasWinner();
    }

    public String boardString(){
        return board.toString();
    }

    private void checkMoveValidity(int index){
        if(!isValidMove(index))
            throw new RuntimeException("Invalid move: " + Integer.toString(index));
    }
}
