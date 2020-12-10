package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/** @noinspection unused*/
public class UsersPage {
    private final UserService userService = new UserService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            User updatedUser = userService.find(user.getId());
            if (updatedUser.isAdmin() != user.isAdmin()) {
                request.getSession().removeAttribute("user");
                request.getSession().setAttribute("user", updatedUser);
            }
        }
    }

    private void setAdminField(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        checkLoggedInUser(request);
        User user = (User) request.getSession().getAttribute("user");
        userService.validateAdmin(user);
        long userId = Long.parseLong(request.getParameter("userId"));
        boolean valueToSet = !((String) request.getParameter("userAdminValue")).equals("true");
        userService.setAdminField(userId, valueToSet);
    }

    private void findAll(HttpServletRequest request, Map<String, Object> view) {
        view.put("users", userService.findAll());
    }

    private void findUser(HttpServletRequest request, Map<String, Object> view) {
        view.put("user", userService.find(Long.parseLong(request.getParameter("userId"))));
    }

    private void checkLoggedInUser(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            request.getSession().setAttribute("message", "This action is available only for logged-in users");
            request.getSession().setAttribute("messageType", "error");
            throw new RedirectException("/index");
        }
    }
}
