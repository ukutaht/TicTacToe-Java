package com.heruku.tictactoe.core;

import java.util.Arrays;

import static com.heruku.tictactoe.core.GameType.*;
import static com.heruku.tictactoe.core.Constants.O;
import static com.heruku.tictactoe.core.Constants.X;

public class GameFactory {
    private final UI ui;

    public GameFactory(UI ui) {
        this.ui = ui;
    }

    public Game forSelection(GameType selection) {
        if (selection == HUMAN_VS_HUMAN)
            return new Game(Arrays.asList(humanPlayer(X), humanPlayer(O)), ui);
        if (selection == HUMAN_VS_COMPUTER)
            return new Game(Arrays.asList(humanPlayer(X), computerPlayer(O)), ui);
        if (selection == COMPUTER_VS_HUMAN)
            return new Game(Arrays.asList(computerPlayer(X), humanPlayer(O)), ui);
        if (selection == COMPUTER_VS_COMPUTER)
            return new Game(Arrays.asList(computerPlayer(X), computerPlayer(O)), ui);
        throw new RuntimeException("Unexpected game type");
    }

    private Player computerPlayer(String mark) {
        return new ComputerPlayer(mark);
    }

    private Player humanPlayer(String mark) {
        return new HumanPlayer(mark, ui);
    }
}
