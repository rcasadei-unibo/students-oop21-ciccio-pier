package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.AudioController;
import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.controller.menu.MenuAction;
import it.unibo.cicciopier.model.Sound;
import it.unibo.cicciopier.model.settings.CustomFont;
import it.unibo.cicciopier.model.settings.Screen;
import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.buttons.Buttons;
import it.unibo.cicciopier.view.menu.buttons.CustomButton;
import it.unibo.cicciopier.view.menu.buttons.MenuActionButton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class represents an instance of the login view
 */
public class LoginView extends JPanel implements MenuPanel {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginView.class);
    private final JTextField textField;
    private final CustomButton submitButton;
    private final MainMenuController mainMenuController;

    /**
     * This constructor creates the whole panel with his components
     *
     * @param mainMenuController the instance of the {@link MainMenuController}
     */
    public LoginView(MainMenuController mainMenuController) {
        LoginView.LOGGER.info("Initializing the class...");
        this.mainMenuController = mainMenuController;
        this.submitButton = new MenuActionButton(
                mainMenuController,
                Buttons.SUBMIT,
                MenuAction.LOGIN
        );
        this.textField = new JTextField("", JTextField.CENTER);
        this.load();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load() {
        LoginView.LOGGER.info("Loading the class...");
        this.textField.setHorizontalAlignment(JTextField.CENTER);
        this.textField.setOpaque(false);
        this.textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        this.textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                AudioController.getInstance().playSound(Sound.TYPING);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.textField.addActionListener(e -> mainMenuController.action(MenuAction.LOGIN));
        this.setLayout(null);
        this.add(this.submitButton);
        this.add(this.textField);
    }

    /**
     * This function update the current logged user username in the text area
     */
    public String getUsername() {
        return this.textField.getText();
    }

    public void logout() {
        this.textField.setText("");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        LoginView.LOGGER.info("Updating the class...");
        this.setPreferredSize(Screen.getCurrentDimension());
        this.textField.setFont(CustomFont.getInstance().getFontOrDefault());
        this.textField.setPreferredSize(new Dimension((int) (225 * Screen.getScale()), (int) (35 * Screen.getScale())));
        this.setPreferredSize(Screen.getCurrentDimension());
        final Pair<Integer> submitPos = new Pair<>(
                Screen.getCurrentDimension().width / 2 - this.submitButton.getPreferredSize().width / 2,
                (int) (Screen.getCurrentDimension().height / 1.28)
        );
        final Pair<Integer> textFieldPos = new Pair<>(
                (Screen.getCurrentDimension().width - this.textField.getPreferredSize().width) / 2,
                (Screen.getCurrentDimension().height - this.textField.getPreferredSize().height) / 2 + Screen.getCurrentDimension().height / 10
        );
        this.textField.setBounds(
                textFieldPos.getX(),
                textFieldPos.getY(),
                this.textField.getPreferredSize().width,
                this.textField.getPreferredSize().height
        );
        this.submitButton.setBounds(submitPos.getX(),
                submitPos.getY(),
                this.submitButton.getPreferredSize().width,
                this.submitButton.getPreferredSize().height
        );
        this.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(
                Texture.LOGIN_BACKGROUND.getTexture(),
                0,
                0,
                Screen.getCurrentDimension().width,
                Screen.getCurrentDimension().height,
                null
        );
    }

}
