package sk.stuba.fei.uim.oop.tileAttributes;

import java.awt.*;
import java.util.ArrayList;

public class TilePosition extends Point {
    public TilePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private boolean isValid(int boardSize) {
        return (0 <= this.x && this.x < boardSize) &&
                (0 <= this.y && this.y < boardSize);
    }

    private TilePosition[] getNeighborsPos() {
        return new TilePosition[]{
                new TilePosition(this.x - 1, this.y),
                new TilePosition(this.x, this.y + 1),
                new TilePosition(this.x + 1, this.y),
                new TilePosition(this.x, this.y - 1)
        };
    }

    public ArrayList<TilePosition> getValidNeighborsPos(int boardSize) {
        ArrayList<TilePosition> validNeighbors = new ArrayList<>();
        for (TilePosition tilePosition : this.getNeighborsPos()) {
            if (tilePosition.isValid(boardSize)) {
                validNeighbors.add(tilePosition);
            }
        }
        return validNeighbors;
    }

    public TilePosition getNeighborPosByDirection(TileDirection direction, int boardSize) {
        TilePosition[] neighbors = this.getNeighborsPos();
        switch (direction) {
            case UP:
                return neighbors[0].isValid(boardSize) ? neighbors[0] : null;
            case RIGHT:
                return neighbors[1].isValid(boardSize) ? neighbors[1] : null;
            case DOWN:
                return neighbors[2].isValid(boardSize) ? neighbors[2] : null;
            case LEFT:
                return neighbors[3].isValid(boardSize) ? neighbors[3] : null;
            default:
                return null;
        }
    }

    public TileDirection getRelativeDirectionByPos(TilePosition position) {
        TilePosition[] neighborsPos = this.getNeighborsPos();

        for (int i = 0; i < 4; i++) {
            if (neighborsPos[i].equals(position)) {
                switch (i) {
                    case 0:
                        return TileDirection.UP;
                    case 1:
                        return TileDirection.RIGHT;
                    case 2:
                        return TileDirection.DOWN;
                    case 3:
                        return TileDirection.LEFT;
                }
            }
        }
        return TileDirection.NONE;
    }
}
