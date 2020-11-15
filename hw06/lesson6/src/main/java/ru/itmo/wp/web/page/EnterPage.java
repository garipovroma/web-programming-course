package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class EnterPage extends Page {
    private void enter(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        String loginOrEmail = request.getParameter("loginOrEmail");
        String password = request.getParameter("password");

        User user = userService.validateEnter(loginOrEmail, password);
        setUser(user);
        setMessage("Hello, " + user.getLogin());

        Event enterEvent = new Event();
        enterEvent.setType(Event.EventType.ENTER);
        enterEvent.setUserId(getUser().getId());
        eventService.recordEvent(enterEvent);

        throw new RedirectException("/index");
    }
}
