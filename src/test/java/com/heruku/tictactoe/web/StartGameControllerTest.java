package com.heruku.tictactoe.web;

import com.heruku.tictactoe.core.FakeIO;
import com.heruku.tictactoe.core.Game;
import com.heruku.tictactoe.core.GameFactory;
import com.heruku.tictactoe.core.GameType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StartGameControllerTest {
    private GameRepository repo;
    private StartGameController controller;
    private ModelMap locals;
    private FakeIO io;

    @Before
    public void setup() {
        io = new FakeIO();
        GameFactory factory = new GameFactory(io);
        repo = new GameRepository();
        controller = new StartGameController(factory, repo, new Paths());
        locals = new ModelMap();
    }

    @Test
    public void addsGameToRepo() {
        assertEquals(0, repo.size());
        controller.start(GameType.HUMAN_VS_HUMAN.toString());

        assertEquals(1, repo.size());
    }

    @Test
    public void initializesGame() {
        controller.start(GameType.HUMAN_VS_HUMAN.toString());

        assertTrue(io.calledMethods().contains("notifyOfTurn"));
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
        String template = controller.showStartForm();

        assertEquals("play", template);
    }
}