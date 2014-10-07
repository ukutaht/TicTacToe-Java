package com.heruku.tictactoe.web;

import com.heruku.tictactoe.core.FakeIO;
import com.heruku.tictactoe.core.Game;
import com.heruku.tictactoe.core.GameFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class GameHtmlRendererTest {
    private Game game;
    private GameHtmlRenderer renderer;
    private int GAME_ID = 9;
    private String CELL_CLASS = "grid-cell";

    @Before
    public void setup() {
        game = new GameFactory(new FakeIO(Arrays.asList(0))).getDefault();
        renderer = new GameHtmlRenderer(game, GAME_ID);
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
        game.playMove();
        renderer = new GameHtmlRenderer(game, 0);

        int links = StringUtils.countOccurrencesOf(renderer.render(), "<a href");
        assertEquals(8, links);
    }

    @Test
    public void buildsLinksWithGameId() {
        assertThat(renderer.render(), containsString("a href=\"/make_move/" + GAME_ID));
    }
}