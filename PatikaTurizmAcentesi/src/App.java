import javax.swing.SwingUtilities;
import controller.UserController;
import db.DatabaseConnection;
import service.Helper;
import service.UserService;
import dao.UserDAO;
import view.LoginView;

public class App {
    public static void main(String[] args) {
        Helper.setTheme();
        // Veritabanı bağlantısını ayarlama
        UserDAO userDAO = new UserDAO(DatabaseConnection.getConnection());
        UserService userService = new UserService(userDAO);
        UserController userController = new UserController(userService);

        // LoginView'i başlatma
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginView(userController).setVisible(true);
            }
        });
    }
}
