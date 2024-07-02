package controller;

import model.User;
import service.UserService;

import java.util.List;

public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public List<User> filterUsersByRole(String role) {
        if (role.equals("")) {
            return userService.getAllUsers();
        }
        return userService.filterUsersByRole(role);
    }

    public User getUser(int id) {
        return userService.getUser(id);
    }

    public void deleteUser(int id) {
        userService.deleteUser(id);
    }

    public void addUser(User user) {
        userService.addUser(user);
    }

    public void updateUser(User user) {
        userService.updateUser(user);
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        return userService.getUserByUsernameAndPassword(username, password);
    }
}
