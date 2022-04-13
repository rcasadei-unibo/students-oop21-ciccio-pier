package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.model.Level;
import it.unibo.cicciopier.model.User;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.buttons.Buttons;
import it.unibo.cicciopier.view.menu.buttons.CustomButton;
import it.unibo.cicciopier.view.menu.buttons.LeaderboardLevelSelectionButton;
import it.unibo.cicciopier.view.menu.buttons.ViewPanelButton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LeaderboardView extends JPanel {
    private final BufferedImage background;
    private final JLabel loggedUser;
    private final MainMenuController mainMenuController;
    private final JList<User> jList;
    private final DefaultListModel<User> test = new DefaultListModel<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(LeaderboardView.class);

    public LeaderboardView(MainMenuController mainMenuController) {
        final JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.setPreferredSize(new Dimension(600, 270));
        panel.setOpaque(false);
        this.mainMenuController = mainMenuController;

        this.loggedUser = new JLabel("Logged user: " + this.mainMenuController.getUsername());
        final Font font = loggedUser.getFont().deriveFont(Font.BOLD, 24);
        this.jList = new JList<>(this.test);
        final JScrollPane jScrollPane = new JScrollPane(jList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        jScrollPane.setOpaque(false);
        jScrollPane.getViewport().setOpaque(false);
        jScrollPane.setPreferredSize(new Dimension(600, 270));
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane.getVerticalScrollBar().setBackground(new Color(210, 175, 128, 255));
        jScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.BLACK;
            }
        });

        this.jList.setLayoutOrientation(JList.VERTICAL);
        this.jList.setOpaque(false);
        this.jList.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        this.jList.setFont(font);
        this.jList.setVisibleRowCount(1);
        this.jList.setFixedCellWidth(600);
        this.jList.setSelectionModel(new NoSelectionModel());
        this.jList.setCellRenderer(new TransparentListCellRenderer(Level.FIRST_LEVEL));


        this.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        CustomButton level1 = new LeaderboardLevelSelectionButton(this.mainMenuController, Buttons.LEVEL1, Level.FIRST_LEVEL);
        CustomButton level2 = new LeaderboardLevelSelectionButton(this.mainMenuController, Buttons.LEVEL2, Level.SECOND_LEVEL);
        CustomButton level3 = new LeaderboardLevelSelectionButton(this.mainMenuController, Buttons.LEVEL3, Level.THIRD_LEVEL);
        CustomButton level4 = new LeaderboardLevelSelectionButton(this.mainMenuController, Buttons.LEVEL_BOSS, Level.BOSS_LEVEL);

        CustomButton settings = new ViewPanelButton(this.mainMenuController, Buttons.SETTINGS, ViewPanels.SETTINGS);

        CustomButton home = new ViewPanelButton(this.mainMenuController, Buttons.HOME, ViewPanels.MAIN_MENU);

        this.loggedUser.setFont(font.deriveFont(Font.BOLD, 20));
        this.loggedUser.setForeground(Color.WHITE);


        Dimension size = new Dimension(1536, 768);
        this.setPreferredSize(size);
        this.background = Texture.LEADERBOARD_BACKGROUND.getTexture();

        this.setLayout(null);
        panel.add(jScrollPane);
        this.add(level1);
        this.add(level2);
        this.add(level3);
        this.add(level4);
        this.add(panel);
        this.add(home);
        this.add(settings);
        this.add(loggedUser);

        final Dimension sizeSettings = settings.getPreferredSize();
        final int settingsWidthOffset = size.width - sizeSettings.width - 60;
        final int listX = size.width / 2 - 300;
        final int listY = size.height / 2;
        final int buttonsStart = size.width / 2 - level1.getPreferredSize().width * 2 - 30;
        final int homeWidthOffset = 60;
        final int settingsHeightOffset = 20;

        settings.setBounds(settingsWidthOffset, settingsHeightOffset, sizeSettings.width, sizeSettings.height);
        panel.setBounds(listX, listY, panel.getPreferredSize().width, panel.getPreferredSize().height);
        home.setBounds(homeWidthOffset, settingsHeightOffset, sizeSettings.width, sizeSettings.height);
        this.loggedUser.setBounds(homeWidthOffset, settingsHeightOffset + sizeSettings.height + 10, 300, 30);
        level1.setBounds(buttonsStart, listY + panel.getPreferredSize().height+10, level1.getPreferredSize().width, level1.getPreferredSize().height);
        level2.setBounds(buttonsStart + level1.getPreferredSize().width + 20, listY + panel.getPreferredSize().height+10, level2.getPreferredSize().width, level2.getPreferredSize().height);
        level3.setBounds(buttonsStart + level1.getPreferredSize().width * 2 + 40, listY + panel.getPreferredSize().height+10, level3.getPreferredSize().width, level3.getPreferredSize().height);
        level4.setBounds(buttonsStart + level1.getPreferredSize().width * 3 + 60, listY + panel.getPreferredSize().height+10, level4.getPreferredSize().width, level4.getPreferredSize().height);


    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }

    public void updateLoggedUser() {
        loggedUser.setText(("Logged user: " + mainMenuController.getUsername()));
    }

    public void updateLeaderboard(Level level) {
        List<User> leaderboard = mainMenuController.getUsers().stream().filter(user -> user.getLevelScore(level.getJsonId()) >= 0).sorted(Comparator.comparingInt(user -> user.getLevelScore(level.getJsonId()))).collect(Collectors.toList());
        Collections.reverse(leaderboard);
        test.removeAllElements();
        leaderboard.forEach(test::addElement);
        this.jList.removeAll();
        this.jList.setModel(this.test);
        this.jList.setCellRenderer(new TransparentListCellRenderer(level));
        LOGGER.info("Loading leaderboard from: "+level.getName());

    }
}
