package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.TalkService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class TalksPage extends Page {
    @Override
    protected void action(HttpServletRequest request, Map<String, Object> view) {
        if (getUser() == null) {
            setMessage("Talks available only for logged in users");
            throw new RedirectException("/index");
        }
        Talk[] userMessages = talkService.getMessagesById(getUser().getId());
        talkService.setUserLogins(userMessages, userService);
        view.put("messages", userMessages);
        view.put("users", userService.findAll());
    }
    private void sendMessage(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        view.put("users", userService.findAll());
        Talk talk = new Talk();
        String message = request.getParameter("message");
        talkService.validateMessage(message);

        talk.setText(request.getParameter("message"));
        talk.setSourceUserId(getUser().getId());
        talk.setTargetUserId(Long.parseLong(request.getParameter("recipientUserId")));

        userService.checkUserExistance(talk.getSourceUserId());
        userService.checkUserExistance(talk.getTargetUserId());

        talkService.save(talk);

        Talk[] userMessages = talkService.getMessagesById(getUser().getId());
        talkService.setUserLogins(userMessages, userService);
        view.put("messages", userMessages);
    }

}
