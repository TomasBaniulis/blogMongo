package lt.code.academy.blogmongo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.code.academy.blogmongo.document.PostDocument;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Post {
    private ObjectId id;
    private String postName;
    private String postText;
    private LocalDate postDate;
    private String picture;
    private List<Comment> comments;



    public static Post convert (PostDocument postDocument){
        return new Post(postDocument.getId(),
                postDocument.getPostName(),
                postDocument.getPostText(),
                postDocument.getPostDate(),
                postDocument.getPicture(),
                postDocument.getComments());

    }

}
