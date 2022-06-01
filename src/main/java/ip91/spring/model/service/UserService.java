package ip91.spring.model.service;

import ip91.spring.model.dto.UserDto;
import ip91.spring.model.entity.Role;
import ip91.spring.model.entity.User;
import ip91.spring.model.exception.user.UsernameIsReservedException;
import ip91.spring.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);

        return userOptional.orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Transactional()
    public void registerNewAccount(UserDto userDto) throws UsernameIsReservedException {
        checkUsernameIsUnique(userDto.getUsername());

        User user = new User(userDto);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        log.info("New account has been created: " + user.getUsername());
    }

    private void checkUsernameIsUnique(String username) throws UsernameIsReservedException {
        if (userRepository.existsByUsername(username)) throw new UsernameIsReservedException();
    }

}
