package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.User;
import util.JNDIFactory;

public class UserDAOImpl implements IUserDAO {

    private static final JNDIFactory JNDI_FACTORY = JNDIFactory.getInstance();

    private static final String UPDATE_USER_SQL = "UPDATE users SET username = ?, password = ?, first_name = ?, last_name = ?, email = ? WHERE id = ?";
    private static final String ADD_USER_SQL = "INSERT INTO users (username, password, first_name, last_name, email) VALUES (?, ?, ?, ?, ?)";
    private static final String GET_USER_BY_USERNAME_SQL = "SELECT * FROM users WHERE username = ?";
    private static final String GET_USER_BY_EMAIL_SQL = "SELECT * FROM users WHERE email = ?";
    private static final String GET_USER_BY_ID_SQL = "SELECT * FROM users WHERE id = ?";
    private static final String REMOVE_USER_SQL = "DELETE FROM users WHERE id = ?";
    private static final String GET_ALL_USERS_SQL = "SELECT * FROM users";

    private String databaseSource;

    public UserDAOImpl(String databaseSource) {
        this.databaseSource = databaseSource;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = JNDI_FACTORY.getConnection(this.databaseSource);
                PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS_SQL)) {

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                users.add(new User(
                        result.getInt("id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("email")));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return users;
    }

    @Override
    public User getUserById(int id) {
        try (Connection connection = JNDI_FACTORY.getConnection(this.databaseSource);
                PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID_SQL)) {
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return new User(
                        result.getInt("id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("email"));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        if (username == null) {
            return null;
        }

        try (Connection connection = JNDI_FACTORY.getConnection(this.databaseSource);
                PreparedStatement statement = connection.prepareStatement(GET_USER_BY_USERNAME_SQL)) {
            statement.setString(1, username);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return new User(
                        result.getInt("id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("email"));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        if (email == null) {
            return null;
        }

        try (Connection connection = JNDI_FACTORY.getConnection(this.databaseSource);
                PreparedStatement statement = connection.prepareStatement(GET_USER_BY_EMAIL_SQL)) {
            statement.setString(1, email);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return new User(
                        result.getInt("id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("email"));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean createUser(String username, String password, String firstName, String lastName, String email) {
        if (username == null || password == null || firstName == null || lastName == null || email == null) {
            return false;
        }

        try (Connection connection = JNDI_FACTORY.getConnection(this.databaseSource);
                PreparedStatement statement = connection.prepareStatement(ADD_USER_SQL)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            statement.setString(5, email);

            return statement.executeUpdate() > 0;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean removeUser(User user) {
        if (user == null) {
            return false;
        }

        try (Connection connection = JNDI_FACTORY.getConnection(this.databaseSource);
                PreparedStatement statement = connection.prepareStatement(REMOVE_USER_SQL)) {
            statement.setInt(1, user.getId());

            return statement.executeUpdate() > 0;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateUser(User user) {
        if (user == null) {
            return false;
        }

        try (Connection connection = JNDI_FACTORY.getConnection(this.databaseSource);
                PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getEmail());
            statement.setInt(6, user.getId());

            return statement.executeUpdate() > 0;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

}
