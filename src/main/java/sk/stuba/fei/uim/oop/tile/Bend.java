package sk.stuba.fei.uim.oop.tile;

import sk.stuba.fei.uim.oop.controls.MouseInteractDirection;
import sk.stuba.fei.uim.oop.tileAttributes.TileDirection;
import sk.stuba.fei.uim.oop.tileAttributes.TilePosition;

public class Bend extends Tile {
    public Bend(TilePosition position, int boardSize, TileDirection exitDirection1, TileDirection exitDirection2) {
        super(position, boardSize, exitDirection1, exitDirection2);
    }

    @Override
    public void interact(MouseInteractDirection direction) {
        if (direction == MouseInteractDirection.CLOCKWISE) {
            this.rotateClockwise();
        } else {
            this.rotateAntiClockwise();
        }
    }

    @Override
    public void randomize() {
        int randomNumber = (int) (Math.random() * 4);
        for (int i = 0; i < randomNumber; i++) {
            this.rotateClockwise();
        }
    }
}
