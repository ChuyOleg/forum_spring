package ip91.spring.controller.guest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ip91.spring.controller.util.UriPath;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class GuestTopicController {

    // TODO: implement (pagination and filtration)
    @GetMapping(UriPath.TOPICS)
    @Operation(
            operationId = "topicCatalog",
            tags = {"GUEST-USER-ADMIN"},
            summary = "Get page of topics using filtration",
            responses = {@ApiResponse(responseCode = "200", description = "Successful request", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})}
    )
    public String getTopicCatalogPage() {
        return "mock";
    }

}
