package gr.avalanche.mongobatch.controllers;

import gr.avalanche.mongobatch.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * NotificationsController Class
 * @author npapadopoulos
 */
@Controller
public class NotificationsController {

    @RequestMapping("/")
    public String home() {
        return Constants.HOME_PAGE;
    }
}
