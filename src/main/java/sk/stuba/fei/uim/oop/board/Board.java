package sk.stuba.fei.uim.oop.board;

import lombok.Getter;
import sk.stuba.fei.uim.oop.randomdfs.PrimitiveTile;
import sk.stuba.fei.uim.oop.randomdfs.RandomDfs;
import sk.stuba.fei.uim.oop.tile.*;
import sk.stuba.fei.uim.oop.tileAttributes.TileDirection;
import sk.stuba.fei.uim.oop.tileAttributes.TilePosition;
import sk.stuba.fei.uim.oop.tileAttributes.TileType;

import java.util.ArrayList;

public class Board {
    @Getter
    private final int size;
    @Getter
    private final Tile[][] tilesArray;
    private final Tile startTile;
    private final Tile endTile;
    @Getter
    private final ArrayList<Tile> lastCheckedPath;

    public Board(int size) {
        this.size = size;

        TilePosition startTilePos = new TilePosition((int) (Math.random() * this.size), 0);
        TilePosition endTilePos = new TilePosition((int) (Math.random() * this.size), size - 1);

        this.tilesArray = new Tile[size][size];
        this.generateBoard(startTilePos, endTilePos);
        this.randomize();

        this.startTile = this.getTileByPos(startTilePos);
        this.endTile = this.getTileByPos(endTilePos);

        this.lastCheckedPath = new ArrayList<>();
    }

    private void generateBoard(TilePosition startTilePos, TilePosition endTilePos) {
        RandomDfs algorithmBoard = new RandomDfs(this.size, startTilePos, endTilePos);
        PrimitiveTile[][] primitiveTileArray = algorithmBoard.getTiles();

        TileType currentType;
        TilePosition currentPosition;
        TileDirection exit1, exit2;

        for (PrimitiveTile[] row : primitiveTileArray) {
            for (PrimitiveTile currentTile : row) {
                currentType = currentTile.getTileType();
                currentPosition = currentTile.getPosition();
                exit1 = currentTile.getEntryDirection();
                exit2 = currentTile.getExitDirection();

                this.createTileAtPos(currentType, currentPosition, exit1, exit2);
            }
        }
    }

    private void randomize() {
        for (Tile[] row : this.tilesArray) {
            for (Tile currentTile : row) {
                currentTile.randomize();
            }
        }
    }

    private Tile getTileByPos(TilePosition position) {
        return this.tilesArray[position.x][position.y];
    }

    private void setTileByPos(TilePosition position, Tile tile) {
        this.tilesArray[position.x][position.y] = tile;
    }

    private void createTileAtPos(TileType type, TilePosition position, TileDirection exit1, TileDirection exit2) {
        switch (type) {
            case BEND:
                this.setTileByPos(position, new Bend(position, this.size, exit1, exit2));
                break;
            case END:
                this.setTileByPos(position, new End(position, this.size, exit1));
                break;
            case STRAIGHT:
                this.setTileByPos(position, new Straight(position, this.size, exit1, exit2));
                break;
            case START:
                this.setTileByPos(position, new Start(position, this.size, exit2));
                break;
            default:
                this.setTileByPos(position, new Blank(position, this.size));
                break;
        }
    }

    private boolean findPathRecursive(Tile currentTile, TileDirection currentEntryDirection, Tile endTile) {
        this.lastCheckedPath.add(currentTile);

        if (currentTile == endTile) {
            return true;
        }

        TilePosition candidate1Pos = currentTile.getNeighborPosByDirection(currentTile.getExitDirection1());
        TilePosition candidate2Pos = currentTile.getNeighborPosByDirection(currentTile.getExitDirection2());
        Tile candidate;
        TileDirection candidateEntryDirection;

        if (currentTile.getExitDirection1() != currentEntryDirection && candidate1Pos != null) {

            candidate = this.getTileByPos(candidate1Pos);
            candidateEntryDirection = currentTile.getExitDirection1().getOppositeDirection();

            if (candidate.getExitDirection1() == candidateEntryDirection ||
                    candidate.getExitDirection2() == candidateEntryDirection
            ) {
                return this.findPathRecursive(candidate, candidateEntryDirection, endTile);
            }
            return false;
        } else if (currentTile.getExitDirection2() != currentEntryDirection && candidate2Pos != null) {

            candidate = this.getTileByPos(candidate2Pos);
            candidateEntryDirection = currentTile.getExitDirection2().getOppositeDirection();

            if (candidate.getExitDirection1() == candidateEntryDirection ||
                    candidate.getExitDirection2() == candidateEntryDirection
            ) {
                return this.findPathRecursive(candidate, candidateEntryDirection, endTile);
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean checkWin() {
        this.lastCheckedPath.clear();
        return this.findPathRecursive(this.startTile, TileDirection.NONE, this.endTile);
    }
}
