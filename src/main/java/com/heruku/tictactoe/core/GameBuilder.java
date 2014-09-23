package com.heruku.tictactoe.core;

import com.heruku.tictactoe.strategies.HumanStrategy;
import com.heruku.tictactoe.strategies.UnbeatableAIStrategy;

public class GameBuilder {
    private UI ui;

    public GameBuilder(UI ui) {
        this.ui = ui;
    }

    public GameBuilder withPlayerX(String type) {
        Player.X(strategyForPlayerType(type));
        return this;
    }

    public GameBuilder withPlayerO(String type) {
        Player.O(strategyForPlayerType(type));
        return this;
    }

    private Strategy strategyForPlayerType(String type) {
        if (type.equals("computer"))
            return new UnbeatableAIStrategy();
        if (type.equals("human"))
            return new HumanStrategy(ui);
        return null;
    }

    public Game build() {
        return new Game(Player.X());
    }
}
