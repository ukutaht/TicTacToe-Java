package com.heruku.tictactoe.web;

import com.heruku.tictactoe.core.*;
import com.heruku.tictactoe.players.HumanPlayer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import java.util.Arrays;
import java.util.List;

import static com.heruku.tictactoe.core.PlayerMark.*;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;

public class PlayControllerTest {

    private PlayController controller;
    private GameRepository repository;
    private Game emptyGame;
    private ModelMap locals;
    private Game computerVsHumanGame;
    private Game XWinsGame;

    @Before
    public void setup() {
        WebIO io = new WebIO();
        repository = new GameRepository();
        controller = new PlayController(repository, io, new Paths());
        locals = new ModelMap();

        emptyGame = new GameFactory(io).getDefault();
        emptyGame.start();

        computerVsHumanGame = new GameFactory(new FakeIO()).build(BoardType.THREE_BY_THREE, GameType.COMPUTER_VS_HUMAN);
        List<Player> players = Arrays.<Player>asList(new HumanPlayer(X, new FakeIO()));
        XWinsGame = new Game(new ThreeByThreeBoard("XXX   OOO"), players, new FakeIO());
    }

    @Test
    public void addsGameIdToLocals() {
        int id = repository.store(emptyGame);
        controller.showGame(id, locals);

        assertEquals(id, locals.get("gameId"));
    }

    @Test
        public void addsNotificationToLocals() {
        int id = repository.store(emptyGame);
        controller.showGame(id, locals);

        assertThat((String) locals.get("notification"), containsString("turn"));
    }

    @Test
    public void addsBoardMarkupToLocals() {
        int id = repository.store(emptyGame);
        controller.showGame(id, locals);

        assertThat((String) locals.get("boardMarkup"), containsString("div"));
    }

    @Test
    public void autoMoveIsTrueWhenComputerMove() {
        int id = repository.store(computerVsHumanGame);
        controller.showGame(id, locals);

        assertTrue((Boolean) locals.get("autoMove"));
    }

    @Test
    public void doesNotAutoMoveWhenHumanTurn() {
        int id = repository.store(emptyGame);
        controller.showGame(id, locals);

        assertFalse((Boolean) locals.get("autoMove"));
    }

    @Test
    public void doesNotAutoMoveWhenGameIsOver() {
        int id = repository.store(XWinsGame);
        controller.showGame(id, locals);

        assertFalse((Boolean) locals.get("autoMove"));
    }

    @Test
    public void makesMove() {
        int id = repository.store(emptyGame);
        Move move = new Move(0);
        controller.makeMove(id, move.getValue());

        assertFalse(emptyGame.getBoard().isValidMove(move));
    }

    @Test
    public void redirectsToSameGameAfterMakingMove() {
        int id = repository.store(emptyGame);
        String redirect = controller.makeMove(id, 0);

        assertEquals("redirect:/play/" + id, redirect);
    }


    @Test(expected = GameNotFoundException.class)
    public void throwsWhenGameIsNotFound() {
        controller.showGame(999, locals);
    }

    @Test
    public void handlesNotFound() {
        String template = controller.gameNotFound();

        assertEquals("gameNotFound", template);
    }
}