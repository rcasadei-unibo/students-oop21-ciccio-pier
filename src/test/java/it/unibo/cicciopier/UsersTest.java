package it.unibo.cicciopier;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.model.settings.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsersTest {

    @Test
    @Order(1)
    public void testCreateUsers() {
        MainMenuController mainMenuController = new MainMenuController();
        User user = mainMenuController.createUser("Test");
        assertEquals("test", user.getUsername());

    }

    @Test
    @Order(2)
    public void testLoadUsers() {
        MainMenuController mainMenuController = new MainMenuController();
        mainMenuController.loadUsers();
        assertTrue(mainMenuController.getUsers().stream().anyMatch(u -> u.getUsername().equals("test")));
        assertFalse(mainMenuController.getUsers().stream().anyMatch(u -> u.getUsername().equals("1111111111111111")));
    }
}
