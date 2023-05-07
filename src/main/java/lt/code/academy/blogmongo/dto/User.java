package lt.code.academy.blogmongo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.code.academy.blogmongo.document.UserDocument;
import org.bson.types.ObjectId;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {
    private ObjectId id;
    private String name;
    private String surname;
    private String username;
    private String avatar;
    private String email;
    private String password;
    private String repeatPassword;

    public User(ObjectId id, String name, String surname, String username, String avatar, String email, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.avatar = avatar;
        this.email = email;
        this.password = password;
    }

    public static User convert (UserDocument userDocument){
        return new User(userDocument.getId(),
                userDocument.getName(),
                userDocument.getSurname(),
                userDocument.getUsername(),
                userDocument.getAvatar(),
                userDocument.getEmail(),
                userDocument.getPassword());
    }
}
