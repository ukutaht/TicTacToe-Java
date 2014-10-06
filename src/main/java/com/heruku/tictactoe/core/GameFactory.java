package com.heruku.tictactoe.core;

import com.heruku.tictactoe.players.ComputerPlayer;
import com.heruku.tictactoe.players.HumanPlayer;

import java.util.List;

import static com.heruku.tictactoe.core.Constants.O;
import static com.heruku.tictactoe.core.Constants.X;
import static java.util.Arrays.asList;

public class GameFactory {
    private final IO io;

    public GameFactory(IO io) {
        this.io = io;
    }

    public Game getDefault() {
        return build(BoardType.THREE_BY_THREE, GameType.HUMAN_VS_HUMAN);
    }

    public Game build(BoardType boardType, GameType gameType) {
        List<Player> players = playersFor(gameType);
        Board board = boardFor(boardType);

        return new Game(board, players, io);
    }

    private List<Player> playersFor(GameType gameType) {
        switch (gameType) {
            case HUMAN_VS_HUMAN:       return asList(humanPlayer(X), humanPlayer(O));
            case HUMAN_VS_COMPUTER:    return asList(humanPlayer(X), computerPlayer(O));
            case COMPUTER_VS_HUMAN:    return asList(computerPlayer(X), humanPlayer(O));
            case COMPUTER_VS_COMPUTER: return asList(computerPlayer(X), computerPlayer(O));
            default:                   throw new RuntimeException("Unexpected game type");
        }
    }

    private Board boardFor(BoardType boardType) {
        switch (boardType) {
            case THREE_BY_THREE: return Board.THREE_BY_THREE();
            case FOUR_BY_FOUR:   return Board.FOUR_BY_FOUR();
            default:             throw new RuntimeException("Unexpected board type");
        }
    }

    private Player computerPlayer(String mark) {
        return new ComputerPlayer(mark);
    }

    private Player humanPlayer(String mark) {
        return new HumanPlayer(mark, io);
    }
}
