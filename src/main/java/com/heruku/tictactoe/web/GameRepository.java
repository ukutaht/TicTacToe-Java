package com.heruku.tictactoe.web;

import com.heruku.tictactoe.core.Game;

import java.util.ArrayList;
import java.util.List;

public class GameRepository {
    private List<Game> games = new ArrayList<Game>();

    public int store(Game game) {
        games.add(game);
        return games.size();
    }

    public Game findById(int id) {
        try {
            return games.get(id - 1);
        } catch(IndexOutOfBoundsException e) {
            throw new GameNotFoundException();
        }
    }

    public int size() {
        return games.size();
    }
}
