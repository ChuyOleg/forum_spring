package ip91.spring.model.service;

import ip91.spring.model.dto.PostDto;
import ip91.spring.model.entity.Post;
import ip91.spring.model.entity.Topic;
import ip91.spring.model.entity.User;
import ip91.spring.model.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log
public class PostService {

    private final PostRepository postRepository;

    public void create(PostDto postDto, Long topicId, Long userId) {
        Post post = new Post(postDto);

        Topic topic = Topic.builder().id(topicId).build();
        User user = User.builder().id(userId).build();

        post.setTopic(topic);
        post.setCreator(user);

        postRepository.save(post);
    }

}
