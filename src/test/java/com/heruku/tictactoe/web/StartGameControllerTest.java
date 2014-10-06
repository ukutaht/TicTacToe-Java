package com.heruku.tictactoe.web;

import com.heruku.tictactoe.core.FakeIO;
import com.heruku.tictactoe.core.Game;
import com.heruku.tictactoe.core.GameFactory;
import com.heruku.tictactoe.core.GameType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StartGameControllerTest {

    private GameRepository repo;
    private StartGameController controller;

    @Before
    public void setup() {
        GameFactory factory = new GameFactory(new FakeIO());
        repo = new GameRepository();
        controller = new StartGameController(factory, repo);
    }

    @Test
    public void addsGameToRepo() {
        controller.start(GameType.HUMAN_VS_HUMAN.toString());
        assertEquals(1, repo.size());
    }

    @Test
    public void redirectsToGameId() {
        String result = controller.start(GameType.HUMAN_VS_HUMAN.toString());
        assertEquals("redirect:/play/1", result);
    }

    @Test
    public void createsRightKindOfGame() {
        controller.start(GameType.COMPUTER_VS_COMPUTER.toString());
        Game game = repo.findById(1);

        assertTrue(game.currentPlayer().isComputer());
    }
}