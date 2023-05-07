package lt.code.academy.blogmongo.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lt.code.academy.blogmongo.repository.PostRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
}
