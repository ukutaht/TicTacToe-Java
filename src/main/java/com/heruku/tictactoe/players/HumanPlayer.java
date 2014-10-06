package com.heruku.tictactoe.players;

import com.heruku.tictactoe.core.Board;
import com.heruku.tictactoe.core.IO;
import com.heruku.tictactoe.core.Player;

public class HumanPlayer implements Player {
    private final String mark;
    private final IO io;

    public HumanPlayer(String mark, IO io) {
        this.mark = mark;
        this.io = io;
    }

    @Override
    public String getMark() {
        return mark;
    }

    @Override
    public int getMove(Board board) {
        return io.getMove();
    }


    @Override
    public boolean isComputer() {
        return false;
    }
}