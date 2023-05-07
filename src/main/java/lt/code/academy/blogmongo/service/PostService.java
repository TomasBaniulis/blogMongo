package lt.code.academy.blogmongo.service;

import lombok.AllArgsConstructor;
import lt.code.academy.blogmongo.document.PostDocument;
import lt.code.academy.blogmongo.dto.Comment;
import lt.code.academy.blogmongo.dto.Post;
import lt.code.academy.blogmongo.repository.PostRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;


    public void createPost (Post post){
        postRepository.insert(PostDocument.convert(post));
    }

    public List <Post> showAllPosts (){
        return postRepository.findAll().
                stream().
                map(Post::convert).
                toList();
    }

    public Post showSinglePost (ObjectId id){
        return Post.convert(Objects.requireNonNull(postRepository.findById(id).orElse(null)));
    }
    public void deletePost (ObjectId id){
        postRepository.deleteById(id);
    }

    public void updatePost (Post post){
        Post odlPost = showSinglePost(post.getId());
        List<Comment>comments = odlPost.getComments();
        post.setPostDate(LocalDate.now());
        post.setComments(comments);
        postRepository.save(PostDocument.convert(post));
    }

    public void createComment (ObjectId id, Comment comment){
        Post post = showSinglePost(id);
        List <Comment> comments = post.getComments();
        comment.setId(UUID.randomUUID());
        comment.setDateTime(LocalDateTime.now());
        comment.setUsername("Tomas");
        comments.add(comment);
        post.setComments(comments);
        postRepository.save(PostDocument.convert(post));

    }




}
