package dao;

import java.util.List;
import java.util.Locale;

import model.User;

public interface IUserDAO {

    public List<User> getAllUsers();

    public User getUserById(int id);

    public User getUserByUsername(String username);

    public User getUserByEmail(String email);

    public boolean createUser(String username, String password, String firstName, String lastName, String email,
            Locale locale);

    public boolean removeUser(User user);

    public boolean updateUser(User user);

}
