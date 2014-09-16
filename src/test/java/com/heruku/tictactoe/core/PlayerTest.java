package com.heruku.tictactoe.core;

import com.heruku.tictactoe.core.Player;
import com.heruku.tictactoe.strategies.MockStrategy;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testBuildsPlayerX(){
        Player player = Player.X(new MockStrategy());
        assertEquals("X", player.getMark());
    }

    @Test
    public void testBuildsPlayerO(){
        Player player = Player.O(new MockStrategy());
        assertEquals("O", player.getMark());
    }

    @Test
    public void testMemoizesPlayerX(){
        Player player = Player.X(new MockStrategy());
        assertTrue(player == Player.X());
    }

    @Test
    public void testMemoizesPlayerO(){
        Player player = Player.O(new MockStrategy());
        assertTrue(player == Player.O());
    }

    @Test
    public void testNext(){
        Player playerX = Player.X(new MockStrategy());
        Player playerO = Player.O(new MockStrategy());
        assertEquals(playerO, playerX.next());
        assertEquals(playerX, playerO.next());
    }

    @Test
    public void testUsesStrategy(){
        Player player = Player.O(new MockStrategy());
        assertEquals(4, player.getMove(new Board("         ")));
    }
}