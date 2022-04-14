package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.controller.menu.ViewPanels;
import it.unibo.cicciopier.model.Level;
import it.unibo.cicciopier.model.User;
import it.unibo.cicciopier.model.settings.CustomFont;
import it.unibo.cicciopier.model.settings.Screen;
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
public class LeaderboardView extends JPanel implements MenuPanel {
    private static final Logger LOGGER = LoggerFactory.getLogger(LeaderboardView.class);
    private final JLabel loggedUser;
    private final MainMenuController mainMenuController;
    private final JList<User> jList;
    private final DefaultListModel<User> test = new DefaultListModel<>();
    private final CustomButton level1;
    private final CustomButton level2;
    private final CustomButton level3;
    private final CustomButton level4;
    private final JPanel panel;
    private final JScrollPane jScrollPane;
    private final CustomButton settings;
    private final CustomButton home;

    /**
     * This constructor creates the whole panel with all his components
     *
     * @param mainMenuController the instance of the {@link MainMenuController}
     */
    public LeaderboardView(MainMenuController mainMenuController) {
        LeaderboardView.LOGGER.info("Initializing the class... ");
        this.mainMenuController = mainMenuController;
        this.panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.jList = new JList<>(this.test);
        this.jScrollPane = new JScrollPane(jList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.loggedUser = new JLabel();
        this.settings = new ViewPanelButton(this.mainMenuController, Buttons.SETTINGS, ViewPanels.SETTINGS);
        this.home = new ViewPanelButton(this.mainMenuController, Buttons.HOME, ViewPanels.MAIN_MENU);
        this.level1 = new LeaderboardLevelSelectionButton(this.mainMenuController, Buttons.LEVEL1, Level.FIRST_LEVEL);
        this.level2 = new LeaderboardLevelSelectionButton(this.mainMenuController, Buttons.LEVEL2, Level.SECOND_LEVEL);
        this.level3 = new LeaderboardLevelSelectionButton(this.mainMenuController, Buttons.LEVEL3, Level.THIRD_LEVEL);
        this.level4 = new LeaderboardLevelSelectionButton(this.mainMenuController, Buttons.LEVEL_BOSS, Level.BOSS_LEVEL);

        this.load();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Texture.LEADERBOARD_BACKGROUND.getTexture(), 0, 0, Screen.getCurrentDimension().width, Screen.getCurrentDimension().height, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateLoggedUser() {
        this.loggedUser.setText(("Logged user: " + this.mainMenuController.getUsername()));
        this.loggedUser.setBounds(this.loggedUser.getBounds().x, this.loggedUser.getBounds().y,
                this.loggedUser.getPreferredSize().width, this.loggedUser.getPreferredSize().height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAnimations() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        LeaderboardView.LOGGER.info("Updating the class...");
        this.updateLoggedUser();
        this.setPreferredSize(Screen.getCurrentDimension());

        this.panel.setPreferredSize(new Dimension((int) (500 * Screen.getScale()), (int) (270 * Screen.getScale())));
        this.jList.setFixedCellWidth(this.panel.getPreferredSize().width);
        this.jScrollPane.setPreferredSize(new Dimension(this.panel.getPreferredSize().width,
                this.panel.getPreferredSize().height));
        this.jList.setFont(CustomFont.getInstance().getFontOrDefault());

        final int homeWidthOffset = this.getPreferredSize().width / 25;
        final int settingsWidthOffset = this.getPreferredSize().width - settings.getPreferredSize().width - homeWidthOffset;
        final int buttonsStart = this.getPreferredSize().width / 2 - level1.getPreferredSize().width * 2 - this.getPreferredSize().width / 50;
        final int levelButtonHeightOffset = this.getPreferredSize().height / 80;
        final int settingsHeightOffset = (int) (this.getPreferredSize().height / 38.4);

        final Pair<Integer> listPos = new Pair<>(this.getPreferredSize().width / 2 - panel.getPreferredSize().width / 2,
                this.getPreferredSize().height / 2 );

        final Pair<Integer> level1Pos = new Pair<>(buttonsStart,
                listPos.getY() + panel.getPreferredSize().height + levelButtonHeightOffset);

        final Pair<Integer> level2Pos = new Pair<>(buttonsStart + level2.getPreferredSize().width + settingsHeightOffset,
                listPos.getY() + panel.getPreferredSize().height + levelButtonHeightOffset);

        final Pair<Integer> level3Pos = new Pair<>(buttonsStart + (level3.getPreferredSize().width + settingsHeightOffset) * 2,
                listPos.getY() + panel.getPreferredSize().height + levelButtonHeightOffset);

        final Pair<Integer> level4Pos = new Pair<>(buttonsStart + (level4.getPreferredSize().width + settingsHeightOffset) * 3,
                listPos.getY() + panel.getPreferredSize().height + levelButtonHeightOffset);

        final Pair<Integer> loggedUserPos = new Pair<>(this.getPreferredSize().width / 25,
                (int)(this.getPreferredSize().height/1.01 - this.loggedUser.getPreferredSize().height));

        this.loggedUser.setBounds(loggedUserPos.getX(), loggedUserPos.getY(),
                this.loggedUser.getPreferredSize().width, this.loggedUser.getPreferredSize().height);

        this.settings.setBounds(settingsWidthOffset, settingsHeightOffset, settings.getPreferredSize().width,
                settings.getPreferredSize().height);

        this.panel.setBounds(listPos.getX(), listPos.getY(), this.panel.getPreferredSize().width,
                this.panel.getPreferredSize().height);

        this.home.setBounds(homeWidthOffset, settingsHeightOffset, this.home.getPreferredSize().width,
                this.home.getPreferredSize().height);

        this.level1.setBounds(level1Pos.getX(), level1Pos.getY(), this.level1.getPreferredSize().width,
                this.level1.getPreferredSize().height);

        this.level2.setBounds(level2Pos.getX(), level2Pos.getY(), this.level2.getPreferredSize().width,
                this.level2.getPreferredSize().height);

        this.level3.setBounds(level3Pos.getX(), level3Pos.getY(), this.level3.getPreferredSize().width,
                this.level3.getPreferredSize().height);

        this.level4.setBounds(level4Pos.getX(), level4Pos.getY(), this.level4.getPreferredSize().width,
                this.level4.getPreferredSize().height);

        this.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load() {
        LeaderboardView.LOGGER.info("Loading the class...");
        this.panel.setOpaque(false);

        this.jList.setLayoutOrientation(JList.VERTICAL);
        this.jList.setOpaque(false);
        this.jList.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        this.jList.setVisibleRowCount(1);
        this.jList.setSelectionModel(new NoSelectionModel());
        this.jList.setCellRenderer(new TransparentListCellRenderer(Level.FIRST_LEVEL));

        this.jScrollPane.setOpaque(false);
        this.jScrollPane.getViewport().setOpaque(false);
        this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.jScrollPane.getVerticalScrollBar().setBackground(new Color(210, 175, 128, 255));
        this.jScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.BLACK;
            }
        });

        this.loggedUser.setFont(CustomFont.getInstance().getFontOrDefault());
        this.loggedUser.setForeground(Color.WHITE);

        this.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        this.panel.add(this.jScrollPane);

        this.setLayout(null);
        this.add(this.loggedUser);
        this.add(this.level1);
        this.add(this.level2);
        this.add(this.level3);
        this.add(this.level4);
        this.add(this.panel);
        this.add(this.home);
        this.add(this.settings);
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
