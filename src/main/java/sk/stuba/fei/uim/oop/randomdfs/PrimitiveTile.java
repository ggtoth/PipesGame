package sk.stuba.fei.uim.oop.randomdfs;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.tileAttributes.TileDirection;
import sk.stuba.fei.uim.oop.tileAttributes.TilePosition;
import sk.stuba.fei.uim.oop.tileAttributes.TileType;

public class PrimitiveTile {
    @Getter
    private final TilePosition position;
    @Getter
    @Setter
    private boolean isVisited;
    @Getter
    private TileDirection entryDirection;
    @Getter
    private TileDirection exitDirection;
    @Getter
    @Setter
    private TileType tileType;

    PrimitiveTile(TilePosition position) {
        this.isVisited = false;
        this.position = position;
        this.entryDirection = TileDirection.NONE;
        this.exitDirection = TileDirection.NONE;
        this.tileType = TileType.BLANK;
    }

    private TileDirection getRelativeDirection(PrimitiveTile tile) {
        return this.position.getRelativeDirectionByPos(tile.position);
    }

    void connectTiles(PrimitiveTile tile) {
        this.exitDirection = this.getRelativeDirection(tile);
        tile.entryDirection = tile.getRelativeDirection(this);
    }
}
