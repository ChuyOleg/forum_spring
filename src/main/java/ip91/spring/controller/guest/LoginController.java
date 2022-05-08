package ip91.spring.controller.guest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ip91.spring.controller.util.HtmlPagePath;
import ip91.spring.controller.util.UriPath;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(UriPath.LOGIN)
    @Operation(
            operationId = "Login",
            tags = {"GUEST"},
            summary = "Get login page for authentication",
            responses = {@ApiResponse(description = "Login page", responseCode = "200", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})}
    )
    public String getLoginPage() {
        return HtmlPagePath.GUEST_LOGIN_PAGE;
    }

}
