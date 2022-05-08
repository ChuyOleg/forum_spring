package ip91.spring.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ip91.spring.controller.util.Attribute;
import ip91.spring.controller.util.UriPath;
import ip91.spring.model.dto.TopicDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(UriPath.ADMIN_PREFIX + UriPath.TOPICS)
public class AdminTopicController {

    // TODO: implement
    @GetMapping()
    @Operation(
            tags = {"ADMIN"},
            summary = "Get html page for creation new topic.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful request", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})}
    )
    public String getTopicCreationPage(Model model) {
        return "mock";
    }

    // TODO: implement
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
                              Model model) {
        return "mock";
    }

    // TODO: implement
    @GetMapping("/{topic_id}")
    @Operation(
            tags = {"ADMIN"},
            summary = "Get html page for editing the selected topic.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful request", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})}
    )
    public String getTopicEditingPage(@PathVariable(name = "topic_id") Long topicId) {
        return "mock";
    }

    // TODO: implement
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
                              Model model) {
        return "mock";
    }

    // TODO: implement
    @DeleteMapping("/{topic_id}")
    @Operation(
            tags = {"ADMIN"},
            summary = "Delete selected topic.",
            responses = {@ApiResponse(responseCode = "302", links = {@Link(operationId = "topicCatalog")}, description = "Selected topic has been successfully deleted. Redirect to /topics .", content = {@Content()}),}
    )
    public String deleteTopic(@PathVariable(name = "topic_id") Long topicId) {
        return "mock";
    }

}
