package com.heruku.tictactoe.core;

import com.heruku.tictactoe.players.ComputerPlayer;
import com.heruku.tictactoe.players.HumanPlayer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameFactoryTest {

    private GameFactory factory;

    @Before
    public void setup() {
        factory = new GameFactory(new FakeIO());
    }

    @Test
    public void humanVsHuman() {
        Game game = factory.build(BoardType.THREE_BY_THREE, GameType.HUMAN_VS_HUMAN);

        assertTrue(game.players.get(0) instanceof HumanPlayer);
        assertTrue(game.players.get(1) instanceof HumanPlayer);
    }

    @Test
    public void humanVsComputer() {
        Game game = factory.build(BoardType.THREE_BY_THREE, GameType.HUMAN_VS_COMPUTER);

        assertTrue(game.players.get(0) instanceof HumanPlayer);
        assertTrue(game.players.get(1) instanceof ComputerPlayer);
    }

    @Test
    public void computerVsHuman() {
        Game game = factory.build(BoardType.THREE_BY_THREE, GameType.COMPUTER_VS_HUMAN);

        assertTrue(game.players.get(0) instanceof ComputerPlayer);
        assertTrue(game.players.get(1) instanceof HumanPlayer);
    }

    @Test
    public void computerVsComputer() {
        Game game = factory.build(BoardType.THREE_BY_THREE, GameType.COMPUTER_VS_COMPUTER);

        assertTrue(game.players.get(0) instanceof ComputerPlayer);
        assertTrue(game.players.get(1) instanceof ComputerPlayer);
    }

    @Test
    public void threeByThree() {
        Game game = factory.build(BoardType.THREE_BY_THREE, GameType.HUMAN_VS_HUMAN);

        assertEquals(9, game.boardString().length());
    }

    @Test
    public void fourByFour() {
        Game game = factory.build(BoardType.FOUR_BY_FOUR, GameType.HUMAN_VS_HUMAN);

        assertEquals(16, game.boardString().length());
    }
}