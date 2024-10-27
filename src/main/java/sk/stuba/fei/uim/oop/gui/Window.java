package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.controls.Game;

import javax.swing.*;
import java.awt.*;

public class Window {
    private final static Color GUI_BACKGROUND_COLOR = Color.WHITE;
    private final static String WINDOW_TITLE = "WaterPipes";
    private final static String RESET_BUTTON_TEXT = "RESTART";
    private final static String CHECK_WIN_BUTTON_TEXT = "CHECK WIN";

    public Window() {
        JFrame frame = this.makeFrame();

        Game game = new Game(frame);
        JPanel sideMenu = this.makeSideMenu(game);

        frame.add(sideMenu, BorderLayout.PAGE_START);
        frame.addKeyListener(game);

        frame.setVisible(true);
    }

    private JFrame makeFrame() {
        JFrame frame = new JFrame(WINDOW_TITLE);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 890);
        frame.getContentPane().setBackground(GUI_BACKGROUND_COLOR);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.requestFocusInWindow();

        return frame;
    }

    private JButton makeButton(Game game, String commandText) {
        JButton button = new JButton(commandText);
        button.addActionListener(game);
        button.setFocusable(false);
        return button;
    }

    private JPanel makeSideMenu(Game game) {
        JPanel sideMenu = new JPanel();
        sideMenu.setBackground(GUI_BACKGROUND_COLOR);
        sideMenu.setLayout(new GridLayout(1, 3));

        JButton resetButton = makeButton(game, RESET_BUTTON_TEXT);
        JPanel checkWinLabelMenu = this.makeWinLabelMenu(game);
        JSlider slider = this.makeJSlider(game);

        game.setResetButton(resetButton);
        game.setBoardSizeSlider(slider);

        sideMenu.add(resetButton);
        sideMenu.add(checkWinLabelMenu);
        sideMenu.add(slider);

        return sideMenu;
    }

    private JPanel makeWinLabelMenu(Game game) {
        JPanel checkWinLabelMenu = new JPanel();
        checkWinLabelMenu.setBackground(GUI_BACKGROUND_COLOR);
        checkWinLabelMenu.setLayout(new GridLayout(1, 2));

        JButton checkWinButton = makeButton(game, CHECK_WIN_BUTTON_TEXT);
        JLabel levelLabel = new JLabel();

        game.setCheckWinButton(checkWinButton);
        game.initializeLevelLabel(levelLabel);

        checkWinLabelMenu.add(checkWinButton);
        checkWinLabelMenu.add(levelLabel);

        return checkWinLabelMenu;
    }

    private JSlider makeJSlider(Game game) {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 8, 16, 8);

        slider.setBackground(GUI_BACKGROUND_COLOR);

        slider.setMinorTickSpacing(2);
        slider.setMajorTickSpacing(2);
        slider.setSnapToTicks(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        slider.addChangeListener(game);

        return slider;
    }
}
