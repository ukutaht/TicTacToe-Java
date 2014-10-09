package com.heruku.tictactoe.web;

public class Paths {

    public String play() {
        return "play";
    }

    public String redirectToPlay(int gameId) {
        return "redirect:/play/" + gameId;
    }

    public String gameNotFound() {
        return "gameNotFound";
    }

    public String makeMove(int gameId, int move) {
        return String.format("/make_move/%d?move=%d", gameId, move);
    }
}
