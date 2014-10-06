package com.heruku.tictactoe.web;

import com.heruku.tictactoe.core.*;

class WebRunner {
    private Game game;
    private WebIO io;
    private GameFactory factory;

    public WebRunner(WebIO io) {
        this(new GameFactory(io).getDefault(), io);
    }

    public WebRunner(Game game, WebIO io) {
        this.game = game;
        this.io = io;
        factory = new GameFactory(io);
    }

    public String getMessage() {
        return io.getMessage();
    }

    public Board getBoard() {
        return game.getBoard();
    }

    public boolean shouldMakeMove() {
        return game.currentPlayer().isComputer() && !game.isOver();
    }

    public void newGameFromString(String gameType) {
        newGame(GameType.valueOf(gameType));
    }

    public void makeMove(Integer move) {
        io.setMove(move);
        game.playMove();
        notifyUser();
    }

    private void notifyUser() {
        if (game.isOver())
            io.notifyWinner(game);
        else
            io.notifyTurn(game);
    }

    private void newGame(GameType type) {
        game = factory.forSelection(BoardType.THREE_BY_THREE, type);
        notifyUser();
    }
}