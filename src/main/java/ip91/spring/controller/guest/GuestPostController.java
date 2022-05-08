package ip91.spring.controller.guest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ip91.spring.controller.util.UriPath;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
@Log
public class GuestPostController {

    // TODO: implement
    @GetMapping(UriPath.TOPICS + "/{topic_id}" + UriPath.POSTS)
    @Operation(
            operationId = "postCatalog",
            tags = {"GUEST-USER-ADMIN"},
            summary = "Get all posts of selected topic",
            responses = {@ApiResponse(responseCode = "200", description = "Successful request", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})}
    )
    public String getPostCatalogPage(@PathVariable(name = "topic_id") Long topicId,
                                     Model model) {
        return "mock";
    }

}
