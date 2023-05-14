package lt.code.academy.blogmongo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.code.academy.blogmongo.document.UserDocument;
import org.bson.types.ObjectId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User implements UserDetails {
    private ObjectId id;
//    @NotBlank
//    @Size(min = 3, max = 20)
    private String name;
//    @NotBlank
//    @Size(min = 5, max=20)
    private String surname;
//    @NotBlank
//    @Max(20)
    private String username;
    private String avatar;
//    @NotBlank
//    @Email
    private String email;
//    @NotBlank
//    @Size(min = 4)
    private String password;
//    @NotBlank
//    @Size(min=4)
    private String repeatPassword;
    private Set<Role> roles;

    public User(ObjectId id, String name, String surname, String username, String avatar, String email, String password, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.avatar = avatar;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public static User convert (UserDocument userDocument){
        return new User(userDocument.getId(),
                userDocument.getName(),
                userDocument.getSurname(),
                userDocument.getUsername(),
                userDocument.getAvatar(),
                userDocument.getEmail(),
                userDocument.getPassword(),
                userDocument.getRoles());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
