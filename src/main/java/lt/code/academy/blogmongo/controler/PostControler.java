package lt.code.academy.blogmongo.controler;

import lombok.AllArgsConstructor;
import lt.code.academy.blogmongo.dto.Comment;
import lt.code.academy.blogmongo.dto.Post;
import lt.code.academy.blogmongo.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/blog")
public class PostControler {

    private final PostService postService;
    @GetMapping()
    public String showAllPosts (Model model){
        model.addAttribute("posts", postService.showAllPosts());
        return "blog";
    }
    @GetMapping("/create")
    public String openPostCreateForm (Model model){
        model.addAttribute("post", new Post());
        return "/form/post";
    }
    @PostMapping("/create")
    public String createPost (Post post, Model model) {
        List<Comment> comments = new ArrayList<>();
        post.setPostDate(LocalDate.now());
        post.setComments(comments);
        postService.createPost(post);
        model.addAttribute("post", new Post());
        model.addAttribute("message", "Post created successfully");
        return "/form/post";

    }
}
