package com.heruku.tictactoe.web;

import com.heruku.tictactoe.core.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
class PlayController {
    private final GameRepository gameRepository;
    private final WebIO io;
    private Game game;
    private int gameId;

    public PlayController(GameRepository gameRepository, WebIO io) {
        this.gameRepository = gameRepository;
        this.io = io;
    }

    @RequestMapping(value = "play/{id}", method = GET)
    public String showGame(@PathVariable int id , ModelMap locals) {
        gameId = id;

        findGame();
        updateIO();
        buildLocals(locals);

        return "play";
    }

    @RequestMapping(value = "/make_move/{id}", method = GET)
    public String makeMove(@PathVariable int id, @RequestParam("move") Integer move) {
        gameId = id;

        findGame();
        makeMoveOnGame(move);

        return "redirect:/play/" + id;
    }

    private void makeMoveOnGame(Integer move) {
        io.setMove(move);
        game.playMove();
    }

    private void buildLocals(ModelMap locals) {
        locals.addAttribute("presenter", new GamePresenter(game, gameId));
        locals.addAttribute("message", io.getMessage());
    }

    private void findGame() {
        game = gameRepository.findById(gameId);
    }

    private void updateIO() {
        game.updateIO();
    }
}
