package lt.code.academy.blogmongo.service;

import com.mongodb.client.model.Filters;
import lombok.AllArgsConstructor;
import lt.code.academy.blogmongo.document.UserDocument;
import lt.code.academy.blogmongo.dto.User;
import lt.code.academy.blogmongo.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void createUser (User user){
        userRepository.insert(UserDocument.convert(user));
    }
    public void deleteUser (ObjectId id){
        userRepository.deleteById(id);
    }
    public void updateUser (User user){
        userRepository.save(UserDocument.convert(user));
    }
//    public User showUser (String username, String password){
//        UserDocument userDocument = userRepository.findOne(Filters.and(Filters.eq("userName", username), Filters.eq("password", password))).orElse(null);
//        return User.convert(userDocument);
//
//    }
}
