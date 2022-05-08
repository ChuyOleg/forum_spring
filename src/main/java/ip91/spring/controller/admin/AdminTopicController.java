package ip91.spring.controller.admin;

import ip91.spring.controller.util.UriPath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(UriPath.ADMIN_PREFIX + UriPath.TOPICS)
public class AdminTopicController {

    // TODO: implement
    @GetMapping()
    public String getTopicCreationPage() {
        return "mock";
    }

    // TODO: implement
    @PostMapping
    public String createTopic() {
        return "mock";
    }

    // TODO: implement
    @GetMapping("/{topic_id}")
    public String getTopicEditingPage(@PathVariable(name = "topic_id") Long topicId) {
        return "mock";
    }

    // TODO: implement
    @PutMapping("/{topic_id}")
    public String updateTopic(@PathVariable(name = "topic_id") Long topicId) {
        return "mock";
    }

    // TODO: implement
    @DeleteMapping("/{topic_id}")
    public String deleteTopic(@PathVariable(name = "topic_id") Long topicId) {
        return "mock";
    }

}
