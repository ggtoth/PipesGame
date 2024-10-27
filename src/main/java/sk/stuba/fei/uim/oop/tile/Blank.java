package sk.stuba.fei.uim.oop.tile;

import sk.stuba.fei.uim.oop.controls.MouseInteractDirection;
import sk.stuba.fei.uim.oop.tileAttributes.TileDirection;
import sk.stuba.fei.uim.oop.tileAttributes.TilePosition;

public class Blank extends Tile {
    public Blank(TilePosition position, int boardSize) {
        super(position, boardSize, TileDirection.NONE, TileDirection.NONE);
    }

    @Override
    public void interact(MouseInteractDirection direction) {
    }

    @Override
    public void randomize() {
    }
}
