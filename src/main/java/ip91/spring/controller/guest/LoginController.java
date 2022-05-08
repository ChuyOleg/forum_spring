package ip91.spring.controller.guest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ip91.spring.controller.util.HtmlPagePath;
import ip91.spring.controller.util.UriPath;
import lombok.extern.java.Log;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Level;

@Controller
@Log
public class LoginController {

    @GetMapping(UriPath.LOGIN)
    @Operation(
            operationId = "Login",
            tags = {"GUEST"},
            summary = "Get login page for authentication",
            responses = {@ApiResponse(description = "Login page", responseCode = "200", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})}
    )
    public String getLoginPage() {
        log.log(Level.INFO, "User has got login page");
        return HtmlPagePath.GUEST_LOGIN_PAGE;
    }

}
