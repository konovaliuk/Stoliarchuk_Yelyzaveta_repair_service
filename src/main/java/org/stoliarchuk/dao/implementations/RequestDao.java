package org.stoliarchuk.dao.implementations;

import org.stoliarchuk.dao.interfaces.RequestInterface;
import org.stoliarchuk.entities.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDao implements RequestInterface {
    private final Connection connection;

    private static final String SQL_SELECT_REQUEST_BY_ID = "SELECT * FROM requests WHERE request_id = ?";
    private static final String SQL_SELECT_ALL_REQUESTS = "SELECT * FROM requests";
    private static final String SQL_SELECT_ALL_REQUESTS_BY_USER_ID = "SELECT * FROM requests WHERE user_id = ?";
    private static final String SQL_SELECT_ALL_REQUESTS_BY_STATUS = "SELECT * FROM requests JOIN statuses USING (status_id) WHERE statuses.name = ?";
    private static final String SQL_CREATE_REQUEST = "INSERT INTO requests (user_id, request_description, product_name, product_model) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE_REQUEST_BY_ID = "UPDATE requests SET status_id = ?, request_description = ?, product_name = ?, product_model = ?, repair_cost = ?, declination_reason = ? WHERE request_id = ?";
    private static final String SQL_DELETE_REQUEST_BY_ID = "DELETE FROM requests WHERE request_id = ?";


    public RequestDao(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public Request getRequest(ResultSet resultSet) throws SQLException {
        long request_id = resultSet.getLong("request_id");
        long user_id = resultSet.getLong("user_id");
        long status_id = resultSet.getLong("status_id");
        String request_description = resultSet.getString("request_description");
        String product_name = resultSet.getString("product_name");
        String product_model = resultSet.getString("product_model");
        Date created_at = resultSet.getDate("created_at");
        int repair_cost = resultSet.getInt("repair_cost");
        String declination_reason = resultSet.getString("declination_reason");
        return new Request(request_id, user_id, status_id, request_description, product_name, product_model, created_at, repair_cost, declination_reason);
    }

    @Override
    public Request findById(long request_id) {
        Request request = new Request();
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_REQUEST_BY_ID);
            stmt.setLong(1, request_id);
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            request = getRequest(resultSet);
            try {
                stmt.close();
                resultSet.close();
            } catch(RuntimeException e) {
                System.out.println(e);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return request;
    }

    @Override
    public List<Request> findAll(){
        List<Request> requestList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_REQUESTS);
            while(resultSet.next()) {
                Request request = getRequest(resultSet);
                requestList.add(request);
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
        return requestList;
    }

    @Override
    public List<Request> findAllByUserId(long user_id){
        List<Request> requestList = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL_REQUESTS_BY_USER_ID);
            stmt.setLong(1, user_id);
            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()) {
                Request request = getRequest(resultSet);
                requestList.add(request);
            }
            try {
                stmt.close();
                resultSet.close();
            } catch (RuntimeException e){
                System.out.println(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestList;
    }

    @Override
    public List<Request> findAllByStatus(String status_name){
        List<Request> requestList = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL_REQUESTS_BY_STATUS);
            stmt.setString(1, status_name);
            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()) {
                Request request = getRequest(resultSet);
                requestList.add(request);
            }
            try {
                stmt.close();
                resultSet.close();
            } catch (RuntimeException e){
                System.out.println(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestList;
    }

    @Override
    public void createRequest(Request request) {
        if (request.getRequest_id() != 0) {
            throw new IllegalArgumentException("ID must not be specified!");
        }
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_CREATE_REQUEST);
            stmt.setLong(1, request.getUser_id());
            stmt.setString(2, request.getRequest_description());
            stmt.setString(3, request.getProduct_name());
            stmt.setString(4, request.getProduct_model());
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
    public void updateRequestById(long request_id, Request request) {
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE_REQUEST_BY_ID);
            stmt.setLong(1, request.getStatus_id());
            stmt.setString(2, request.getRequest_description());
            stmt.setString(3, request.getProduct_name());
            stmt.setString(4, request.getProduct_model());
            stmt.setInt(5, request.getRepair_cost());
            stmt.setString(6, request.getDeclination_reason());
            stmt.setLong(7, request_id);
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
    public void deleteById(long request_id) {
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_DELETE_REQUEST_BY_ID);
            stmt.setLong(1, request_id);
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
