package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT id, username, role, name, surname FROM users";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setRole(resultSet.getString("role"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public List<User> filterUsersByRole(String role) {
        List<User> users = new ArrayList<>();
        String query = "SELECT id, username, role, name, surname FROM users WHERE role = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, role);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setRole(resultSet.getString("role"));
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));

                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User getUser(int id) {
        User user = null;
        String query = "SELECT id, username, role, name, surname FROM users WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setRole(resultSet.getString("role"));
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        User user = null;
        String query = "SELECT id, username, role, name, surname FROM users WHERE username = ? AND password = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setRole(resultSet.getString("role"));
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public void deleteUser(int id) {
        String query = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) {
        String query = "INSERT INTO users (username, password, role, name, surname) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getSurname());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        String query = "UPDATE users SET username = ?, password = ?, role = ?, name = ?, surname = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getSurname());
            preparedStatement.setInt(6, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
