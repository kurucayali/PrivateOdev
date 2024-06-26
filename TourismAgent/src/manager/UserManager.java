package manager;

import dao.UserDao;
import model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

// UserManager sınıfı, iş mantığını içeren servis katmanıdır
public class UserManager {
    private UserDao userDao;

    // UserManager constructor
    public UserManager(Connection connection) {
        this.userDao = new UserDao(connection);
    }

    // Kullanıcı ekleme metodu
    public void registerUser(User user) throws SQLException {
        userDao.addUser(user);
    }

    // Kullanıcı giriş metodu
    public User loginUser(String username, String password) throws SQLException {
        User user = userDao.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    // Kullanıcı güncelleme metodu
    public void updateUser(User user) throws SQLException {
        userDao.updateUser(user);
    }

    // Kullanıcı silme metodu
    public void deleteUser(int userId) throws SQLException {
        userDao.deleteUser(userId);
    }

    // Tüm kullanıcıları listeleme metodu
    public List<User> getAllUsers() throws SQLException {
        return userDao.getAllUsers();
    }
}
