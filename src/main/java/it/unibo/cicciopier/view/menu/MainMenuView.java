package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.App;
import it.unibo.cicciopier.controller.Engine;
import it.unibo.cicciopier.model.entities.Player;
import it.unibo.cicciopier.view.StaticView;
import it.unibo.cicciopier.view.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class MainMenuView extends JPanel implements StaticView {
    private static final int BUTTONHEIGHT = 322;
    private static final int BUTTONSPACE = 15;
    private final String path = "/menuGraphics/menuBackground.png";
    private BufferedImage background;
    private final JFrame jframe = new JFrame("CICCIO PIER THE GAME");
    private final PlayButton play = new PlayButton();
    private final PlayButton leaderboard = new PlayButton();
    private final PlayButton settings = new PlayButton();
    private final JButton quit = new JButton("Quit");
    private final Dimension size = new Dimension(1536, 768);


    public MainMenuView() {
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load() throws Exception {
        final InputStream is = getClass().getResourceAsStream(path);
        background = ImageIO.read(is);

        this.setLayout(null);
        this.add(play);
        this.add(leaderboard);
        this.add(settings);
        // this.add(quit);

        Dimension sizeButton = play.getPreferredSize();
        final int width = size.width / 2 - sizeButton.width / 2;
        final int spacing = sizeButton.height + BUTTONSPACE;

        play.setBounds(width, BUTTONHEIGHT, sizeButton.width, sizeButton.height);

        leaderboard.setBounds(width, BUTTONHEIGHT + spacing, sizeButton.width, sizeButton.height);

        settings.setBounds(width, BUTTONHEIGHT + spacing * 2, sizeButton.width, sizeButton.height);

        //quit.setBounds(width, BUTTONHEIGHT + spacing *3, sizeButton.width, sizeButton.height);


        this.jframe.getContentPane().add(this);
        this.jframe.pack();
        this.jframe.setResizable(false);
        this.jframe.setSize(size);


    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {

        this.setPreferredSize(this.size);
        this.repaint();
        jframe.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }
}
