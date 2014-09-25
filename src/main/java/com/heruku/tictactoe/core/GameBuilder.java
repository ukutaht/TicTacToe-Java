package com.heruku.tictactoe.core;

import java.util.ArrayList;
import java.util.List;

public class GameBuilder {
    private UI ui;
    private List<Player> players;

    public GameBuilder(UI ui) {
        this.ui = ui;
        this.players = new ArrayList<Player>();
    }

    public GameBuilder withPlayerX(String type) {
        players.add(new HumanPlayer("X", ui));
        return this;
    }

    public GameBuilder withPlayerO(String type) {
        players.add(new HumanPlayer("O", ui));
        return this;
    }

    public Game build() {
        return new Game(ui, players);
    }
}
