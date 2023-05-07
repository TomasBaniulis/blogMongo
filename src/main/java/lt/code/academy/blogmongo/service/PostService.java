package lt.code.academy.blogmongo.service;

import lombok.AllArgsConstructor;
import lt.code.academy.blogmongo.document.PostDocument;
import lt.code.academy.blogmongo.dto.Post;
import lt.code.academy.blogmongo.repository.PostRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;


    public void createPost (Post post){
        postRepository.insert(PostDocument.convert(post, "Posts"));
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




}
