package com.heruku.tictactoe.web;

import com.heruku.tictactoe.core.Board;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class GameHtmlRendererTest {
    private GameHtmlRenderer renderer;
    private int GAME_ID = 9;
    private String CELL_CLASS = "grid-cell";
    private Board board;

    @Before
    public void setup() {
        board = Board.THREE_BY_THREE();
        renderer = new GameHtmlRenderer(board, GAME_ID);
    }

    @Test
    public void wrapsCellsWithDiv() {
        int cells = StringUtils.countOccurrencesOf(renderer.render(), String.format("<div class=\"%s\"", CELL_CLASS));
        assertEquals(9, cells);
    }

    @Test
    public void addsLinks() {
        int links = StringUtils.countOccurrencesOf(renderer.render(), "<a href");
        assertEquals(9, links);
    }

    @Test
    public void doesNotAddLinkForPlayedSquares() {
        board = board.markSquare(0, "X");
        renderer = new GameHtmlRenderer(board, 0);

        int links = StringUtils.countOccurrencesOf(renderer.render(), "<a href");
        assertEquals(8, links);
    }

    @Test
    public void doesNotAddLinksWhenGameIsOver() {
        board = Board.THREE_BY_THREE("XXX      ");
        renderer = new GameHtmlRenderer(board, 0);

        int links = StringUtils.countOccurrencesOf(renderer.render(), "<a href");
        assertEquals(0, links);
    }

    @Test
    public void buildsLinksWithGameId() {
        assertThat(renderer.render(), containsString("a href=\"/make_move/" + GAME_ID));
    }
}