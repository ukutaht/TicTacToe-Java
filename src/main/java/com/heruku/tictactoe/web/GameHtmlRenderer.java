package com.heruku.tictactoe.web;

import com.googlecode.jatl.Html;
import com.heruku.tictactoe.core.Board;
import com.heruku.tictactoe.core.Move;

import java.io.StringWriter;
import java.io.Writer;

public class GameHtmlRenderer {
    private final Writer out = new StringWriter();
    private final int gameId;
    private final Board board;
    private final Paths paths;

    public GameHtmlRenderer(Board board, int gameId) {
        this.board = board;
        this.gameId = gameId;
        this.paths = new Paths();
    }

    public String render() {
        for (int i = 0; i < board.toString().length(); i++) {
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

            private void renderMarkWithLink(int square) {
                a().href(paths.makeMove(gameId, square));
                renderMark(square);
                end();
            }

            private void renderMark(int square) {
                span().classAttr("mark").text(board.squareAt(square));
                end();
            }
        };
    }

    private boolean needsLink(int square) {
        return board.isValidMove(new Move(square)) &&!board.isOver();
    }
}
