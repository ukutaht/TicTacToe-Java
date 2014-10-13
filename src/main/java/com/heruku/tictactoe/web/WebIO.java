package com.heruku.tictactoe.web;

import com.heruku.tictactoe.core.*;

public class WebIO implements IO {
    private Move move;
    private String notification;
    private Board board;

    public void setMove(Move move) {
        this.move = move;
    }

    public String getNotification() {
        return notification;
    }

    public String boardMarkup(int gameId) {
        return new GameHtmlRenderer(board , gameId).render();
    }

    @Override
    public Move getMove() {
        return move;
    }

    @Override
    public void showBoard(Board board) {
        this.board = board;
    }

    @Override
    public void notifyOfInvalidMove() {}

    @Override
    public void notifyOfTurn(Player player) {
        notification = player.getMark() + " turn.";
    }

    @Override
    public void notifyWinner(Player winner) {
        notification = winner.getMark() + " wins!";
    }

    @Override
    public void notifyOfDraw() {
        notification = "It's a draw!";
    }
}
