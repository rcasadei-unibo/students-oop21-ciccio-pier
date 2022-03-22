package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.App;
import it.unibo.cicciopier.controller.Engine;
import it.unibo.cicciopier.model.entities.Player;
import it.unibo.cicciopier.view.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class MainMenuView extends JPanel implements View {
    private final Engine engine;
    private final String path = "/menuGraphics/menuBackground.png";
    private BufferedImage background;
    private final JFrame jframe = new JFrame("CICCIO PIER THE GAME");
    private final JButton play = new JButton("Play");
    private final JButton leaderboard = new JButton("Leaderboard");
    private final JButton settings = new JButton("Settings");
    private final JButton quit = new JButton("Quit");
    Dimension size = new Dimension(1536,768);


    public MainMenuView(Engine engine) {
        this.engine = engine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load() throws Exception {
        final InputStream is = getClass().getResourceAsStream(path);
        background = ImageIO.read(is);
        this.add(play);
        this.add(leaderboard);
        this.add(settings);
        this.add(quit);
        jframe.getContentPane().add(this);
        jframe.pack();
        jframe.setResizable(false);
        jframe.setSize(size);



    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {

        this.setPreferredSize(size);
        this.repaint();
        jframe.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {



    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Engine getEngine() {
        return this.engine;
    }

}
