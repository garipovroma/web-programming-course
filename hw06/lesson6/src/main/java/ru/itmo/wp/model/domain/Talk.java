package ru.itmo.wp.model.domain;

import java.util.Date;

public class Talk {
    private long id;
    private long sourceUserId;
    private long targetUserId;
    private String text;
    private Date creationTime;
    private String sourceUserLogin;
    private String targetUserLogin;

    public void setId(long id) {
        this.id = id;
    }

    public void setSourceUserId(long sourceUserId) {
        this.sourceUserId = sourceUserId;
    }

    public void setTargetUserId(long targetUserId) {
        this.targetUserId = targetUserId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getSourceUserLogin() {
        return sourceUserLogin;
    }

    public void setSourceUserLogin(String sourceUserLogin) {
        this.sourceUserLogin = sourceUserLogin;
    }

    public String getTargetUserLogin() {
        return targetUserLogin;
    }

    public void setTargetUserLogin(String targetUserLogin) {
        this.targetUserLogin = targetUserLogin;
    }

    public long getId() {
        return id;
    }

    public long getSourceUserId() {
        return sourceUserId;
    }

    public long getTargetUserId() {
        return targetUserId;
    }

    public String getText() {
        return text;
    }

    public Date getCreationTime() {
        return creationTime;
    }
}
