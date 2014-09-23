package com.heruku.tictactoe.core;

public class Game {
    private final Board board;
    final Player currentPlayer;

    public Game(Player currentPlayer){
        board = new Board("         ");
        this.currentPlayer = currentPlayer;
    }

    Game(Board board, Player currentPlayer){
        this.board = board;
        this.currentPlayer = currentPlayer;
    }

    public Game playMove(int index){
        checkMoveValidity(index);
        Board newBoard = board.markSquare(index, currentPlayer.getMark());
        return new Game(newBoard, currentPlayer.next());
    }

    public boolean isValidMove(int index){
        return board.isValidMove(index);
    }

    public int getCurrentPlayerMove(){
        return currentPlayer.getMove(board);
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

    private boolean hasDraw() {
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
