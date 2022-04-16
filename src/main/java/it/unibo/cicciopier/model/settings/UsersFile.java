package it.unibo.cicciopier.model.settings;

import java.util.List;

/**
 * This class represents the json file model to save users
 */
public class UsersFile {
    private String lastUser;
    private List<User> users;

    /**
     * This function returns the last logged user
     *
     * @return the last logged user
     */
    public String getLastUser() {
        return lastUser;
    }

    /**
     * This function sets the last logged users
     *
     * @param lastUser last logged user username
     */
    public void setLastUser(final String lastUser) {
        this.lastUser = lastUser;
    }

    /**
     * This function returns the list of users
     *
     * @return the list of users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * this function sets the list of users
     *
     * @param users the list that contains all the users loaded from the json file
     */
    public void setUsers(final List<User> users) {
        this.users = users;
    }
}
