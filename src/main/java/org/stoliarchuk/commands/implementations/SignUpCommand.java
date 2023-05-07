package org.stoliarchuk.commands.implementations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.stoliarchuk.commands.CommandController;
import org.stoliarchuk.entities.User;
import org.stoliarchuk.services.UserService;

public class SignUpCommand implements CommandController {
    @Override
    public boolean execute(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User(email, username, password);
        User newUser = new UserService().registerUser(user);
        if (newUser != null){
            req.getSession().setAttribute("user", newUser);
            return true;
        }
        return false;
    }
}