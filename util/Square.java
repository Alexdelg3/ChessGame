package util;

public class Square {
    private boolean lightColor;
    private Piece occupant;

    public boolean isLightColor() {
        return lightColor;
    }

    public Piece getOccupant() {
        return occupant;
    }

    public void setOccupant(Piece occupant) {
        this.occupant = occupant;
    }

    public Square(boolean lightColor, Piece occupant) {
        this.lightColor = lightColor;
        this.occupant = occupant;
    }
}
