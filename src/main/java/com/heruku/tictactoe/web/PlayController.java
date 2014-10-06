package com.heruku.tictactoe.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/play")
class PlayController {

    private WebRunner runner;

    @Autowired
    public void setRunner(WebRunner runner) {
        this.runner = runner;
    }

    @RequestMapping(method = GET)
    public String showGame(ModelMap locals) {
        locals.addAttribute("board", runner.getBoard());
        locals.addAttribute("message", runner.getMessage());
        locals.addAttribute("shouldMakeMove", runner.shouldMakeMove());
        return "play";
    }

    @RequestMapping(value = "/make_move", method = GET)
    public String makeMove(@RequestParam("move") Integer move) {
        runner.makeMove(move);
        return "redirect:/play";
    }
}
