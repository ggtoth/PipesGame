package sk.stuba.fei.uim.oop.tileAttributes;

public enum TileDirection {
    UP,
    RIGHT,
    DOWN,
    LEFT,
    NONE;

    public TileDirection getOppositeDirection() {
        switch (this) {
            case UP:
                return TileDirection.DOWN;
            case RIGHT:
                return TileDirection.LEFT;
            case DOWN:
                return TileDirection.UP;
            case LEFT:
                return TileDirection.RIGHT;
            default:
                return TileDirection.NONE;
        }
    }

    public TileDirection getNextDirectionClockwise() {
        switch (this) {
            case UP:
                return TileDirection.RIGHT;
            case RIGHT:
                return TileDirection.DOWN;
            case DOWN:
                return TileDirection.LEFT;
            case LEFT:
                return TileDirection.UP;
            default:
                return TileDirection.NONE;
        }
    }

    public TileDirection getNextDirectionAntiClockwise() {
        switch (this) {
            case UP:
                return TileDirection.LEFT;
            case RIGHT:
                return TileDirection.UP;
            case DOWN:
                return TileDirection.RIGHT;
            case LEFT:
                return TileDirection.DOWN;
            default:
                return TileDirection.NONE;
        }
    }
}
