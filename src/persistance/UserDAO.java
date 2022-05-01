package persistance;

import business.entities.User;

public interface UserDAO {


    boolean addUser(User user);

    boolean deleteUser(String code);

    String getPassword(String string);
}
