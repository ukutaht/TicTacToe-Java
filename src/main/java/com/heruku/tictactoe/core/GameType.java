package com.heruku.tictactoe.core;

public enum GameType {
    HUMAN_VS_HUMAN("Human vs Human"),
    HUMAN_VS_COMPUTER("Human vs Computer"),
    COMPUTER_VS_HUMAN("Computer vs Human"),
    COMPUTER_VS_COMPUTER("Computer vs Computer");

    private final String name;

    GameType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
