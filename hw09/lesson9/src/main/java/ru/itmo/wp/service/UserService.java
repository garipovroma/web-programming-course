package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.Role;
import ru.itmo.wp.domain.Tag;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.PostForm;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.repository.RoleRepository;
import ru.itmo.wp.repository.UserRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {
    private final UserRepository userRepository;

    /** @noinspection FieldCanBeLocal, unused */
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;

        this.roleRepository = roleRepository;
        for (Role.Name name : Role.Name.values()) {
            if (roleRepository.countByName(name) == 0) {
                roleRepository.save(new Role(name));
            }
        }
    }

    public User register(UserCredentials userCredentials) {
        User user = new User();
        user.setLogin(userCredentials.getLogin());
        userRepository.save(user);
        userRepository.updatePasswordSha(user.getId(), userCredentials.getLogin(), userCredentials.getPassword());
        return user;
    }

    public boolean isLoginVacant(String login) {
        return userRepository.countByLogin(login) == 0;
    }

    public User findByLoginAndPassword(String login, String password) {
        return login == null || password == null ? null : userRepository.findByLoginAndPassword(login, password);
    }

    public User findById(Long id) {
        return id == null ? null : userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAllByOrderByIdDesc();
    }

    public void writePost(User user, PostForm postForm, TagService tagService) {

        Post post = new Post();
        post.setUser(user);
        post.setTitle(postForm.getTitle());
        post.setText(postForm.getText());

        List <String> stringList = Arrays.asList(postForm.getTags().trim().split("\\s+"));
        Function<String, Tag> stringToTag = new Function<String, Tag>() {
            @Override
            public Tag apply(String s) {
                Tag tag = new Tag();
                tag.setName(s);
                if (tagService.findByName(s) == null) {
                    tagService.save(tag);
                }
                return tagService.findByName(s);
            }
        };

        Stream<Tag> tagStream = stringList.stream().distinct().map(stringToTag);
        if (!(stringList.size() == 1 && stringList.contains(""))) {
            List<Tag> tagList = tagStream.collect(Collectors.toList());
            post.setTags(tagList);
        }
        user.addPost(post);
        userRepository.save(user);
    }
}
