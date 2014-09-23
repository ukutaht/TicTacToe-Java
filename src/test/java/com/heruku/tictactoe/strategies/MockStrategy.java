package com.heruku.tictactoe.strategies;

import com.heruku.tictactoe.core.Board;
import com.heruku.tictactoe.core.Player;
import com.heruku.tictactoe.core.Strategy;

public class MockStrategy implements Strategy {
    public int getMove(Board board, Player player){
        return 4;
    }
}
