package org.stoliarchuk.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.stoliarchuk.connection.DBCPDataSource;
import org.stoliarchuk.dao.implementations.RoleDao;
import org.stoliarchuk.dao.implementations.UserDao;
import org.stoliarchuk.entities.Role;
import org.stoliarchuk.entities.User;

import java.sql.Connection;
import java.util.List;
import java.util.Objects;

public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    public User getUser(long id){
        try (Connection con = DBCPDataSource.getConnection()){
            UserDao userCon = new UserDao(con);
            try {
                return userCon.findById(id);
            } catch (Exception ex) {
                logger.error("Error: " + ex.getMessage());
            }
        } catch (Exception ex){
            logger.error("Error: " + ex.getMessage());
        }
        return null;
    }

    public List<User> getAllUsers(){
        try (Connection con = DBCPDataSource.getConnection()){
            UserDao userCon = new UserDao(con);
            try {
                return userCon.findAll();
            } catch (Exception ex) {
                logger.error("Error: " + ex.getMessage());
            }
        } catch (Exception ex){
            logger.error("Error: " + ex.getMessage());
        }
        return null;
    }

    public User loginUser(String username, String password) {
        try (Connection con = DBCPDataSource.getConnection()){
            UserDao userCon = new UserDao(con);
            try {
                User user = userCon.findByUsername(username);
                if (Objects.equals(password, user.getPassword())){
                    return user;
                }
            } catch (Exception ex) {
                logger.error("Error: " + ex.getMessage());
            }
        } catch (Exception ex){
            logger.error("Error: " + ex.getMessage());
        }
        return null;
    }
    public User registerUser(User user) {
        try (Connection con = DBCPDataSource.getConnection()){
            UserDao userCon = new UserDao(con);
            RoleDao roleCon = new RoleDao(con);
            try {
                con.setAutoCommit(false);
                Role role = roleCon.findByName("user");
                user.setRoleId(role.getRoleId());
                if (userCon.createUser(user) != null) {
                    con.commit();
                    return user;
                }
            } catch (Exception ex) {
                try {
                    con.rollback();
                } catch (Exception e){
                    logger.error("Error: " + e.getMessage());
                }
            } finally {
                con.setAutoCommit(true);
            }
        } catch (Exception ex){
            logger.error("Error: " + ex.getMessage());
        }
        return null;
    }
    public void updateUser(long userId, User user){
        try (Connection con = DBCPDataSource.getConnection()){
            UserDao userCon = new UserDao(con);
            try {
                con.setAutoCommit(false);
                userCon.updateUserById(userId, user);
                con.commit();
            } catch (Exception ex) {
                try {
                    con.rollback();
                } catch (Exception e){
                    logger.error("Error: " + e.getMessage());
                }
            } finally {
                con.setAutoCommit(true);
            }
        } catch (Exception ex){
            logger.error("Error: " + ex.getMessage());
        }
    }

    public boolean deleteUser(long userId, String password){
        try (Connection con = DBCPDataSource.getConnection()){
            UserDao userCon = new UserDao(con);
            try {
                con.setAutoCommit(false);
                User user = userCon.findById(userId);
                if (Objects.equals(password, user.getPassword())) {
                    userCon.deleteById(userId);
                    con.commit();
                    return true;
                }
            } catch (Exception ex){
                try {
                    con.rollback();
                } catch (Exception e){
                    logger.error("Error: " + e.getMessage());
                }
            } finally {
                con.setAutoCommit(true);
            }
        } catch (Exception ex) {
            logger.error("Error: " + ex.getMessage());
        }
        return false;
    }
}
