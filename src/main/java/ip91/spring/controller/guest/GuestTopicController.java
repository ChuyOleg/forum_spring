package ip91.spring.controller.guest;

import ip91.spring.controller.util.UriPath;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class GuestTopicController {

    // TODO: implement (pagination and filtration)
    @GetMapping(UriPath.TOPICS)
    public String getTopicCatalogPage() {
        return "mock";
    }

}
