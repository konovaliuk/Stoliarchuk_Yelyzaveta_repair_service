package org.stoliarchuk.commands.implementations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.stoliarchuk.commands.CommandController;
import org.stoliarchuk.entities.Request;
import org.stoliarchuk.entities.User;
import org.stoliarchuk.services.RequestService;

import java.io.IOException;
import java.util.List;

public class RequestsCommand implements CommandController {
    @Override
    public boolean execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            User user = (User) req.getSession().getAttribute("user");
            RequestService requestService = new RequestService();
            List<Request> requests = requestService.getAllRequestsByUser(user.getUserId());
            req.getSession().setAttribute("requests", requests);
            req.getRequestDispatcher("/html/requests.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
