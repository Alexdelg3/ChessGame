package game;

public class Move {
    private int row0, col0;//(row0, col0) specifies the row/col numbers of the original square where a piece is moved from
    private int row1, col1;//(row1, col1) specifies the row/col numbers of the square where a piece is moved to
    private String move;

    public int getRow0() {
        return row0;
    }

    public int getCol0() {
        return col0;
    }

    public int getRow1() {
        return row1;
    }

    public int getCol1() {
        return col1;
    }

    public Move(String move) {//move: "b2c4 or f2f7, etc
        assert (move.length() == 4);
        this.move = move;
        row0 = 8 - move.charAt(1) + '0';
        row1 = 8 - move.charAt(3) + '0';
        col0 = move.charAt(0) - 'a';
        col1 = move.charAt(2) - 'a';
    }

    @Override
    public String toString() {
        return move;
    }
}
