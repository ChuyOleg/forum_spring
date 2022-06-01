package ip91.spring.controller.guest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ip91.spring.controller.util.HtmlPagePath;
import ip91.spring.controller.util.UriPath;
import ip91.spring.model.dto.UserDto;
import ip91.spring.model.exception.user.UsernameIsReservedException;
import ip91.spring.model.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static ip91.spring.controller.util.Attribute.*;

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
        model.addAttribute(USER_DTO, new UserDto());

        return HtmlPagePath.GUEST_REGISTRATION_PAGE;
    }

    @PostMapping(UriPath.REGISTRATION)
    @Operation(
            tags = {"GUEST"},
            summary = "Register new user account",
            responses = {
                    @ApiResponse(responseCode = "302", links = {@Link(name = "Link to /login", operationId = "Login")}, description = "Account has been successfully created. Redirect to /login.", content = {@Content()}),
                    @ApiResponse(responseCode = "400", description = "Error. Return registration page again.", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})
            }
    )
    public String createUserAccount(@ModelAttribute(name = USER_DTO) @Valid UserDto userDto,
                                            BindingResult bindingResult,
                                            HttpServletResponse response,
                                            Model model) {

        if (!bindingResult.hasFieldErrors(USER_DTO_FIELD_PASSWORD) && !userDto.getPassword().equals(userDto.getPasswordCopy())) {
            bindingResult.addError(
                    new FieldError(UserDto.class.getName(), USER_DTO_FIELD_PASSWORD_COPY, USER_ERROR_PASSWORDS_NOT_MATCH)
            );
        }

        if (!bindingResult.hasErrors()) {
            try {
                log.info("User wants to create new account");
                userService.registerNewAccount(userDto);

                return UriPath.REDIRECT + UriPath.LOGIN;
            } catch (UsernameIsReservedException e) {
                model.addAttribute(USER_ERROR_USERNAME_IS_RESERVED, true);
                log.warning(String.format("Username (%s) is reserved", userDto.getUsername()));
            }
        }

        log.warning("User print incorrect data during registration");
        response.setStatus(400);
        return HtmlPagePath.GUEST_REGISTRATION_PAGE;
    }

}
