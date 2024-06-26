package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// UserDao sınıfı veritabanı işlemlerini gerçekleştirir
public class UserDao {
    private Connection connection;

    // UserDao constructor
    public UserDao(Connection connection) {
        this.connection = connection;
    }

    // Yeni kullanıcı ekleme metodu
    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO users (username, password, role, name, surname) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.setString(4, user.getName());
            stmt.setString(5, user.getSurname());
            stmt.executeUpdate();
        }
    }

    // Kullanıcı adından kullanıcıyı getirme metodu
    public User getUserByUsername(String username) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                return user;
            }
        }
        return null;
    }

    // Kullanıcı güncelleme metodu
    public void updateUser(User user) throws SQLException {
        String query = "UPDATE users SET username = ?, password = ?, role = ?, name = ?, surname = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.setString(4, user.getName());
            stmt.setString(5, user.getSurname());
            stmt.setInt(6, user.getId());
            stmt.executeUpdate();
        }
    }

    // Kullanıcı silme metodu
    public void deleteUser(int userId) throws SQLException {
        String query = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }

    // Tüm kullanıcıları listeleme metodu
    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                userList.add(user);
            }
        }
        return userList;
    }
}
