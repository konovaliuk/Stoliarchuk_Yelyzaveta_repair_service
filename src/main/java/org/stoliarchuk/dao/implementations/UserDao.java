package org.stoliarchuk.dao.implementations;

import org.stoliarchuk.dao.interfaces.UserInterface;
import org.stoliarchuk.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements UserInterface {
    private final Connection connection;

    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM users WHERE user_id = ?";
    private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String SQL_SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String SQL_SELECT_USER_BY_USERNAME = "SELECT * FROM users WHERE username = ?";
    private static final String SQL_CREATE_USER = "INSERT INTO users (email, username, password, role_id) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE_USER_BY_ID = "UPDATE users SET email = ?, username = ?, password = ?, role_id = ? WHERE user_id = ?";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE user_id = ?";

    public UserDao(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public User getUser(ResultSet resultSet) throws SQLException {
        long user_id = resultSet.getLong("user_id");
        long role_id = resultSet.getLong("role_id");
        String email = resultSet.getString("email");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        return new User(user_id, role_id, email, username, password);
    }

    @Override
    public User findById(long user_id) {
        User user = new User();
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
            stmt.setLong(1, user_id);
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            user = getUser(resultSet);
            try {
                stmt.close();
                resultSet.close();
            } catch(RuntimeException e) {
                System.out.println(e);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        User user = new User();
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_USER_BY_EMAIL);
            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            user = getUser(resultSet);
            try {
                stmt.close();
                resultSet.close();
            } catch(RuntimeException e) {
                System.out.println(e);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findByUsername(String username) {
        User user = new User();
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_USER_BY_USERNAME);
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            user = getUser(resultSet);
            try {
                stmt.close();
                resultSet.close();
            } catch(RuntimeException e) {
                System.out.println(e);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAll(){
        List<User> userList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while(resultSet.next()) {
                User user = getUser(resultSet);
                userList.add(user);
            }
            try {
                statement.close();
                resultSet.close();
            } catch (RuntimeException e){
                System.out.println(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User createUser(User user) {
        if (user.getUserId() != 0) {
            throw new IllegalArgumentException("ID must not be specified!");
        }
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setLong(4, user.getRoleId());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setUserId(rs.getLong(1));
                }
            }
            try {
                stmt.close();
                return user;
            } catch (RuntimeException e) {
                System.out.println(e);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateUserById(long user_id, User user) {
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE_USER_BY_ID);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setLong(4, user.getRoleId());
            stmt.setLong(5, user_id);
            stmt.executeUpdate();
            try {
                stmt.close();
            } catch (RuntimeException e) {
                System.out.println(e);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(long user_id) {
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_DELETE_USER_BY_ID);
            stmt.setLong(1, user_id);
            stmt.executeUpdate();
            try {
                stmt.close();
            } catch (RuntimeException e){
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
