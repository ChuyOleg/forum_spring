package ip91.spring.model.service;

import ip91.spring.model.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log
public class PostService {

    private final PostRepository postRepository;

}
