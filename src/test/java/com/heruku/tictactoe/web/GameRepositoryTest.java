package com.heruku.tictactoe.web;

import com.heruku.tictactoe.core.FakeIO;
import com.heruku.tictactoe.core.Game;
import com.heruku.tictactoe.core.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameRepositoryTest {

    private GameRepository repo;

    @Before
    public void setup() {
        repo = new GameRepository();
    }

    @Test
    public void storesAndReturnsID() {
        int id = repo.store(dummyGame());

        assertTrue(id > 0);
    }

    @Test
    public void canTellItsSize() {
        assertEquals(0, repo.size());
        repo.store(dummyGame());
        assertEquals(1, repo.size());
    }

    @Test
    public void storesAndFetchesGame() {
        Game game = dummyGame();
        int id = repo.store(game);

        assertEquals(game, repo.findById(id));
    }

    @Test
    public void canStoreMultipleGames() {
        Game first = dummyGame();
        Game second = dummyGame();

        int firstId = repo.store(first);
        int secondId = repo.store(second);

        assertEquals(first, repo.findById(firstId));
        assertEquals(second, repo.findById(secondId));
    }

    private Game dummyGame() {
        return new Game(Collections.<Player>emptyList(), new FakeIO());
    }
}