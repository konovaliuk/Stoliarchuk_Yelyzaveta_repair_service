package org.stoliarchuk.commands.implementations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.stoliarchuk.commands.CommandController;
import org.stoliarchuk.entities.User;
import org.stoliarchuk.services.UserService;

public class LoginCommand implements CommandController {
    @Override
    public boolean execute(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserService userService = new UserService();
        User newUser = userService.loginUser(username, password);
        if (newUser != null) {
            req.getSession().setAttribute("user", newUser);
            return true;
        }
        return false;
    }
}
