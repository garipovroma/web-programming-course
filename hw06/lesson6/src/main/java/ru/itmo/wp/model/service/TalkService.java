package ru.itmo.wp.model.service;


import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.TalkRepository;
import ru.itmo.wp.model.repository.impl.TalkRepositoryImpl;

import java.util.HashMap;
import java.util.Map;

public class TalkService {
    private final TalkRepository talkRepository = new TalkRepositoryImpl();
    public Talk[] getMessagesById(long id) {
        return talkRepository.findByUserId(id);
    }
    public void save(Talk talk) {
        talkRepository.save(talk);
    }

    public void validateMessage(String message) throws ValidationException {
        if (message.isEmpty()) {
            throw new ValidationException("Message can't be empty");
        }
        if (message.length() >= 255) {
            throw new ValidationException("Message can't contains more than 255 symbols");
        }
    }
    public void setUserLogins(Talk[] userMessages, UserService userService) {
        for (Talk talk : userMessages) {
            User sourceUser = userService.find(talk.getSourceUserId());
            User targetUser = userService.find(talk.getTargetUserId());
            talk.setSourceUserLogin(sourceUser.getLogin());
            talk.setTargetUserLogin(targetUser.getLogin());
        }
    }
}
