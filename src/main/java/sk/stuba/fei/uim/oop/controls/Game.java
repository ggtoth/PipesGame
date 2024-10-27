package sk.stuba.fei.uim.oop.controls;

import lombok.Setter;
import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.gui.BoardGUI;
import sk.stuba.fei.uim.oop.gui.TileGUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Game extends UniversalAdapter {
    private final static int INITIAL_BOARD_SIZE = 8;
    private final static int WIN_DELAY = 500;
    private final JFrame window;
    private final Timer winDelayTimer;
    private int currentBoardSize;
    private Board board;
    @Setter
    private JButton resetButton;
    @Setter
    private JButton checkWinButton;
    @Setter
    private JSlider boardSizeSlider;
    private JLabel levelLabel;
    private BoardGUI boardGUI;
    private int level;

    public Game(JFrame window) {
        this.window = window;

        this.currentBoardSize = INITIAL_BOARD_SIZE;
        this.board = new Board(this.currentBoardSize);
        this.boardGUI = new BoardGUI(this.board, this);

        this.level = 1;

        this.winDelayTimer = new Timer(WIN_DELAY, e -> this.winFunction());

        this.window.add(this.boardGUI);
    }

    private void winFunction() {
        this.winDelayTimer.stop();
        this.level++;
        this.updateLevelLabel();
        this.genNew();
    }

    public void initializeLevelLabel(JLabel levelLabel) {
        this.levelLabel = levelLabel;
        this.updateLevelLabel();
    }

    private void updateLevelLabel() {
        if (this.levelLabel != null) {
            this.levelLabel.setText("Level: " + this.level);
            this.levelLabel.repaint();
        }
    }

    private void genNew() {
        this.window.remove(this.boardGUI);

        this.board = new Board(this.currentBoardSize);
        this.boardGUI = new BoardGUI(this.board, this);

        this.window.add(this.boardGUI);
        this.window.revalidate();
        this.window.repaint();
        this.window.setFocusable(true);
        this.window.requestFocus();
    }

    private void reset() {
        if (this.winDelayTimer.isRunning()) {
            this.winDelayTimer.stop();
        }
        this.level = 1;
        this.updateLevelLabel();
        this.genNew();
    }

    private void winCheck() {
        if (this.winDelayTimer.isRunning()) {
            this.winDelayTimer.restart();
            this.boardGUI.getPathTimer().restart();
            return;
        }

        boolean winResult = this.board.checkWin();
        this.boardGUI.applyPathHighlight();

        if (winResult) {
            this.winDelayTimer.start();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof TileGUI) {
            if (this.winDelayTimer.isRunning()) {
                winDelayTimer.stop();
            }

            if (this.boardGUI.getPathTimer().isRunning()) {
                this.boardGUI.highlightPathTimerInterrupt();
            }

            MouseInteractDirection direction;
            if (e.getButton() == MouseEvent.BUTTON1) {
                direction = MouseInteractDirection.CLOCKWISE;
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                direction = MouseInteractDirection.ANTI_CLOCKWISE;
            } else {
                return;
            }

            ((TileGUI) e.getSource()).getTile().interact(direction);
            ((TileGUI) e.getSource()).repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof TileGUI) {
            ((TileGUI) e.getSource()).setHighlightMouseHover(true);
            ((TileGUI) e.getSource()).repaint();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof TileGUI) {
            ((TileGUI) e.getSource()).setHighlightMouseHover(false);
            ((TileGUI) e.getSource()).repaint();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == this.boardSizeSlider && !this.boardSizeSlider.getValueIsAdjusting()) {
            int sliderValue = this.boardSizeSlider.getValue();
            if (this.currentBoardSize != sliderValue) {
                this.currentBoardSize = sliderValue;
                this.reset();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                this.reset();
                break;
            case KeyEvent.VK_ENTER:
                this.winCheck();
                break;
            case KeyEvent.VK_ESCAPE:
                this.window.dispose();
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.checkWinButton) {
            this.winCheck();
        } else if (e.getSource() == this.resetButton) {
            this.reset();
        }
    }
}
