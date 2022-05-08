package ip91.spring.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ip91.spring.controller.util.Attribute;
import ip91.spring.controller.util.UriPath;
import ip91.spring.model.dto.PostDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping(UriPath.USER_PREFIX + UriPath.TOPICS)
public class UserPostController {

    // TODO: implement
    @GetMapping("/{topic_id}" + UriPath.POSTS)
    @Operation(
            tags = {"USER-ADMIN"},
            summary = "Get html page for creation post to the selected topic",
            responses = {@ApiResponse(responseCode = "200", description = "Successful request", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})}
    )
    public String getPostCreationPage(@PathVariable(name = "topic_id") Long topicId) {
        return "mock";
    }

    // TODO: implement
    @PostMapping("/{topic_id}" + UriPath.POSTS)
    @Operation(
            tags = {"USER-ADMIN"},
            summary = "Create new post for the selected topic.",
            responses = {
                    @ApiResponse(responseCode = "302", links = {@Link(operationId = "postCatalog")}, description = "New post has been successfully created. Redirect to /topics/{topic_id}/posts .", content = {@Content()}),
                    @ApiResponse(responseCode = "400", description = "Error. Return postCreationPage again.", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})
            }
    )
    public String createPost(@PathVariable(name = "topic_id") Long topicId,
                             @ModelAttribute(name = Attribute.POST_DTO) @Valid PostDto postDto,
                             BindingResult bindingResult,
                             HttpServletResponse response,
                             Model model) {
        return "mock";
    }

}
