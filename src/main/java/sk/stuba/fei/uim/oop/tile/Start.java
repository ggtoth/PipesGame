package sk.stuba.fei.uim.oop.tile;

import sk.stuba.fei.uim.oop.controls.MouseInteractDirection;
import sk.stuba.fei.uim.oop.tileAttributes.TileDirection;
import sk.stuba.fei.uim.oop.tileAttributes.TilePosition;

public class Start extends Tile {
    public Start(TilePosition position, int boardSize, TileDirection exitDirection) {
        super(position, boardSize, TileDirection.NONE, exitDirection);
    }

    @Override
    public void interact(MouseInteractDirection direction) {
    }

    @Override
    public void randomize() {
    }
}
