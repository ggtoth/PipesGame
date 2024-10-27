package sk.stuba.fei.uim.oop.gui;

import lombok.Getter;
import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.controls.Game;
import sk.stuba.fei.uim.oop.tile.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BoardGUI extends JPanel {
    private final static Color BOARD_BACKGROUND_COLOR = Color.WHITE;
    private final static int PATH_HIGHLIGHT_DELAY = 500;
    @Getter
    private final Timer pathTimer;
    private final Board board;

    public BoardGUI(Board board, Game game) {
        this.board = board;

        this.setBackground(BOARD_BACKGROUND_COLOR);
        this.setLayout(new GridLayout(this.board.getSize(), this.board.getSize()));

        for (Tile[] row : this.board.getTilesArray()) {
            for (Tile currentTile : row) {
                TileGUI tileGUI = new TileGUI(currentTile);
                tileGUI.addMouseListener(game);
                this.add(tileGUI);
            }
        }

        this.pathTimer = new Timer(PATH_HIGHLIGHT_DELAY, e -> this.pathTimerStop());
    }

    private void pathTimerStop() {
        this.pathTimer.stop();
        this.clearPathHighlight();
    }

    public void highlightPathTimerInterrupt() {
        this.pathTimerStop();
    }

    public void applyPathHighlight() {
        if (this.pathTimer.isRunning()) {
            this.pathTimer.restart();
            return;
        }

        ArrayList<Tile> lastPath = this.board.getLastCheckedPath();
        for (Component component : this.getComponents()) {
            if (component instanceof TileGUI && lastPath.contains(((TileGUI) component).getTile())) {
                ((TileGUI) component).setHighlightPath(true);
            }
        }

        this.repaint();
        this.pathTimer.start();
    }

    private void clearPathHighlight() {
        for (Component component : this.getComponents()) {
            if (component instanceof TileGUI) {
                ((TileGUI) component).setHighlightPath(false);
            }
        }
        this.repaint();
    }
}
