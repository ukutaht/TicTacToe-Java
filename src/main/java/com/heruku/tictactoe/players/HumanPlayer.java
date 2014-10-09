package com.heruku.tictactoe.players;

import com.heruku.tictactoe.core.Board;
import com.heruku.tictactoe.core.IO;
import com.heruku.tictactoe.core.Player;
import com.heruku.tictactoe.core.PlayerMark;

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
    public int getMove(Board board) {
        return io.getMove();
    }


    @Override
    public boolean isComputer() {
        return false;
    }
}