package org.stoliarchuk.dao.implementations;

import org.stoliarchuk.dao.interfaces.FeedbackInterface;
import org.stoliarchuk.entities.Feedback;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDao implements FeedbackInterface {
    private final Connection connection;

    private static final String SQL_SELECT_FEEDBACK_BY_ID = "SELECT * FROM feedbacks WHERE feedback_id = ?";
    private static final String SQL_SELECT_ALL_FEEDBACKS = "SELECT * FROM feedbacks";
    private static final String SQL_SELECT_ALL_FEEDBACKS_BY_REQUEST_ID = "SELECT * FROM feedbacks WHERE request_id = ?";
    private static final String SQL_CREATE_FEEDBACK = "INSERT INTO feedbacks (rating, feedback_description, request_id) VALUES (?, ?, ?)";
    private static final String SQL_DELETE_FEEDBACK_BY_ID = "DELETE FROM feedbacks WHERE feedback_id = ?";

    public FeedbackDao(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public Feedback getFeedback(ResultSet resultSet) throws SQLException {
        long feedback_id = resultSet.getLong("feedback_id");
        long request_id = resultSet.getLong("request_id");
        Date created_at = resultSet.getDate("created_at");
        int rating = resultSet.getInt("rating");
        String feedback_description = resultSet.getString("feedback_description");
        return new Feedback(feedback_id, request_id, created_at, rating, feedback_description);
    }

    @Override
    public Feedback findById(long feedback_id) {
        Feedback feedback = new Feedback();
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_FEEDBACK_BY_ID);
            stmt.setLong(1, feedback_id);
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            feedback = getFeedback(resultSet);
            try {
                stmt.close();
                resultSet.close();
            } catch(RuntimeException e) {
                System.out.println(e);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return feedback;
    }

    @Override
    public List<Feedback> findAll(){
        List<Feedback> feedbackList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(SQL_SELECT_ALL_FEEDBACKS);
            while(resultSet.next()) {
                Feedback feedback = getFeedback(resultSet);
                feedbackList.add(feedback);
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
        return feedbackList;
    }

    @Override
    public List<Feedback> findAllByRequestId(long request_id){
        List<Feedback> feedbackList = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL_FEEDBACKS_BY_REQUEST_ID);
            stmt.setLong(1, request_id);
            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()) {
                Feedback feedback = getFeedback(resultSet);
                feedbackList.add(feedback);
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
        return feedbackList;
    }

    @Override
    public void createFeedback(Feedback feedback) {
        if (feedback.getFeedback_id() != 0) {
            throw new IllegalArgumentException("ID must not be specified!");
        }
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_CREATE_FEEDBACK);
            stmt.setInt(1, feedback.getRating());
            stmt.setString(2, feedback.getFeedback_description());
            stmt.setLong(3, feedback.getRequest_id());
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
    public void deleteById(long feedback_id) {
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_DELETE_FEEDBACK_BY_ID);
            stmt.setLong(1, feedback_id);
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