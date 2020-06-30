package ru.aniskov.petproject.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.aniskov.petproject.pojo.model.QuizUser;
import ru.aniskov.petproject.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class PostgresUserDetailsService implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public PostgresUserDetailsService(UserRepository userRepository){
        this.repository = userRepository;
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
