package ip91.spring.controller.guest;

import ip91.spring.controller.util.Attribute;
import ip91.spring.controller.util.HtmlPagePath;
import ip91.spring.controller.util.UriPath;
import ip91.spring.model.dto.UserDto;
import ip91.spring.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping(UriPath.REGISTRATION)
    public String getRegistrationPage(Model model) {
        model.addAttribute(Attribute.USER_DTO, new UserDto());

        return HtmlPagePath.GUEST_REGISTRATION_PAGE;
    }

    // TODO: implement
    @PostMapping(UriPath.REGISTRATION)
    public String createUserAccount(@ModelAttribute(name = Attribute.USER_DTO) @Valid UserDto userDto,
                                    BindingResult bindingResult,
                                    Model model) {
        return "mock";
    }

}
