package ip91.spring.controller.guest;

import ip91.spring.controller.util.HtmlPagePath;
import ip91.spring.controller.util.UriPath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(UriPath.LOGIN)
    public String getLoginPage() {
        return HtmlPagePath.GUEST_LOGIN_PAGE;
    }

}
