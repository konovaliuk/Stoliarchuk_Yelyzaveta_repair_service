package org.stoliarchuk;

import org.stoliarchuk.connection.DBCPDataSource;
import org.stoliarchuk.dao.implementations.*;
import org.stoliarchuk.entities.*;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("--------------------------------------------------");
            System.out.println("User DAO");
            System.out.println("--------------------------------------------------");
            UserDao userCon = new UserDao(DBCPDataSource.getConnection());

            // Create user
            System.out.println("Create user");
            userCon.createUser(new User(1, "test@gmail.com", "test", "test"));

            // View all users
            System.out.println("\nView all users");
            List<User> userList1 = userCon.findAll();
            for (User u : userList1) {
                u.print();
            }

            // Find by username
            System.out.println("\nFind user by username");
            User user1 = userCon.findByUsername("test");
            long user1Id = user1.getUser_id();
            user1.print();

            // Update user by id
            System.out.println("\nUpdate user by id");
            user1.setRole_id(2);
            userCon.updateUserById(user1Id, user1);

            // Find by email
            System.out.println("\nFind user by email");
            User user2 = userCon.findByEmail("test@gmail.com");
            user2.print();

            // Delete user by id
            System.out.println("\nDelete user by id");
            userCon.deleteById(user1Id);

            // View all users
            System.out.println("\nView all users");
            List<User> userList2 = userCon.findAll();
            for (User u : userList2) {
                u.print();
            }

            System.out.println("--------------------------------------------------");
            System.out.println("Role DAO");
            System.out.println("--------------------------------------------------");
            RoleDao roleCon = new RoleDao(DBCPDataSource.getConnection());

            // Create role
            System.out.println("Create role");
            roleCon.createRole(new Role("test"));

            // View all roles
            System.out.println("\nView all roles");
            List<Role> rolesList1 = roleCon.findAll();
            for (Role r : rolesList1) {
                r.print();
            }

            // Find by name
            System.out.println("\nFind role by name");
            Role role = roleCon.findByName("test");
            long roleId = role.getRole_id();
            role.print();

            // Delete role by id
            System.out.println("\nDelete role");
            roleCon.deleteById(roleId);

            // View all roles
            System.out.println("\nView all roles");
            List<Role> rolesList2 = roleCon.findAll();
            for (Role r : rolesList2) {
                r.print();
            }

            System.out.println("--------------------------------------------------");
            System.out.println("Status DAO");
            System.out.println("--------------------------------------------------");
            StatusDao statusCon = new StatusDao(DBCPDataSource.getConnection());

            // Create status
            System.out.println("Create status");
            statusCon.createStatus(new Status("test"));

            // View all statuses
            System.out.println("\nView all statuses");
            List<Status> statusesList1 = statusCon.findAll();
            for (Status s : statusesList1) {
                s.print();
            }

            // Find by name
            System.out.println("\nFind status by name");
            Status status = statusCon.findByName("test");
            long statusId = status.getStatus_id();
            status.print();

            // Delete status by id
            System.out.println("\nDelete status");
            statusCon.deleteById(statusId);

            // View all statuses
            System.out.println("\nView all statuses");
            List<Status> statusesList2 = statusCon.findAll();
            for (Status s : statusesList2) {
                s.print();
            }

            System.out.println("--------------------------------------------------");
            System.out.println("Request DAO");
            System.out.println("--------------------------------------------------");
            RequestDao requestCon = new RequestDao(DBCPDataSource.getConnection());

            // Create request
            System.out.println("Create request");
            requestCon.createRequest(new Request(1, "It started to burn", "Keyboard", "AX12"));

            // View all requests by user id
            System.out.println("\nView all requests by user id");
            List<Request> requestsList1 = requestCon.findAllByUserId(1);
            for (Request r : requestsList1) {
                r.print();
            }

            // Update request by id
            System.out.println("\nUpdate request by id");
            Request request1 = requestsList1.get(0);
            request1.setStatus_id(3);
            request1.setDeclination_reason("We don't have needed instruments.");
            requestCon.updateRequestById(request1.getRequest_id(), request1);

            // View all requests by status
            System.out.println("\nView all requests by status");
            List<Request> requestsList2 = requestCon.findAllByStatus("rejected");
            for (Request r : requestsList2) {
                r.print();
            }

            // Delete request by id
            System.out.println("\nDelete request by id");
            requestCon.deleteById(request1.getRequest_id());

            // View all requests
            System.out.println("\nView all requests");
            List<Request> requestsList3 = requestCon.findAll();
            for (Request r : requestsList3) {
                r.print();
            }

            System.out.println("--------------------------------------------------");
            System.out.println("Feedback DAO");
            System.out.println("--------------------------------------------------");
            FeedbackDao feedbackCon = new FeedbackDao(DBCPDataSource.getConnection());

            // Create feedback
            System.out.println("Create feedback");
            feedbackCon.createFeedback(new Feedback(3, 5, "Wonderful"));

            // View all feedbacks by request id
            System.out.println("\nView all feedbacks by request id");
            List<Feedback> feedbacksList1 = feedbackCon.findAllByRequestId(3);
            for (Feedback f : feedbacksList1) {
                f.print();
            }

            // Delete feedback by id
            System.out.println("\nDelete feedback by id");
            feedbackCon.deleteById(feedbacksList1.get(1).getFeedback_id());

            // View all feedbacks
            System.out.println("\nView all feedbacks by request id");
            List<Feedback> feedbacksList2 = feedbackCon.findAll();
            for (Feedback f : feedbacksList2) {
                f.print();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}