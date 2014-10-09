package com.heruku.tictactoe.web;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PathsTest {

    @Test
    public void playPath() {
        Paths paths = new Paths();

        assertEquals("play", paths.play());
    }

    @Test
    public void redirectPlayWithId() {
        Paths paths = new Paths();

        assertEquals("redirect:/play/1", paths.redirectToPlay(1));
        assertEquals("redirect:/play/3", paths.redirectToPlay(3));
    }

    @Test
    public void gameNotFound() {
        Paths paths = new Paths();

        assertEquals("gameNotFound", paths.gameNotFound());
    }

    @Test
    public void makeMove() {
        Paths paths = new Paths();

        assertEquals("/make_move/1?move=2", paths.makeMove(1, 2));
        assertEquals("/make_move/2?move=3", paths.makeMove(2, 3));
    }
}