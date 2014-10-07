package com.heruku.tictactoe.web;

import com.googlecode.jatl.Html;
import com.heruku.tictactoe.core.Game;

import java.io.StringWriter;
import java.io.Writer;

public class GameHtmlRenderer {
    private final Game game;
    private final Writer out = new StringWriter();
    private final int gameId;
    private boolean addLinks = true;

    public GameHtmlRenderer(Game game, int gameId) {
        this.game = game;
        this.gameId = gameId;
    }

    public String render() {
        for (int i = 0; i < game.boardString().length(); i++) {
            renderCellAtIndex(i);
        }
        return out.toString();
    }

    private void renderCellAtIndex(final int index) {
        new Html(out){{
                div().classAttr("grid-cell");
                if (needsLink(index))
                    renderMarkWithLink(index);
                else
                    renderMark(index);
                end();
          }

            private void renderMark(int square) {
                span().classAttr("mark").text(game.getBoard().squareAt(square));
                end();
            }

            private void renderMarkWithLink(int square) {
                a().href(makeLink(square));
                renderMark(square);
                end();
            }
        };
    }

    private String makeLink(int square) {
        return String.format("/make_move/%d?move=%d", gameId, square);
    }

    private boolean needsLink(int square) {
        if (addLinks)
            return game.getBoard().isValidMove(square) && !game.isOver();
        else
            return false;
    }

    public void noLinks() {
        addLinks = false;
    }
}
