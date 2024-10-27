package sk.stuba.fei.uim.oop.gui;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.tile.End;
import sk.stuba.fei.uim.oop.tile.Start;
import sk.stuba.fei.uim.oop.tile.Tile;
import sk.stuba.fei.uim.oop.tileAttributes.TileDirection;

import javax.swing.*;
import java.awt.*;

public class TileGUI extends JPanel {
    private final static Color PATH_HIGHLIGHT_COLOR = Color.BLUE;
    private final static Color MOUSE_HOVER_HIGHLIGHT_COLOR = Color.YELLOW;
    private final static Color BORDER_COLOR = Color.BLACK;
    private final static Color BACKGROUND_COLOR = Color.WHITE;
    private final static Color START_PIPE_COLOR = Color.GREEN;
    private final static Color END_PIPE_COLOR = Color.RED;
    private final static Color DEFAULT_PIPE_COLOR = Color.GRAY;
    @Getter
    private final Tile tile;
    private final Color originalColor;
    @Setter
    private boolean highlightMouseHover;
    @Setter
    private boolean highlightPath;
    private Color currentColor;

    TileGUI(Tile tile) {
        this.tile = tile;

        this.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
        this.setBackground(BACKGROUND_COLOR);

        if (this.tile instanceof Start) {
            this.originalColor = START_PIPE_COLOR;
        } else if (this.tile instanceof End) {
            this.originalColor = END_PIPE_COLOR;
        } else {
            this.originalColor = DEFAULT_PIPE_COLOR;
        }

        this.currentColor = this.originalColor;
    }

    private void drawExitTube(Graphics g, TileDirection exit) {
        switch (exit) {
            case UP:
                g.fillRect(this.getWidth() / 2 - this.getWidth() / 8, 0, this.getWidth() / 8 * 2, this.getHeight() / 2);
                break;
            case RIGHT:
                g.fillRect(this.getWidth() / 2 - this.getWidth() / 8, this.getHeight() / 2 - this.getHeight() / 8, this.getWidth() / 2 + this.getWidth() / 8, this.getHeight() / 8 * 2);
                break;
            case DOWN:
                g.fillRect(this.getWidth() / 2 - this.getWidth() / 8, this.getHeight() / 2 - this.getHeight() / 8, this.getWidth() / 8 * 2, this.getHeight() / 2 + this.getHeight() / 8 * 2);
                break;
            case LEFT:
                g.fillRect(0, this.getHeight() / 2 - this.getHeight() / 8, this.getWidth() / 2 + this.getWidth() / 8, this.getHeight() / 8 * 2);
                break;
            default:
                break;
        }
    }

    private void drawStartEndCircle(Graphics g) {
        g.setColor(this.originalColor);
        g.fillOval(
                (int) (0 + this.getWidth() * 0.1), (int) (0 + this.getHeight() * 0.1),
                (int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.8)
        );
        g.setColor(this.currentColor);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (this.highlightMouseHover) {
            g.setColor(MOUSE_HOVER_HIGHLIGHT_COLOR);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }

        if (this.highlightPath) {
            this.currentColor = PATH_HIGHLIGHT_COLOR;
        } else {
            this.currentColor = this.originalColor;
        }

        g.setColor(this.currentColor);

        this.drawExitTube(g, this.tile.getExitDirection1());
        this.drawExitTube(g, this.tile.getExitDirection2());

        if (this.tile instanceof Start || this.tile instanceof End) {
            this.drawStartEndCircle(g);
        }
    }
}
