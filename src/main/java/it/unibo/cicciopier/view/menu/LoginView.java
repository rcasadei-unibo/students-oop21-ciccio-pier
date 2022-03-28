package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.controller.menu.MenuAction;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.buttons.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LoginView extends JPanel {
    private final BufferedImage background;

    public LoginView(MainMenuController mainMenuController) {

        BufferedImage[] bufferedImage = new BufferedImage[2];
        bufferedImage[0] = Texture.SUBMIT_BUTTON.getTexture();
        bufferedImage[1] = Texture.SUBMIT_BUTTON_PRESSED.getTexture();
        CustomButton submitButton = new CustomButton(mainMenuController,new Dimension(150, 57),bufferedImage, MenuAction.LOGIN,false);

        Dimension size = new Dimension(1536, 768);
        this.setPreferredSize(size);
        this.background = Texture.LOGIN_BACKGROUND.getTexture();

        this.setLayout(null);
        this.add(submitButton);

        final Dimension submitButtonSize = submitButton.getPreferredSize();
        final int submitButtonX = size.width / 2 - submitButtonSize.width / 2;

        submitButton.setBounds(submitButtonX,600,submitButtonSize.width,submitButtonSize.height);

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
