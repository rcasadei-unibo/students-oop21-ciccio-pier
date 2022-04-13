package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.controller.menu.ViewPanels;
import it.unibo.cicciopier.model.Level;
import it.unibo.cicciopier.model.User;
import it.unibo.cicciopier.utility.Pair;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents an instance of the leaderboard view
 */
public class LeaderboardView extends JPanel {
    private final JLabel loggedUser;
    private final MainMenuController mainMenuController;
    private final JList<User> jList;
    private final DefaultListModel<User> test = new DefaultListModel<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(LeaderboardView.class);

    /**
     * This constructor creates the whole panel with all his components
     *
     * @param mainMenuController the instance of the {@link MainMenuController}
     */
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

        panel.add(jScrollPane);

        Dimension size = new Dimension(1536, 768);
        this.setPreferredSize(size);

        this.setLayout(null);
        this.add(this.loggedUser);
        this.add(level1);
        this.add(level2);
        this.add(level3);
        this.add(level4);
        this.add(panel);
        this.add(home);
        this.add(settings);


        final int homeWidthOffset = size.width / 25;
        final int settingsWidthOffset = size.width - settings.getPreferredSize().width - homeWidthOffset;
        final int listX = size.width / 2 - panel.getPreferredSize().width;
        final int listY = size.height / 2;
        final int buttonsStart = size.width / 2 - level1.getPreferredSize().width * 2 - size.width / 50;
        final int levelButtonHeightOffset = size.height / 80;
        final int settingsHeightOffset = (int) (size.height / 38.4);

        final Pair<Integer> level1Pos = new Pair<>(buttonsStart,
                listY + panel.getPreferredSize().height + levelButtonHeightOffset);

        final Pair<Integer> level2Pos = new Pair<>(buttonsStart + level2.getPreferredSize().width + settingsHeightOffset,
                listY + panel.getPreferredSize().height + levelButtonHeightOffset);

        final Pair<Integer> level3Pos = new Pair<>(buttonsStart + (level3.getPreferredSize().width + settingsHeightOffset) * 2,
                listY + panel.getPreferredSize().height + levelButtonHeightOffset);

        final Pair<Integer> level4Pos = new Pair<>(buttonsStart + (level4.getPreferredSize().width + settingsHeightOffset) * 3,
                listY + panel.getPreferredSize().height + levelButtonHeightOffset);

        this.loggedUser.setBounds(homeWidthOffset, settingsHeightOffset + settings.getPreferredSize().height + (size.height / 75),
                this.loggedUser.getPreferredSize().width, this.loggedUser.getPreferredSize().height);

        settings.setBounds(settingsWidthOffset, settingsHeightOffset, settings.getPreferredSize().width,
                settings.getPreferredSize().height);

        panel.setBounds(listX, listY, panel.getPreferredSize().width, panel.getPreferredSize().height);

        home.setBounds(homeWidthOffset, settingsHeightOffset, home.getPreferredSize().width,
                home.getPreferredSize().height);

        level1.setBounds(level1Pos.getX(), level1Pos.getY(), level1.getPreferredSize().width,
                level1.getPreferredSize().height);

        level2.setBounds(level2Pos.getX(), level2Pos.getY(), level2.getPreferredSize().width,
                level2.getPreferredSize().height);

        level3.setBounds(level3Pos.getX(), level3Pos.getY(), level3.getPreferredSize().width,
                level3.getPreferredSize().height);

        level4.setBounds(level4Pos.getX(), level4Pos.getY(), level4.getPreferredSize().width,
                level4.getPreferredSize().height);


    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Texture.LEADERBOARD_BACKGROUND.getTexture(), 0, 0, null);
    }

    /**
     * This function update the current logged user username in the text area
     */
    public void updateLoggedUser() {

        this.loggedUser.setBounds(loggedUser.getBounds().x, loggedUser.getBounds().y,
                this.loggedUser.getPreferredSize().width, this.loggedUser.getPreferredSize().height);
        this.loggedUser.setText(("Logged user: " + mainMenuController.getUsername()));
    }

    /**
     * This function updates the leaderboard showing the leaderboard of a given {@link Level}
     *
     * @param level the {@link Level} which you want to see the leaderboard of
     */
    public void updateLeaderboard(Level level) {
        List<User> leaderboard = mainMenuController.getUsers().stream().filter(user -> user.getLevelScore(level.getJsonId()) >= 0).sorted(Comparator.comparingInt(user -> user.getLevelScore(level.getJsonId()))).collect(Collectors.toList());
        Collections.reverse(leaderboard);
        test.removeAllElements();
        leaderboard.forEach(test::addElement);
        this.jList.removeAll();
        this.jList.setModel(this.test);
        this.jList.setCellRenderer(new TransparentListCellRenderer(level));
        LOGGER.info("Loading leaderboard from: " + level.getName());

    }
}
