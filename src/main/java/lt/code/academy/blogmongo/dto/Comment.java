package lt.code.academy.blogmongo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Comment {
    private ObjectId id;
    private String commentText;
    private String username;
    private LocalDateTime dateTime;
}
