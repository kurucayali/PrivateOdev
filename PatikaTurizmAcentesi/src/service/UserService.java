package service;

import dao.UserDAO;
import model.User;

import java.util.List;

public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public List<User> filterUsersByRole(String role) {
        return userDAO.filterUsersByRole(role);
    }

    public User getUser(int id) {
        return userDAO.getUser(id);
    }

    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }

    public void addUser(User user) {
        userDAO.addUser(user);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        return userDAO.getUserByUsernameAndPassword(username, password);
    }
}
