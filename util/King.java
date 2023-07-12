package util;

import game.*;

public class King extends Piece {
    public King(boolean white) {
        super(white);
    }

    @Override
    public boolean isLegal(Move move, Game game) {
        if (!super.isLegal(move, game))
            return false;
        //King Rules:
        int rowDiff = move.getRow1() - move.getRow0();
        int colDiff = move.getCol1() - move.getCol0();
        if (rowDiff == 0) {//Horizontal Movement
            if(Math.abs(colDiff) != 1)
                return false;
            for (int i = 1; i < Math.abs(colDiff); i++)
                if (colDiff > 0 && game.getPiece(move.getRow0(), move.getCol0() + i) != null//to the right
                        || colDiff < 0 && game.getPiece(move.getRow0(), move.getCol1() + i) != null)//to the left
                    return false;
            return true;
        }
        if (colDiff == 0) {//Vertical Movement
            if(Math.abs(rowDiff) != 1)
                return false;
            for (int i = 1; i < Math.abs(rowDiff); i++)
                if (rowDiff > 0 && game.getPiece(move.getRow0() + i, move.getCol0()) != null//moves down
                        || rowDiff < 0 && game.getPiece(move.getRow1() + i, move.getCol0()) != null)//moves up
                    return false;
            return true;
        }
        if (Math.abs(rowDiff) == Math.abs(colDiff)) {//Diagonal Movement
            if(Math.abs(rowDiff) != 1 || Math.abs(colDiff) != 1)
                return false;
            for (int i = 1; i < Math.abs(rowDiff); i++)
                if (rowDiff > 0 && colDiff > 0 && game.getPiece(move.getRow0() + i, move.getCol0() + i) != null
                        || rowDiff > 0 && colDiff < 0 && game.getPiece(move.getRow0() + i, move.getCol1() + i) != null
                        || rowDiff < 0 && colDiff > 0 && game.getPiece(move.getRow1() + i, move.getCol0() + i) != null
                        || rowDiff < 0 && colDiff < 0 && game.getPiece(move.getRow1() + i, move.getCol1() + i) != null)
                    return false;
            return true;
        }
        return false;
        //King is the same as queen, but with the restriction of only being able to move 1 tile at a time
    }

    @Override
    public String toString() {
        return white ? "\u2654" : "\u265A";
    }
}
