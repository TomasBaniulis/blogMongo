package lt.code.academy.blogmongo.dto;

import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
@NoArgsConstructor
public class Comment {
    private ObjectId id;
    private String commentText;
    private String username;
    private LocalDateTime dateTime;
}
