package com.heruku.tictactoe.web;

import com.heruku.tictactoe.core.*;
import com.heruku.tictactoe.players.HumanPlayer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import java.util.Arrays;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class PlayControllerTest {

    private PlayController controller;
    private GameRepository repository;
    private Game emptyGame;
    private Game XWinsGame;
    private ModelMap locals;

    @Before
    public void setup() {
        WebIO io = new WebIO();
        repository = new GameRepository();
        controller = new PlayController(repository, io);
        locals = new ModelMap();

        emptyGame = new GameFactory(io).getDefault();
        XWinsGame = new Game(new Board("XXX  OOO "), Arrays.<Player>asList(new HumanPlayer(Constants.X, io)), io);
    }

    @Test
    public void buildsPresenter() {
        int id = repository.store(emptyGame);
        controller.showGame(id, locals);
        GamePresenter presenter = (GamePresenter) locals.get("presenter");

        assertEquals(id, presenter.gameId());
    }

    @Test
    public void notifiesUser() {
        int id = repository.store(XWinsGame);
        controller.showGame(id, locals);

        assertThat((String) locals.get("message"), containsString("X wins"));
    }

    @Test
    public void makesMove() {
        int id = repository.store(emptyGame);
        controller.makeMove(id, 0);

        assertFalse(emptyGame.getBoard().isValidMove(0));
    }

    @Test
    public void redirectsToSameGameAfterMakingMove() {
        int id = repository.store(emptyGame);
        String redirect = controller.makeMove(id, 0);

        assertEquals("redirect:/play/" + id, redirect);
    }
}