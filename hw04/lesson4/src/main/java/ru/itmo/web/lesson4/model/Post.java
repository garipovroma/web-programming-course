package ru.itmo.web.lesson4.model;

public class Post {
    private final long id, userId;
    private final String title, text;

    public Post(long id, long userId, String title, String text) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }
}
