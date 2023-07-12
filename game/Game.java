package game;

import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.*;

import util.*;

public class Game {
    private String player1, player2;
    private ArrayList<Move> moves;
    private Square[][] board;//Black on top(moving down is +), white on bottom (moving up is -)
    public Piece getPiece(int row, int col) {
        return board[row][col].getOccupant();
    }
    public boolean isWhiteTurn() {//specifies whose turn it is: white/player1 or black/player2
        return moves.size() % 2 == 0;
    } //White always goes on even turns
    public String getPlayer1() {
        return player1;
    }
    public String getPlayer2() {
        return player2;
    }
    public ArrayList<Move> getMoves() {
        return moves;
    }

    public Game(String player1, String player2) {//sets up the pieces on the board, initializes player's names.
        this.player1 = player1;
        this.player2 = player2;
        moves = new ArrayList<Move>();
        board = new Square[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                boolean white = (row + col) % 2 == 0;
                if (row == 1)
                    board[row][col] = new Square(white, new Pawn(false));
                else if (row == 6)
                    board[row][col] = new Square(white, new Pawn(true));
                else if (row == 7 || row == 0) {
                    if (col == 0 || col == 7)
                        board[row][col] = new Square(white, new Rook(row == 7));
                    else if (col == 1 || col == 6)
                        board[row][col] = new Square(white, new Knight(row == 7));
                    else if (col == 2 || col == 5)
                        board[row][col] = new Square(white, new Bishop(row == 7));
                    else if (col == 3)
                        board[row][col] = new Square(white, new Queen(row == 7));
                    else
                        board[row][col] = new Square(white, new King(row == 7));
                } else
                    board[row][col] = new Square(white, null);
            }
        }
    }

    public boolean move(Move move) {//makes a move, returns true if successful, false otherwise.
        Square src = board[move.getRow0()][move.getCol0()], dst = board[move.getRow1()][move.getCol1()];
        if (src.getOccupant() == null ||//empty src square
                !src.getOccupant().isLegal(move, this))//illegal move
            return false;
        moves.add(move);
        dst.setOccupant(src.getOccupant());//Sets the destination to the source
        src.setOccupant(null);//Sets the source to nothing
        return true;
    }
    /**
     * New Method: capture. Overwrites a piece of one color with another.
     * @param move is the move whose legality is checked.
     * @return returns true if the capture was successful and false if not.
     */
    public boolean capture(Move move) {//attempts a capture, returns true if successful, false otherwise.
        Square src = board[move.getRow0()][move.getCol0()], dst = board[move.getRow1()][move.getCol1()];
        if (dst.getOccupant() == null ||//if destination is empty
                !src.getOccupant().isLegal(move, this))//or if the occupant's move is illegal
            return false;
        if(getPiece(move.getRow1(),move.getCol1()) == null)//If the target tile is empty
            return false;//then return false
        moves.add(move);
        dst.setOccupant(src.getOccupant());//Sets the destination to the source
        src.setOccupant(null);//Sets the source to nothing
        return true;
    }
    /**
     * New Method: subtractMove. Makes a new game and recreates all moves of a
     * passed in game, except the last.
     * @param game is the game the user would like to undo a move of.
     * @return returns the original game with 1 less move.
     */
    public Game subtractMove(Game game){
        Game newgame = new Game(game.getPlayer1(), game.getPlayer2());
        for (int i = 0; i < game.moves.size() - 1; i++)//Copies all the moves except the last in the created game newgame
            newgame.move(moves.get(i));
        game = newgame;//Overwrites the old game with the new one, except with the last move
        return game;
    }

    @Override
    public String toString() {
        return moves.toString();
    }
    //NOTE: This has been edited a little to look better on my computer. Output may vary.
    public void showBoard(PrintStream stream) {
        stream.println(player2 + "\n______________________________________\n\ta\tb\tc\td\te\tf\tg\th");
        for (int row = 0; row < 8; row++) {
            for (int col = -1; col < 8; col++) {
                if (col < 0)
                    stream.print(8 - row);
                else if (getPiece(row, col) != null)
                    stream.print(getPiece(row, col));
                else if (getPiece(row, col) == null)
                    stream.print("  ");
                stream.print(col == -1 ? "  " : col != 7 ? "\t" : row != 7 ? "\n\n\n" : "\n");
            }
        }
        stream.println("______________________________________\n" + player1);
    }
}
