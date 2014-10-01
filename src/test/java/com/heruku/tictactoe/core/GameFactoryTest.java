package com.heruku.tictactoe.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GameFactoryTest {

    private GameFactory factory;

    @Before
    public void setup() {
        factory = new GameFactory(new FakeUI());
    }

    @Test
    public void humanVsHuman() {
        Game game = factory.forSelection(GameType.HUMAN_VS_HUMAN);

        assertTrue(game.players.get(0) instanceof HumanPlayer);
        assertTrue(game.players.get(1) instanceof HumanPlayer);
    }

    @Test
    public void humanVsComputer() {
        Game game = factory.forSelection(GameType.HUMAN_VS_COMPUTER);

        assertTrue(game.players.get(0) instanceof HumanPlayer);
        assertTrue(game.players.get(1) instanceof ComputerPlayer);
    }

    @Test
    public void computerVsHuman() {
        Game game = factory.forSelection(GameType.COMPUTER_VS_HUMAN);

        assertTrue(game.players.get(0) instanceof ComputerPlayer);
        assertTrue(game.players.get(1) instanceof HumanPlayer);
    }

    @Test
    public void computerVsComputer() {
        Game game = factory.forSelection(GameType.COMPUTER_VS_COMPUTER);

        assertTrue(game.players.get(0) instanceof ComputerPlayer);
        assertTrue(game.players.get(1) instanceof ComputerPlayer);
    }
}