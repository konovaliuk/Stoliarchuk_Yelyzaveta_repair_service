package org.stoliarchuk.dao.implementations;

import org.stoliarchuk.dao.interfaces.RoleInterface;
import org.stoliarchuk.entities.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDao implements RoleInterface {
    private final Connection connection;

    private static final String SQL_SELECT_ROLE_BY_ID = "SELECT * FROM roles WHERE role_id = ?";
    private static final String SQL_SELECT_ROLE_BY_NAME = "SELECT * FROM roles WHERE name = ?";
    private static final String SQL_SELECT_ALL_ROLES = "SELECT * FROM roles";
    private static final String SQL_CREATE_ROLE = "INSERT INTO roles (name) VALUES (?)";
    private static final String SQL_DELETE_ROLE_BY_ID = "DELETE FROM roles WHERE role_id = ?";

    public RoleDao(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public Role getRole(ResultSet resultSet) throws SQLException {
        long role_id = resultSet.getLong("role_id");
        String name = resultSet.getString("name");
        return new Role(role_id, name);
    }

    @Override
    public Role findById(long role_id) {
        Role role = new Role();
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ROLE_BY_ID);
            stmt.setLong(1, role_id);
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            role = getRole(resultSet);
            try {
                stmt.close();
                resultSet.close();
            } catch(RuntimeException e) {
                System.out.println(e);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public Role findByName(String name) {
        Role role = new Role();
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ROLE_BY_NAME);
            stmt.setString(1, name);
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            role = getRole(resultSet);
            try {
                stmt.close();
                resultSet.close();
            } catch(RuntimeException e) {
                System.out.println(e);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public List<Role> findAll(){
        List<Role> roleList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_ROLES);
            while(resultSet.next()) {
                Role role = getRole(resultSet);
                roleList.add(role);
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
        return roleList;
    }

    @Override
    public void createRole(Role role) {
        if (role.getRole_id() != 0) {
            throw new IllegalArgumentException("ID must not be specified!");
        }
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_CREATE_ROLE);
            stmt.setString(1, role.getName());
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
    public void deleteById(long role_id) {
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_DELETE_ROLE_BY_ID);
            stmt.setLong(1, role_id);
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
