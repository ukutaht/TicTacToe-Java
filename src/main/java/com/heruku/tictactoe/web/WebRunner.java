package com.heruku.tictactoe.web;

import com.heruku.tictactoe.core.Game;
import com.heruku.tictactoe.core.Player;

import java.util.ArrayList;

public class WebRunner {
    private Game game;
    private WebUI ui;

    public WebRunner() {
        game = new Game(new ArrayList<Player>(), ui);
    }

    public void makeMove(Integer move) {
        ui.setMove(move);
        game.playMove();
    }

    public String boardString() {
        return game.boardString();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setUi(WebUI ui) {
        this.ui = ui;
    }

    public WebUI getUi() {
        return ui;
    }
}
