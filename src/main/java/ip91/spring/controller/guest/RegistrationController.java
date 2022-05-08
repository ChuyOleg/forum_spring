package ip91.spring.controller.guest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ip91.spring.controller.util.Attribute;
import ip91.spring.controller.util.HtmlPagePath;
import ip91.spring.controller.util.UriPath;
import ip91.spring.model.dto.UserDto;
import ip91.spring.model.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Log
public class RegistrationController {

    private final UserService userService;

    @GetMapping(UriPath.REGISTRATION)
    @Operation(
            tags = {"GUEST"},
            summary = "Get registration page",
            responses = {@ApiResponse(description = "Registration page.", responseCode = "200", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})}
    )
    public String getRegistrationPage(Model model) {
        model.addAttribute(Attribute.USER_DTO, new UserDto());

        return HtmlPagePath.GUEST_REGISTRATION_PAGE;
    }

    // TODO: implement
    @PostMapping(UriPath.REGISTRATION)
    @Operation(
            tags = {"GUEST"},
            summary = "Register new user account",
            responses = {
                    @ApiResponse(responseCode = "302", links = {@Link(name = "Link to /login", operationId = "Login")}, description = "Account has been successfully created. Redirect to /login.", content = {@Content()}),
                    @ApiResponse(responseCode = "400", description = "Error. Return registration page again.", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})
            }
    )
    public String createUserAccount(@ModelAttribute(name = Attribute.USER_DTO) @Valid UserDto userDto,
                                            BindingResult bindingResult,
                                            HttpServletResponse response,
                                            Model model) {

        return "mock";
    }

}
