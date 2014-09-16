package com.heruku.tictactoe.strategies;

import com.heruku.tictactoe.UI;
import com.heruku.tictactoe.core.Board;
import com.heruku.tictactoe.core.Strategy;

public class HumanStrategy implements Strategy {
    UI ui;

    public HumanStrategy(UI ui){
        this.ui = ui;
    }

    public int getMove(Board board){
        int move = ui.getMove();;
        while(!board.isValidMove(move)){
            ui.notifyOfInvalidMove();
            ui.printBoard(board.toString());
            move = ui.getMove();;
        }
        return move;
    }
}
