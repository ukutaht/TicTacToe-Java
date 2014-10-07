package com.heruku.tictactoe.web;

import com.heruku.tictactoe.core.FakeIO;
import com.heruku.tictactoe.core.Game;
import com.heruku.tictactoe.core.GameFactory;
import com.heruku.tictactoe.core.GameType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class StartGameControllerTest {
    private GameRepository repo;
    private StartGameController controller;
    private ModelMap locals;

    @Before
    public void setup() {
        GameFactory factory = new GameFactory(new FakeIO());
        repo = new GameRepository();
        controller = new StartGameController(factory, repo);
        locals = new ModelMap();
    }

    @Test
    public void addsGameToRepo() {
        assertEquals(0, repo.size());
        controller.start(GameType.HUMAN_VS_HUMAN.toString());

        assertEquals(1, repo.size());
    }

    @Test
    public void redirectsToGameId() {
        String path = controller.start(GameType.HUMAN_VS_HUMAN.toString());
        assertEquals("redirect:/play/1", path);
    }

    @Test
    public void createsRightKindOfGame() {
        controller.start(GameType.COMPUTER_VS_COMPUTER.toString());
        Game game = repo.findById(1);

        assertTrue(game.currentPlayer().isComputer());
    }

    @Test
    public void rendersPlayAtRoot() {
        String template = controller.showStartForm(locals);

        assertEquals("play", template);
    }

    @Test
    public void setsUpDummyGamePresenterAtRoot() {
        controller.showStartForm(locals);
        GamePresenter presenter = (GamePresenter) locals.get("presenter");

        assertThat(presenter.boardHtml(), not(containsString("<a href")));
    }
}