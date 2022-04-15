package it.unibo.cicciopier.model.settings;

import java.util.List;

public class UsersFile {
    private String lastUser;
    private List<User> users;

    public String getLastUser() {
        return lastUser;
    }

    public void setLastUser(final String lastUser) {
        this.lastUser = lastUser;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(final List<User> users) {
        this.users = users;
    }
}
