package chessmodel.piece;

public enum Colour {

    BLACK(1),
    WHITE(-1);

    private final int value;

    private Colour(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }

}
