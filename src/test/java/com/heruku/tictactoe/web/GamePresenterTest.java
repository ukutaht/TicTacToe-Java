package com.heruku.tictactoe.web;

import com.heruku.tictactoe.core.*;
import com.heruku.tictactoe.players.HumanPlayer;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GamePresenterTest {

    private Game humanVsHumanGame;
    private Game computerVsHumanGame;
    private Game XWinsGame;

    @Before
    public void setup() {
        humanVsHumanGame = new GameFactory(new FakeIO()).getDefault();
        computerVsHumanGame = new GameFactory(new FakeIO()).build(BoardType.THREE_BY_THREE, GameType.COMPUTER_VS_HUMAN);
        List<Player> players = Arrays.<Player>asList(new HumanPlayer(Constants.X, new FakeIO()));
        XWinsGame = new Game(new Board("XXX   OOO"), players, new FakeIO());
    }

    @Test
    public void exposesBoardHtml() {
        GamePresenter presenter = new GamePresenter(humanVsHumanGame, 0);

        assertNotNull(presenter.boardHtml());
    }

    @Test
    public void autoMoveIsTrueWhenComputerMove() {
        GamePresenter presenter = new GamePresenter(computerVsHumanGame, 0);

        assertTrue(presenter.autoMove());
    }

    @Test
    public void doesNotAutoMoveWhenHumanTurn() {
        GamePresenter presenter = new GamePresenter(humanVsHumanGame, 0);

        assertFalse(presenter.autoMove());
    }

    @Test
    public void doesNotAutoMoveWhenGameIsOver() {
        GamePresenter presenter = new GamePresenter(XWinsGame, 0);

        assertFalse(presenter.autoMove());
    }

    @Test
    public void exposesId() {
        GamePresenter presenter = new GamePresenter(null, 12);

        assertEquals(12, presenter.gameId());
    }
}