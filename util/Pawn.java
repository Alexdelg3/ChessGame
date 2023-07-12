package util;

import game.*;

public class Pawn extends Piece {
    public Pawn(boolean white) {
        super(white);
    }

    @Override
    public boolean isLegal(Move move, Game game) {
        if (!super.isLegal(move, game))
            return false;
        //Pawn Rules:
        int rowDiff = move.getRow1() - move.getRow0();
        int colDiff = move.getCol1() - move.getCol0();
        if (rowDiff > 0 && white || rowDiff < 0 && !white)//Cannot move backwards
            return false;
        if(game.getPiece(move.getRow1(), move.getCol1()) == null){//If the space you are moving to is empty
            if(colDiff != 0)
                return false;
            if(rowDiff == 2 && move.getRow0() != 1)//For white pieces
                return false;
            if(rowDiff == -2 && move.getRow0() != 6)//For black pieces
                return false;
            if(Math.abs(rowDiff) != 1 && Math.abs(rowDiff) != 2)
                return false;
            return true;
        }else{//Pawn Capturing:
            if(Math.abs(rowDiff) == 1 && Math.abs(colDiff) == 1 && game.getPiece(move.getRow1(),move.getCol1()) != null)
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return white ? "\u2659" : "\u265F";
    }
}
