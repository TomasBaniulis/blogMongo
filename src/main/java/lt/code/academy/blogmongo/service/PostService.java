package lt.code.academy.blogmongo.service;

import lombok.AllArgsConstructor;
import lt.code.academy.blogmongo.document.PostDocument;
import lt.code.academy.blogmongo.dto.Post;
import lt.code.academy.blogmongo.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;


    public void createPost (Post post){
        postRepository.insert(PostDocument.convert(post, "Posts"));
    }

    public List <Post> showAllPosts (){
        List<Post> posts = postRepository.findAll().
                stream().
                map(Post::convert).
                toList();
        posts.forEach(System.out::println);
        return posts;
    }


}
