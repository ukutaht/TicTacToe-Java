package com.heruku.tictactoe.players;

import com.heruku.tictactoe.core.*;

public class HumanPlayer implements Player {
    private final PlayerMark mark;
    private final IO io;

    public HumanPlayer(PlayerMark mark, IO io) {
        this.mark = mark;
        this.io = io;
    }

    @Override
    public PlayerMark getMark() {
        return mark;
    }

    @Override
    public Move getTheMove(Board board) {
        return io.getMove();
    }

    @Override
    public boolean isComputer() {
        return false;
    }
}