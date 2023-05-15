package lt.code.academy.blogmongo.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.code.academy.blogmongo.dto.Comment;
import lt.code.academy.blogmongo.dto.Post;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Document(collection = "Posts")
public class PostDocument {
    @Id
    private ObjectId id;
    private String postName;
    @TextIndexed
    private String postText;
    private LocalDate postDate;
    private String picture;
    private List<Comment> comments;

    public static PostDocument convert(Post post) {
        return new PostDocument(post.getId(),
                post.getPostName(),
                post.getPostText(),
                post.getPostDate(),
                post.getPicture(),
                post.getComments());

    }
}
