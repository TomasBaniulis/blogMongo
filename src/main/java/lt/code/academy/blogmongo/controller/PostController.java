package lt.code.academy.blogmongo.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lt.code.academy.blogmongo.dto.Comment;
import lt.code.academy.blogmongo.dto.Post;
import lt.code.academy.blogmongo.service.MessageService;
import lt.code.academy.blogmongo.service.PostService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/blog")
public class PostController {

    private final PostService postService;
    private final MessageService messageService;

    @GetMapping()
    public String showAllPosts (Model model ){
        model.addAttribute("posts", postService.showAllPosts());
        return "blog";
    }
    @GetMapping("/create")
    public String openPostCreateForm (Model model){
        model.addAttribute("post", new Post());
        return "/form/post";
    }
    @PostMapping("/create")
    public String createPost ( @Valid Post post, Model model, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "/form/post";
        }
        List<Comment> comments = new ArrayList<>();
        post.setPostDate(LocalDate.now());
        post.setComments(comments);
        postService.createPost(post);
        model.addAttribute("post", new Post());
        model.addAttribute("message", "Post created successfully");
        return "redirect:/blog";
    }

    @GetMapping("/{postId}")
    public String showSinglePost (@PathVariable ObjectId postId, Model model){
        model.addAttribute("post", postService.showSinglePost(postId));
        model.addAttribute("comment", new Comment());
        return "singlePost";
    }
    @GetMapping("/{postId}/update")
    public String updatePostForm (@PathVariable ObjectId postId, Model model){
        model.addAttribute("post", postService.showSinglePost(postId));
        return "/form/postUpdate";
    }
    @PostMapping("/{postId}/update")
    public String updatePost (Post post, @PathVariable ObjectId postId){
        postService.updatePost(post);
        return "redirect:/blog/" + post.getId();
    }

    @GetMapping("/{postId}/delete")
    public String deletePost (@PathVariable ObjectId postId, Model model){
        postService.deletePost(postId);
        model.addAttribute("posts", postService.showAllPosts());
        return "redirect:/blog";
    }

    @PostMapping("/{postId}/newComment")
    public String createComment (@PathVariable ObjectId postId, Comment comment){
        postService.createComment(postId, comment);
        return "redirect:/blog/" + postId;
    }

    @GetMapping("/{postId}/{commentId}/delete")
    public String deleteComment (@PathVariable ObjectId postId, @PathVariable String commentId){
        postService.deleteComment(postId, commentId);
        return "redirect:/blog/" + postId;
    }

    @PostMapping ("/search")
    public String search (Model model, @RequestParam ("searchText") String searchText){

        model.addAttribute("posts", postService.showSearchedPosts(searchText));

        return "redirect:/blog";
    }



}
