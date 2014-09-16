package com.heruku.tictactoe.core;

public class Player {
    private String mark;
    private Strategy strategy;
    private static Player playerX;
    private static Player playerO;

    public static Player X(Strategy strategy){
        playerX = new Player("X", strategy);
        return playerX;
    }

    public static Player O(Strategy strategy){
        playerO = new Player("O", strategy);
        return playerO;
    }

    public static Player X(){
        return playerX;
    }

    public static Player O(){
        return playerO;
    }

    private Player(String mark, Strategy strategy){
        this.mark     = mark;
        this.strategy = strategy;
    }

    public String getMark(){
        return mark;
    }

    public int getMove(Board board){
        return strategy.getMove(board);
    }

    public Player next(){
        if(this == playerX)
            return playerO;
        return playerX;
    }
}
