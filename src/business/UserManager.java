package business;

import business.entities.User;
import persistance.UserDAO;
import persistance.sql.SQLUserDAO;

import java.util.ArrayList;

public class UserManager {

    private final UserDAO userDao;
    private User user;

    public UserManager() {
        userDao = new SQLUserDAO();
    }

    public boolean addUser(String username, String email, String password) {
        this.user = new User(username, email, password);
        return userDao.addUser(user);
    }

    public void delete(String code) {
        userDao.deleteUser(code);
    }

    public boolean isLogin(String login, String password) {
        User user = userDao.getUser(login);
        if (user.getPassword().equals(password)) {
            this.user = user;
            return true;
        }
        return false;
    }

    public void logout() {
        user = null;
    }

    public ArrayList<String> getUsersName(){
        return userDao.getUsersName();
    }


    public User getUser() {
        return user;
    }

    public int[] getWinrate(String name) {
        int[] stats = userDao.getStats(name);

        return stats;
    }
    public ArrayList<Integer> getAttacks(String name) {
        return userDao.getNumAttacks(name);
    }

}
