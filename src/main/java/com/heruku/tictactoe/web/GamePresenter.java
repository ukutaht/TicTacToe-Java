package com.heruku.tictactoe.web;

import com.heruku.tictactoe.core.Game;

public class GamePresenter {

    private final Game game;
    private final int gameId;
    private GameHtmlRenderer renderer;

    public GamePresenter(Game game, int gameId) {
        this.game = game;
        this.gameId = gameId;
        this.renderer = new GameHtmlRenderer(game, gameId);
    }

    public String boardHtml() {
        return renderer.render();
    }

    public boolean autoMove() {
        return game.currentPlayer().isComputer() && !game.isOver();
    }

    public int gameId() {
        return gameId;
    }

    public void noLinks() {
        renderer.noLinks();
    }
}
