package com.heruku.tictactoe.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String start(@RequestParam("game_type") String gameType) {
        runner.newGameFromString(gameType);
        return "redirect:/play";
    }
}