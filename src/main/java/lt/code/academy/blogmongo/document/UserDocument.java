package lt.code.academy.blogmongo.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.code.academy.blogmongo.dto.User;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Document(collection = "Users")
public class UserDocument {
    @Id
    private ObjectId id;
    private String name;
    private String surname;
    private String username;
    private String avatar;
    private String email;
    private String password;

    public static UserDocument convert (User user){
        return new UserDocument(user.getId(),
                user.getName(),
                user.getSurname(),
                user.getUsername(),
                user.getAvatar(),
                user.getEmail(),
                user.getPassword());
    }
}
