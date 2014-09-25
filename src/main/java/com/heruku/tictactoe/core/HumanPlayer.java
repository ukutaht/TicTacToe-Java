package com.heruku.tictactoe.core;

public class HumanPlayer implements Player {
    private UI ui;
    private String mark;

    public HumanPlayer(String mark, UI ui) {
        this.mark = mark;
        this.ui = ui;
    }

    @Override
    public String getMark() {
        return mark;
    }

    @Override
    public Board makeMove(Board board) {
        int move = ui.getMove();
        while(!board.isValidMove(move)) {
            ui.notifyOfInvalidMove();
            ui.printBoard(board.toString());
            move = ui.getMove();
        }
        return board.markSquare(move, getMark());
    }
}
