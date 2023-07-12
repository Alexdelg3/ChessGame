package util;

import game.*;

public class Knight extends Piece {
    public Knight(boolean white) {
        super(white);
    }

    @Override
    public boolean isLegal(Move move, Game game) {
        if (!super.isLegal(move, game))
            return false;
        //Knight Rules:
        return Math.abs((move.getCol1() - move.getCol0()) * (move.getRow1() - move.getRow0())) == 2;
    }

    @Override
    public String toString() {
        return white ? "\u2658" : "\u265E";
    }
}
