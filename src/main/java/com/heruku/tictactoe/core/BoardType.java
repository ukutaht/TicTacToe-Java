package com.heruku.tictactoe.core;

public enum BoardType {
    THREE_BY_THREE("3x3"),
    FOUR_BY_FOUR("4x4");

    private final String name;

    BoardType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
