package sk.stuba.fei.uim.oop.tile;

import lombok.Getter;
import sk.stuba.fei.uim.oop.controls.MouseInteractDirection;
import sk.stuba.fei.uim.oop.tileAttributes.TileDirection;
import sk.stuba.fei.uim.oop.tileAttributes.TilePosition;

public abstract class Tile {
    private final TilePosition position;
    private final int boardSize;
    @Getter
    private TileDirection exitDirection1;
    @Getter
    private TileDirection exitDirection2;

    public Tile(TilePosition position, int boardSize, TileDirection exitDirection1, TileDirection exitDirection2) {
        this.exitDirection1 = exitDirection1;
        this.exitDirection2 = exitDirection2;
        this.position = position;
        this.boardSize = boardSize;
    }

    public TilePosition getNeighborPosByDirection(TileDirection direction) {
        return this.position.getNeighborPosByDirection(direction, this.boardSize);
    }

    protected void rotateClockwise() {
        this.exitDirection1 = this.exitDirection1.getNextDirectionClockwise();
        this.exitDirection2 = this.exitDirection2.getNextDirectionClockwise();
    }

    protected void rotateAntiClockwise() {
        this.exitDirection1 = this.exitDirection1.getNextDirectionAntiClockwise();
        this.exitDirection2 = this.exitDirection2.getNextDirectionAntiClockwise();
    }

    public abstract void interact(MouseInteractDirection direction);

    public abstract void randomize();
}
