package ip91.spring.controller.user;

import ip91.spring.controller.util.UriPath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(UriPath.USER_PREFIX + UriPath.TOPICS)
public class UserPostController {

    // TODO: implement
    @GetMapping("/{topic_id}" + UriPath.POSTS)
    public String getPostCreationPage(@PathVariable(name = "topic_id") Long topicId) {
        return "mock";
    }

    // TODO: implement
    @PostMapping("/{topic_id}" + UriPath.POSTS)
    public String createPost(@PathVariable(name = "topic_id") Long topicId) {
        return "mock";
    }

}
