package lt.code.academy.blogmongo.service;

import com.mongodb.client.model.Filters;
import jakarta.jws.soap.SOAPBinding;
import lombok.AllArgsConstructor;
import lt.code.academy.blogmongo.document.UserDocument;
import lt.code.academy.blogmongo.dto.Role;
import lt.code.academy.blogmongo.dto.User;
import lt.code.academy.blogmongo.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public void createUser (User user){
        Set<Role> roles = new HashSet<>();
        Role userRole = new Role("USER");
//        Role adminRole = new Role("ADMIN");
        roles.add(userRole);
//        roles.add(adminRole);
        user.setRoles(roles);
        userRepository.insert(UserDocument.convert(user));
    }
    public void deleteUser (ObjectId id){
        userRepository.deleteById(id);
    }
    public void updateUser (User user){
        userRepository.save(UserDocument.convert(user));
    }

    public List<String> getAllUserNames (){
        List<User>users = userRepository.findAll()
                .stream()
                .map(User::convert)
                .toList();
        List<String>usernames = new ArrayList<>();
        for (User user : users){
            usernames.add(user.getUsername());
        }
        return usernames;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDocument userDocument = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
        return User.convert(userDocument);
    }
}
