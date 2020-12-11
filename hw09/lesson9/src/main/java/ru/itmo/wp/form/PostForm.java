package ru.itmo.wp.form;

import javax.validation.constraints.*;

public class PostForm {
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 150)
    private String title;

    @Size(max = 100)
    private String tags;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 1500)
    private String text;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
