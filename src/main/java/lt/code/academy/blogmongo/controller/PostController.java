package lt.code.academy.blogmongo.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lt.code.academy.blogmongo.dto.Comment;
import lt.code.academy.blogmongo.dto.Post;
import lt.code.academy.blogmongo.service.MessageService;
import lt.code.academy.blogmongo.service.PostService;
import org.bson.types.ObjectId;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller
public class PostController {

    private final PostService postService;
    private final MessageService messageService;

    @GetMapping("/public/blog")
    public String showAllPosts(Model model) {
        List<Post> allPosts = postService.showAllPosts();
        List<Post> postsToShow = new ArrayList<>();
        for(int i=0; i<3; i++){
            postsToShow.add(allPosts.get(i));
        }
        model.addAttribute("posts", postsToShow);
        return "blog";
    }

    @PostMapping("/public/blog")
    public String showMorePosts(@RequestParam("number") String number, Model model) {
        List<Post> allPosts = postService.showAllPosts();
        List<Post> postsToShow = new ArrayList<>();
        int biggerNumber = Integer.parseInt(number) + 3;

        if (biggerNumber < allPosts.size()) {

            for (int i = 0; i < biggerNumber; i++) {
                postsToShow.add(allPosts.get(i));
            }
            model.addAttribute("posts", postsToShow);

            return "blog";
        }

        postsToShow.addAll(allPosts);
        model.addAttribute("posts", postsToShow);

        return "blog";

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/blog/create")
    public String openPostCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "/form/post";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/blog/create")
    public String createPost(@Valid Post post, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/form/post";
        }
        List<Comment> comments = new ArrayList<>();
        post.setPostDate(LocalDate.now());
        post.setComments(comments);
        postService.createPost(post);
        return "redirect:/public/blog";
    }

    @GetMapping("public/blog/{postId}")
    public String showSinglePost(@PathVariable ObjectId postId, Model model) {
        model.addAttribute("post", postService.showSinglePost(postId));
        model.addAttribute("comment", new Comment());
        return "singlePost";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/blog/{postId}/update")
    public String updatePostForm(@PathVariable ObjectId postId, Model model) {
        model.addAttribute("post", postService.showSinglePost(postId));
        return "/form/postUpdate";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/blog/{postId}/update")
    public String updatePost(Post post, @PathVariable ObjectId postId) {
        postService.updatePost(post);
        return "redirect:/public/blog/" + post.getId();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/blog/{postId}/delete")
    public String deletePost(@PathVariable ObjectId postId, Model model) {
        postService.deletePost(postId);
        model.addAttribute("posts", postService.showAllPosts());
        return "redirect:/public/blog";
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/blog/{postId}/newComment")
    public String createComment(@PathVariable ObjectId postId, Comment comment) {
        postService.createComment(postId, comment);
        return "redirect:/public/blog/" + postId;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/blog/{postId}/delete/{commentId}")
    public String deleteComment(@PathVariable ObjectId postId, @PathVariable String commentId) {
        postService.deleteComment(postId, commentId);
        return "redirect:/public/blog/" + postId;
    }

    @PostMapping("/public/blog/search")
    public String search(Model model, @RequestParam("searchText") String searchText) {

        model.addAttribute("posts", postService.showSearchedPosts(searchText));

        return "blog";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/porn")
    public String showPorn() {
        return "porn";
    }


}
