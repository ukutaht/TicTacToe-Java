package com.heruku.tictactoe.web;

import com.heruku.tictactoe.core.Game;
import com.heruku.tictactoe.core.Move;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
class PlayController {
    private final GameRepository gameRepository;
    private final WebIO io;
    private final Paths paths;
    private Game game;
    private int gameId;

    public PlayController(GameRepository gameRepository, WebIO io, Paths paths) {
        this.gameRepository = gameRepository;
        this.io = io;
        this.paths = paths;
    }

    @RequestMapping(value = "play/{id}", method = GET)
    public String showGame(@PathVariable int id , ModelMap locals) {
        findGame(id);
        buildLocals(locals);

        return paths.play();
    }

    @RequestMapping(value = "/make_move/{id}", method = GET)
    public String makeMove(@PathVariable int id, @RequestParam("move") Integer move) {
        findGame(id);
        makeMoveOnGame(move);

        return paths.redirectToPlay(id);
    }

    @ExceptionHandler(GameNotFoundException.class)
    public String gameNotFound() {
        return paths.gameNotFound();
    }

    private void makeMoveOnGame(Integer move) {
        io.setMove(new Move(move));
        game.playMove();
    }

    private void buildLocals(ModelMap locals) {
        locals.addAttribute("gameId", gameId);
        locals.addAttribute("notification", io.getNotification());
        locals.addAttribute("boardMarkup", io.boardMarkup(gameId));
        locals.addAttribute("autoMove", shouldAutoMove());
    }

    private boolean shouldAutoMove() {
        return game.currentPlayer().isComputer() && !game.isOver();
    }

    private void findGame(int id) {
        gameId = id;
        game = gameRepository.findById(gameId);
    }
}
