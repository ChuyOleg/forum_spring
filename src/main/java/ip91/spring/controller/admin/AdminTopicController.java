package ip91.spring.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ip91.spring.controller.util.Attribute;
import ip91.spring.controller.util.HtmlPagePath;
import ip91.spring.controller.util.UriPath;
import ip91.spring.model.dto.TopicDto;
import ip91.spring.model.entity.Category;
import ip91.spring.model.entity.Topic;
import ip91.spring.model.entity.User;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static ip91.spring.controller.util.Attribute.*;

@Controller
@RequestMapping(UriPath.ADMIN_PREFIX + UriPath.TOPICS)
@RequiredArgsConstructor
@Log
public class AdminTopicController {

    private final TopicService topicService;

    private final static String SUCCESS = "success";

    @GetMapping()
    @Operation(
            tags = {"ADMIN"},
            summary = "Get html page for creation new topic.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful request", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})}
    )
    public String getTopicCreationPage(Model model) {
        model.addAttribute(TOPIC_DTO, new TopicDto());
        model.addAttribute(CATEGORY_LIST, Category.values());

        return HtmlPagePath.ADMIN_CREATE_TOPIC_PAGE;
    }

    @PostMapping()
    @Operation(
            tags = {"ADMIN"},
            summary = "Create new topic.",
            responses = {
                    @ApiResponse(responseCode = "302", links = {@Link(operationId = "topicCatalog")}, description = "New topic has been successfully created. Redirect to /topics .", content = {@Content()}),
                    @ApiResponse(responseCode = "400", description = "Error. Return topicCreationPage again.", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})
            }
    )
    public String createTopic(@ModelAttribute(name = Attribute.TOPIC_DTO) @Valid TopicDto topicDto,
                              BindingResult bindingResult,
                              HttpServletResponse response,
                              Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(TOPIC_DTO, topicDto);
            model.addAttribute(CATEGORY_LIST, Category.values());

            response.setStatus(400);
            return HtmlPagePath.ADMIN_CREATE_TOPIC_PAGE;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long currentUserId = ((User) authentication.getPrincipal()).getId();

        topicService.create(topicDto, currentUserId);

        return UriPath.REDIRECT + UriPath.TOPICS;
    }

    @GetMapping("/{topic_id}")
    @Operation(
            tags = {"ADMIN"},
            summary = "Get html page for editing the selected topic.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful request", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})}
    )
    public String getTopicEditingPage(@PathVariable(name = "topic_id") Long topicId,
                                      @RequestParam(name = SUCCESS, required = false) String success,
                                      Model model) {

        Topic topic = topicService.getById(topicId);
        TopicDto topicDto = new TopicDto();

        topicDto.setTitle(topic.getTitle());
        topicDto.setCategory(topic.getCategory().name());

        model.addAttribute(TOPIC_DTO, topicDto);
        model.addAttribute(CATEGORY_LIST, Category.values());
        model.addAttribute(SUCCESS, success);

        return HtmlPagePath.ADMIN_EDIT_TOPIC_PAGE;
    }

    @PutMapping("/{topic_id}")
    @Operation(
            tags = {"ADMIN"},
            summary = "Update selected topic.",
            responses = {
                    @ApiResponse(responseCode = "302", description = "Selected topic has been successfully updated. Redirect to /admin/topics/{topic_id} .", content = {@Content()}),
                    @ApiResponse(responseCode = "400", description = "Error. Return topicEditingPage again.", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})
            }
    )
    public String updateTopic(@PathVariable(name = "topic_id") Long topicId,
                              @ModelAttribute(name = Attribute.TOPIC_DTO) @Valid TopicDto topicDto,
                              BindingResult bindingResult,
                              HttpServletResponse response,
                              HttpServletRequest request,
                              RedirectAttributes redirectAttributes,
                              Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(TOPIC_DTO, topicDto);
            model.addAttribute(CATEGORY_LIST, Category.values());

            response.setStatus(400);
            return HtmlPagePath.ADMIN_EDIT_TOPIC_PAGE;
        }

        topicService.update(topicDto, topicId);

        redirectAttributes.addAttribute(SUCCESS, true);
        return UriPath.REDIRECT + request.getRequestURI() + UriPath.QUESTION_MARK + SUCCESS + true;
    }

    @DeleteMapping("/{topic_id}")
    @Operation(
            tags = {"ADMIN"},
            summary = "Delete selected topic.",
            responses = {@ApiResponse(responseCode = "302", links = {@Link(operationId = "topicCatalog")}, description = "Selected topic has been successfully deleted. Redirect to /topics .", content = {@Content()}),}
    )
    public String deleteTopic(@PathVariable(name = "topic_id") Long topicId) {
        topicService.deleteById(topicId);
        return UriPath.REDIRECT + UriPath.TOPICS;
    }

}
