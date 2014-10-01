package com.heruku.tictactoe.core;

public class HumanPlayer implements Player {
    private final String mark;
    private final UI ui;

    public HumanPlayer(String mark, UI ui) {
        this.mark = mark;
        this.ui = ui;
    }

    @Override
    public String getMark() {
        return mark;
    }

    @Override
    public int getMove(Board board) {
        return ui.getMove();
    }
}