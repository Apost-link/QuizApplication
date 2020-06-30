package ru.aniskov.petproject.db.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.aniskov.petproject.db.service.UserService;
import ru.aniskov.petproject.pojo.model.QuizUser;
import ru.aniskov.petproject.db.repository.UserRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public Optional<QuizUser> findUserById(long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<QuizUser> findUserByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Iterable<QuizUser> findUserAll() {
        return repository.findAll();
    }

    @Override
    public QuizUser saveUser(QuizUser quizUser) {
        quizUser.setRegistrerDate(new Date());
        return repository.save(quizUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<QuizUser> user = repository.findByName(username);

        if(!user.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }

        QuizUser quizUser = user.get();

        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(quizUser.getRole()));

        return new User(quizUser.getName(), quizUser.getPassword(), authorities);
    }
}
