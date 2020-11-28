package ru.itmo.wp.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@SuppressWarnings("unused")
public class UserStatusForm {
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^(enable|disable)$")
    private String action;

    private long id;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
