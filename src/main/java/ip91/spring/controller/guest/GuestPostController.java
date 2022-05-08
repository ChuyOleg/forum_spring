package ip91.spring.controller.guest;

import ip91.spring.controller.util.UriPath;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class GuestPostController {

    // TODO: implement
    @GetMapping(UriPath.TOPICS + "/{topic_id}" + UriPath.POSTS)
    public String getPostCatalogPage(@PathVariable(name = "topic_id") Long topicId) {
        return "mock";
    }

}
