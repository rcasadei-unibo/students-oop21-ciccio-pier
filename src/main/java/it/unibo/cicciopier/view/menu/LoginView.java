package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.controller.menu.MenuAction;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.buttons.Buttons;
import it.unibo.cicciopier.view.menu.buttons.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LoginView extends JPanel {
    private final BufferedImage background;
    private final JTextField textField;

    public LoginView(MainMenuController mainMenuController) {

        CustomButton submitButton = new CustomButton(mainMenuController, Buttons.SUBMIT);

        this.textField = new JTextField("", JTextField.CENTER);
        textField.setHorizontalAlignment(JTextField.CENTER);
        Font font = textField.getFont().deriveFont(Font.BOLD, 20);
        textField.setOpaque(false);
        textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        textField.setFont(font);


        Dimension size = new Dimension(1536, 768);
        this.setPreferredSize(size);
        this.background = Texture.LOGIN_BACKGROUND.getTexture();


        this.setLayout(null);
        this.add(submitButton);
        this.add(textField);

        final Dimension submitButtonSize = submitButton.getPreferredSize();
        final int submitButtonX = size.width / 2 - submitButtonSize.width / 2;
        final int textFieldX = size.width / 2 - 125;

        textField.setBounds(textFieldX, 443, 250, 32);
        submitButton.setBounds(submitButtonX, 600, submitButtonSize.width, submitButtonSize.height);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }

    public String getUsername() {
        return this.textField.getText();
    }

}
