package lt.code.academy.blogmongo.service;

import lombok.AllArgsConstructor;
import lt.code.academy.blogmongo.repository.UserRepository;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
}
