package ip91.spring.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ip91.spring.controller.util.Attribute;
import ip91.spring.controller.util.HtmlPagePath;
import ip91.spring.controller.util.UriPath;
import ip91.spring.model.dto.PostDto;
import ip91.spring.model.entity.Topic;
import ip91.spring.model.entity.User;
import ip91.spring.model.service.PostService;
import ip91.spring.model.service.TopicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static ip91.spring.controller.util.Attribute.*;

@Controller
@RequestMapping(UriPath.USER_PREFIX + UriPath.TOPICS)
@RequiredArgsConstructor
@Log
public class UserPostController {

    private final TopicService topicService;
    private final PostService postService;

    @GetMapping("/{topic_id}" + UriPath.POSTS)
    @Operation(
            tags = {"USER-ADMIN"},
            summary = "Get html page for creation post to the selected topic",
            responses = {@ApiResponse(responseCode = "200", description = "Successful request", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})}
    )
    public String getPostCreationPage(@PathVariable(name = "topic_id") Long topicId,
                                      Model model) {

        Topic topic = topicService.getById(topicId);

        model.addAttribute(TOPIC, topic);
        model.addAttribute(POST_DTO, new PostDto());

        return HtmlPagePath.USER_CREATE_POST_PAGE;
    }

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

        if (bindingResult.hasErrors()) {
            Topic topic = topicService.getById(topicId);

            model.addAttribute(TOPIC, topic);
            model.addAttribute(POST_DTO, postDto);
            response.setStatus(400);
            return HtmlPagePath.USER_CREATE_POST_PAGE;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long currentUserId = ((User) authentication.getPrincipal()).getId();

        postService.create(postDto, topicId, currentUserId);

        return UriPath.REDIRECT + UriPath.TOPICS + UriPath.SLASH + topicId + UriPath.POSTS;
    }

}
