package com.heruku.tictactoe.web;

import com.heruku.tictactoe.core.Game;
import com.heruku.tictactoe.core.GameFactory;
import com.heruku.tictactoe.core.GameType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.heruku.tictactoe.core.BoardType.THREE_BY_THREE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
class StartGameController {
    private GameFactory gameFactory;
    private GameRepository gameRepository;
    private Paths paths;
    private Game game;
    private int gameId;

    public StartGameController(GameFactory gameFactory, GameRepository gameRepository, Paths paths) {
        this.gameFactory = gameFactory;
        this.gameRepository = gameRepository;
        this.paths = paths;
    }

    @RequestMapping(value = "/start", method = POST)
    public String start(@RequestParam("game_type") String gameType) {
        buildGame(gameType);
        storeGame();

        return paths.redirectToPlay(gameId);
    }

    @RequestMapping(value = "/", method = GET)
    public String showStartForm(ModelMap locals) {
        locals.addAttribute("gameTypes", GameType.values());

        return paths.play();
    }

    private void buildGame(String gameType) {
        game = gameFactory.build(THREE_BY_THREE, GameType.valueOf(gameType));
        game.start();
    }

    private void storeGame() {
        gameId = gameRepository.store(game);
    }
}