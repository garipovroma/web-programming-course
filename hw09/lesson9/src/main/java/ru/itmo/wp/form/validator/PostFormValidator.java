package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.PostForm;
import ru.itmo.wp.form.UserCredentials;

import java.util.Arrays;
import java.util.function.Predicate;

@Component
public class PostFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PostForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            PostForm postForm = (PostForm) target;
            if (!Arrays.stream(postForm.getTags().split("\\s+")).allMatch(new Predicate<String>() {
                @Override
                public boolean test(String s) {
                    return s.matches("[a-z]+");
                }
            })){
                errors.rejectValue("tags", "tags.pattern-mismatch", "Tags can contain only latin characters");
            }
        }
    }
}
