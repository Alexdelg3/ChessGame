package util;

import game.*;

public class Piece {
    protected boolean white;//white if true, black otherwise

    public boolean isLegal(Move move, Game game) {
        if (white != game.isWhiteTurn())
            return false;
        Piece captured = game.getPiece(move.getRow1(), move.getCol1());
        if (captured != null && captured.white == this.white)//a piece can't capture a piece of the same color
            return false;
        return true;
    }

    public Piece(boolean white) {
        this.white = white;
    }
}
