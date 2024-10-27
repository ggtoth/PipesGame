package sk.stuba.fei.uim.oop.randomdfs;

import lombok.Getter;
import sk.stuba.fei.uim.oop.tileAttributes.TileDirection;
import sk.stuba.fei.uim.oop.tileAttributes.TilePosition;
import sk.stuba.fei.uim.oop.tileAttributes.TileType;

import java.util.ArrayList;

public class RandomDfs {
    private final int size;
    @Getter
    private final PrimitiveTile[][] tiles;
    private final ArrayList<PrimitiveTile> path;

    public RandomDfs(int size, TilePosition start, TilePosition end) {
        this.size = size;

        this.tiles = new PrimitiveTile[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                TilePosition position = new TilePosition(i, j);
                this.setPrimitiveTileByPos(position, new PrimitiveTile(position));
            }
        }
        this.path = new ArrayList<>();
        this.path.add(this.getPrimitiveTileByPos(start));

        this.gen(this.getPrimitiveTileByPos(start), this.getPrimitiveTileByPos(end));

        this.constructTileRepresentation();
    }

    private boolean gen(PrimitiveTile currentTile, PrimitiveTile endTile) {
        currentTile.setVisited(true);

        PrimitiveTile nextTile = this.getRandomUnvisitedNeighbor(currentTile);
        while (nextTile != null) {
            currentTile.connectTiles(nextTile);
            this.path.add(nextTile);

            if (nextTile == endTile) {
                return true;
            }
            if (this.gen(nextTile, endTile)) {
                return true;
            }

            nextTile = this.getRandomUnvisitedNeighbor(currentTile);
        }
        this.path.remove(this.path.size() - 1);
        return false;
    }

    private PrimitiveTile getRandomUnvisitedNeighbor(PrimitiveTile tile) {
        ArrayList<PrimitiveTile> neighbors = this.getUnvisitedNeighbors(tile);

        if (neighbors.size() == 0) {
            return null;
        }
        return neighbors.get((int) (Math.random() * neighbors.size()));
    }

    private ArrayList<PrimitiveTile> getUnvisitedNeighbors(PrimitiveTile tile) {
        ArrayList<TilePosition> allNeighbors = tile.getPosition().getValidNeighborsPos(this.size);

        ArrayList<PrimitiveTile> unvisitedNeighbors = new ArrayList<>();
        for (TilePosition currentPosition : allNeighbors) {
            PrimitiveTile currentTile = this.getPrimitiveTileByPos(currentPosition);
            if (!currentTile.isVisited()) {
                unvisitedNeighbors.add(currentTile);
            }
        }

        return unvisitedNeighbors;
    }

    private void constructTileRepresentation() {
        for (PrimitiveTile[] row : this.tiles) {
            for (PrimitiveTile currentTile : row) {
                currentTile.setTileType(TileType.BLANK);
            }
        }

        for (PrimitiveTile currentTile : this.path) {
            TileDirection entry = currentTile.getEntryDirection();
            TileDirection exit = currentTile.getExitDirection();

            if (entry == TileDirection.NONE) {
                currentTile.setTileType(TileType.START);
            } else if (exit == TileDirection.NONE) {
                currentTile.setTileType(TileType.END);
            } else if ( // magic boolean
                    (entry == TileDirection.UP || entry == TileDirection.DOWN) && (exit == TileDirection.UP || exit == TileDirection.DOWN) ||
                            (entry == TileDirection.RIGHT || entry == TileDirection.LEFT) && (exit == TileDirection.RIGHT || exit == TileDirection.LEFT)
            ) {
                currentTile.setTileType(TileType.STRAIGHT);
            } else {
                currentTile.setTileType(TileType.BEND);
            }
        }
    }

    private PrimitiveTile getPrimitiveTileByPos(TilePosition position) {
        return this.tiles[position.x][position.y];
    }

    private void setPrimitiveTileByPos(TilePosition position, PrimitiveTile primitiveTile) {
        this.tiles[position.x][position.y] = primitiveTile;
    }
}
