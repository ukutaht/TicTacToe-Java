package com.heruku.tictactoe.web;

import com.heruku.tictactoe.core.Game;
import com.heruku.tictactoe.core.HumanPlayer;
import com.heruku.tictactoe.core.Player;
import com.heruku.tictactoe.core.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/start")
class StartGameController {
    private WebRunner runner;

    @Autowired
    public void setRunner(WebRunner runner) {
        this.runner = runner;
    }

    @RequestMapping(method = POST)
    public String start(@RequestParam("game_type") String gameType, ModelMap locals) {
        List<Player> players = new ArrayList<Player>();
        players.add(new HumanPlayer("X", runner.getUi()));
        players.add(new HumanPlayer("O", runner.getUi()));
        UI ui = new WebUI();
        runner.setGame(new Game(players, ui));
        return "redirect:/play";
    }
}
