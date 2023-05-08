import dao.implementations.*;
import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        System.out.println("-----------");
        System.out.println("USER DAO & ROLE DAO");
        System.out.println("-----------");

        System.out.println("View all users");
        List<User> allUsers = new UserDao(em).findAll();
        for (User user: allUsers) {
            System.out.println(user);
        }

        System.out.println("\nFind root user by username");
        User rootUser = new UserDao(em).findByUsername("root");
        System.out.println(rootUser);

        System.out.println("\nFind role by name");
        Role masterRole = new RoleDao(em).findByName("master");
        System.out.println(masterRole);

        System.out.println("\nUpdate root user");
        rootUser.setRole(masterRole);
        new UserDao(em).update(rootUser);
        System.out.println(rootUser);

        System.out.println("\nView all users");
        List<User> allUsersNew = new UserDao(em).findAll();
        for (User user: allUsersNew) {
            System.out.println(user);
        }

        System.out.println("-----------");
        System.out.println("REQUEST DAO & STATUS DAO");
        System.out.println("-----------");

        System.out.println("View all requests");
        List<Request> allRequests = new RequestDao(em).findAll();
        for (Request request: allRequests) {
            System.out.println(request);
        }

        System.out.println("\nFind status by name");
        Status statusPending = new StatusDao(em).findByName("pending");
        System.out.println(statusPending);

        System.out.println("\nFind requests by status");
        List<Request> allPendingRequests = new RequestDao(em).findAllByStatus("pending");
        for (Request request: allPendingRequests) {
            System.out.println(request);
        }

        System.out.println("\nFind requests by user");
        List<Request> allUserRequests = new RequestDao(em).findAllByUserId(2);
        for (Request request: allUserRequests) {
            System.out.println(request);
        }

        System.out.println("-----------");
        System.out.println("FEEDBACK DAO");
        System.out.println("-----------");

        System.out.println("View all feedbacks");
        List<Feedback> allFeedbacks = new FeedbackDao(em).findAll();
        for (Feedback feedback: allFeedbacks) {
            System.out.println(feedback);
        }

        System.out.println("\nCreate feedback");
        Feedback newFeedback = new Feedback();
        Request currRequest = new RequestDao(em).findById(3);
        newFeedback.setFeedbackDescription("Great!!!");
        newFeedback.setRating(4);
        newFeedback.setRequest(currRequest);
        Feedback createdFeedback = new FeedbackDao(em).create(newFeedback);
        System.out.println(createdFeedback);

        System.out.println("\nView all feedbacks");
        List<Feedback> allFeedbacksNew = new FeedbackDao(em).findAll();
        for (Feedback feedback: allFeedbacksNew) {
            System.out.println(feedback);
        }

        System.out.println("\nDelete feedback");
        long deleteFeedbackId = createdFeedback.getFeedbackId();
        new FeedbackDao(em).delete(deleteFeedbackId);
        System.out.println(deleteFeedbackId);

        System.out.println("\nView all feedbacks");
        List<Feedback> allFeedbacksNew2 = new FeedbackDao(em).findAll();
        for (Feedback feedback: allFeedbacksNew2) {
            System.out.println(feedback);
        }

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
