package com.heruku.tictactoe.web;

import com.heruku.tictactoe.core.Game;
import com.heruku.tictactoe.core.GameFactory;
import com.heruku.tictactoe.core.GameType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.heruku.tictactoe.core.BoardType.THREE_BY_THREE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/start")
class StartGameController {
    private GameFactory factory;
    private GameRepository repository;

    public StartGameController(GameFactory factory, GameRepository repository) {
        this.factory = factory;
        this.repository = repository;
    }

    @RequestMapping(method = POST)
    public String start(@RequestParam("game_type") String gameType) {
        Game game = factory.build(THREE_BY_THREE, GameType.valueOf(gameType));
        int id = repository.store(game);
        return "redirect:/play/" + id;
    }
}