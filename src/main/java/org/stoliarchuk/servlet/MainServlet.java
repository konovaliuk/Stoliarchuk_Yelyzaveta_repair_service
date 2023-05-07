package org.stoliarchuk.servlet;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import org.stoliarchuk.commands.CommandController;
import org.stoliarchuk.commands.implementations.LoginCommand;
import org.stoliarchuk.commands.implementations.RequestsCommand;
import org.stoliarchuk.commands.implementations.SignUpCommand;
import org.stoliarchuk.entities.User;


//@WebServlet(urlPatterns = {"", "/demo_war_exploded/html/signup", "/demo_war_exploded/html/logout", "/demo_war_exploded/html/requests"})
//@WebServlet(name = "MainServlet", urlPatterns = "/servlet")
@WebServlet(name = "MainServlet", urlPatterns = {"/html/login", "/html/signup", "/html/logout", "/html/requests"})
public class MainServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(MainServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        logger.info("Command: " + command);
        if (command.equals("requests")) {
            CommandController requestsCommand = new RequestsCommand();
            requestsCommand.execute(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        HttpSession session = req.getSession();
        logger.info("Command: " + command);
        switch (command) {
            case "login" -> {
                CommandController loginCommand = new LoginCommand();
                if (loginCommand.execute(req, resp)) {
                    resp.sendRedirect("/demo_war_exploded/");
                } else {
                    req.setAttribute("error", "Invalid Username or Password");
                    req.getRequestDispatcher("/html/login.jsp").forward(req, resp);
                }
            }
            case "signup" -> {
                CommandController signUpCommand = new SignUpCommand();
                if (signUpCommand.execute(req, resp)) {
                    resp.sendRedirect("/demo_war_exploded/");
                } else {
                    req.setAttribute("error", "This Email or Username is taken");
                    req.getRequestDispatcher("/html/signup.jsp").forward(req, resp);
                }
            }
            case "logout" -> {
                session.setAttribute("user", null);
                resp.sendRedirect("/demo_war_exploded/html/login.jsp");
            }
            default -> {
            }
        }
    }
}