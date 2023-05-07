package org.stoliarchuk.dao.implementations;

import org.stoliarchuk.dao.interfaces.StatusInterface;
import org.stoliarchuk.entities.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatusDao implements StatusInterface {
    private final Connection connection;

    private static final String SQL_SELECT_STATUS_BY_ID = "SELECT * FROM statuses WHERE status_id = ?";
    private static final String SQL_SELECT_STATUS_BY_NAME = "SELECT * FROM statuses WHERE name = ?";
    private static final String SQL_SELECT_ALL_STATUSES = "SELECT * FROM statuses";
    private static final String SQL_CREATE_STATUS = "INSERT INTO statuses (name) VALUES (?)";
    private static final String SQL_DELETE_STATUS_BY_ID = "DELETE FROM statuses WHERE status_id = ?";

    public StatusDao(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public Status getStatus(ResultSet resultSet) throws SQLException {
        long status_id = resultSet.getLong("status_id");
        String name = resultSet.getString("name");
        return new Status(status_id, name);
    }

    @Override
    public Status findById(long status_id) {
        Status status = new Status();
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_STATUS_BY_ID);
            stmt.setLong(1, status_id);
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            status = getStatus(resultSet);
            try {
                stmt.close();
                resultSet.close();
            } catch(RuntimeException e) {
                System.out.println(e);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public Status findByName(String name) {
        Status status = new Status();
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_STATUS_BY_NAME);
            stmt.setString(1, name);
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            status = getStatus(resultSet);
            try {
                stmt.close();
                resultSet.close();
            } catch(RuntimeException e) {
                System.out.println(e);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<Status> findAll(){
        List<Status> statusList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_STATUSES);
            while(resultSet.next()) {
                Status status = getStatus(resultSet);
                statusList.add(status);
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
        return statusList;
    }

    @Override
    public void createStatus(Status status) {
        if (status.getStatusId() != 0) {
            throw new IllegalArgumentException("ID must not be specified!");
        }
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_CREATE_STATUS);
            stmt.setString(1, status.getName());
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
    public void deleteById(long status_id) {
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_DELETE_STATUS_BY_ID);
            stmt.setLong(1, status_id);
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
