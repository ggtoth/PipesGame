package sk.stuba.fei.uim.oop.tile;

import sk.stuba.fei.uim.oop.controls.MouseInteractDirection;
import sk.stuba.fei.uim.oop.tileAttributes.TileDirection;
import sk.stuba.fei.uim.oop.tileAttributes.TilePosition;

public class End extends Tile {
    public End(TilePosition position, int boardSize, TileDirection entryDirection) {
        super(position, boardSize, entryDirection, TileDirection.NONE);
    }

    @Override
    public void interact(MouseInteractDirection direction) {
    }

    @Override
    public void randomize() {
    }
}
